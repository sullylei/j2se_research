package com.sully.excel.operate;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * Created by lei.s on 2017/2/23.
 */
public class ExcelReaderHandler extends DefaultHandler {

    /**
     * The type of the data value is indicated by an attribute on the cell. The
     * value is usually in a "v" element within the cell.
     */
    enum xssfDataType {
        BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
    }

    /**
     * Table with styles
     */
    private StylesTable stylesTable;

    /**
     * Table with unique strings
     */
    private ReadOnlySharedStringsTable sharedStringsTable;

    /**
     * Destination for data
     */
    private final PrintStream output;

    /**
     * Number of columns to read starting with leftmost
     */
    private final int minColumnCount;

    // Set when V start element is seen
    private boolean vIsOpen;

    // Set when cell start element is seen;
    // used when cell close element is seen.
    private xssfDataType nextDataType;

    // Used to format numeric cell values.
    private short formatIndex;
    private String formatString;
    private final DataFormatter formatter;

    private int thisColumn = -1;
    // The last column printed to the output stream
    private int lastColumnNumber = -1;

    // Gathers characters as they are seen.
    private StringBuffer value;
    private String[] record;
    private List<String[]> rows = new ArrayList<String[]>();
    private boolean isCellNull = false;

    /**
     * Accepts objects needed while parsing.
     *
     * @param styles
     *            Table of styles
     * @param strings
     *            Table of shared strings
     * @param cols
     *            Minimum number of columns to show
     * @param target
     *            Sink for output
     */
    public ExcelReaderHandler(StylesTable styles, ReadOnlySharedStringsTable strings, int cols, PrintStream target) {
        this.stylesTable = styles;
        this.sharedStringsTable = strings;
        this.minColumnCount = cols;
        this.output = target;
        this.value = new StringBuffer();
        this.nextDataType = xssfDataType.NUMBER;
        this.formatter = new DataFormatter();
        record = new String[this.minColumnCount];
        rows.clear();// 每次读取都清空行集合
    }

    /*
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {

        if ("inlineStr".equals(name) || "v".equals(name)) {
            vIsOpen = true;
            // Clear contents cache
            value.setLength(0);
        }
        // c => cell
        else if ("c".equals(name)) {
            // Get the cell reference
            String r = attributes.getValue("r");
            int firstDigit = -1;
            for (int c = 0; c < r.length(); ++c) {
                if (Character.isDigit(r.charAt(c))) {
                    firstDigit = c;
                    break;
                }
            }
            thisColumn = nameToColumn(r.substring(0, firstDigit));

            // Set up defaults.
            this.nextDataType = xssfDataType.NUMBER;
            this.formatIndex = -1;
            this.formatString = null;
            String cellType = attributes.getValue("t");
            String cellStyleStr = attributes.getValue("s");
            if ("b".equals(cellType))
                nextDataType = xssfDataType.BOOL;
            else if ("e".equals(cellType))
                nextDataType = xssfDataType.ERROR;
            else if ("inlineStr".equals(cellType))
                nextDataType = xssfDataType.INLINESTR;
            else if ("s".equals(cellType))
                nextDataType = xssfDataType.SSTINDEX;
            else if ("str".equals(cellType))
                nextDataType = xssfDataType.FORMULA;
            else if (cellStyleStr != null) {
                // It's a number, but almost certainly one
                // with a special style or format
                int styleIndex = Integer.parseInt(cellStyleStr);
                XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                this.formatIndex = style.getDataFormat();
                this.formatString = style.getDataFormatString();
                if (this.formatString == null)
                    this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String name) throws SAXException {

        String thisStr = null;

        // v => contents of a cell
        if ("v".equals(name)) {
            // Process the value contents as required.
            // Do now, as characters() may be called more than once
            switch (nextDataType) {

                case BOOL:
                    char first = value.charAt(0);
                    thisStr = first == '0' ? "FALSE" : "TRUE";
                    break;

                case ERROR:
                    thisStr = "\"ERROR:" + value.toString() + '"';
                    break;

                case FORMULA:
                    // A formula could result in a string value,
                    // so always add double-quote characters.
                    // thisStr = '"' + value.toString() + '"';
                    thisStr = value.toString();
                    break;

                case INLINESTR:
                    XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                    // thisStr = '"' + rtsi.toString() + '"';
                    thisStr = rtsi.toString();
                    break;

                case SSTINDEX:
                    String sstIndex = value.toString();
                    try {
                        int idx = Integer.parseInt(sstIndex);
                        XSSFRichTextString rtss = new XSSFRichTextString(sharedStringsTable.getEntryAt(idx));
                        // thisStr = '"' + rtss.toString() + '"';
                        thisStr = rtss.toString();
                    } catch (NumberFormatException ex) {
                        output.println("Failed to parse SST index '" + sstIndex + "': " + ex.toString());
                    }
                    break;

                case NUMBER:
                    String n = value.toString();
                    // 判断是否是日期格式
                    if (HSSFDateUtil.isADateFormat(this.formatIndex, n)) {
                        Double d = Double.parseDouble(n);
                        Date date = HSSFDateUtil.getJavaDate(d);
                        thisStr = formateDateToString(date);
                    } else if (this.formatString != null)
                        thisStr = formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex,
                                this.formatString);
                    else
                        thisStr = n;
                    break;

                default:
                    thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
                    break;
            }

            // Output after we've seen the string contents
            // Emit commas for any fields that were missing on this row
            if (lastColumnNumber == -1) {
                lastColumnNumber = 0;
            }
            // 判断单元格的值是否为空
            if (thisStr == null || "".equals(isCellNull)) {
                isCellNull = true;// 设置单元格是否为空值
            }
            record[thisColumn] = thisStr;
            // Update column
            if (thisColumn > -1)
                lastColumnNumber = thisColumn;

        } else if ("row".equals(name)) {

            // Print out any missing commas if needed
            if (this.minColumnCount > 0) {
                // Columns are 0 based
                if (lastColumnNumber == -1) {
                    lastColumnNumber = 0;
                }
                if (isCellNull == false && record[0] != null && record[1] != null)// 判断是否空行
                {
                    rows.add(record.clone());
                    isCellNull = false;
                    for (int i = 0; i < record.length; i++) {
                        record[i] = null;
                    }
                }
            }
            lastColumnNumber = -1;
        }

    }

    public List<String[]> getRows() {
        return rows;
    }

    public void setRows(List<String[]> rows) {
        this.rows = rows;
    }

    /**
     * Captures characters only if a suitable element is open. Originally was
     * just "v"; extended for inlineStr also.
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (vIsOpen)
            value.append(ch, start, length);
    }

    /**
     * Converts an Excel column name like "C" to a zero-based index.
     *
     * @param name
     * @return Index corresponding to the specified name
     */
    private int nameToColumn(String name) {
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }

    private String formateDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期
        return sdf.format(date);
    }
}
