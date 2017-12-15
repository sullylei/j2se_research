package com.sully.excel.operate;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * XSSF and SAX (Event API)
 */
public abstract class XxlsAbstract extends DefaultHandler {
	private SharedStringsTable sst;
	private String lastContents;
	private boolean nextIsString;

	private int sheetIndex = -1;
	private List<String> rowlist = new ArrayList<String>();
	private int curRow = 0;
	private int curCol = 0;

    //定义前一个元素和当前元素的位置，用来计算其中空的单元格数量，如A6和A8等
    private String preRef = null, ref = null;
    //定义该文档一行最大的单元格数，用来补全一行最后可能缺失的单元格
    private String maxRef = null;

	//excel记录行操作方法，以行索引和行元素列表为参数，对一行元素进行操作，元素为String类型
//	public abstract void optRows(int curRow, List<String> rowlist) throws SQLException ;
	
	//excel记录行操作方法，以sheet索引，行索引和行元素列表为参数，对sheet的一行元素进行操作，元素为String类型
	public abstract void optRows(int sheetIndex,int curRow, List<String> rowlist) throws Exception;
	
	//只遍历一个sheet，其中sheetId为要遍历的sheet索引，从1开始，1-3
	public void processOneSheet(String filename,int sheetId) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		
		XMLReader parser = fetchSheetParser(sst);

		// rId2 found by processing the Workbook
		// 根据 rId# 或 rSheet# 查找sheet
		InputStream sheet2 = r.getSheet("rId"+sheetId);
		sheetIndex++;
		InputSource sheetSource = new InputSource(sheet2);
		parser.parse(sheetSource);
		sheet2.close();
	}

	/**
	 * 遍历 excel 文件
	 */
	public void process(String filename) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		Iterator<InputStream> sheets = r.getSheetsData();

		while (sheets.hasNext()) {
			curRow = 0;
			sheetIndex++;
			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
		}
	}

	public XMLReader fetchSheetParser(SharedStringsTable sst)
			throws SAXException {
		XMLReader parser = XMLReaderFactory
				.createXMLReader("org.apache.xerces.parsers.SAXParser");
		this.sst = sst;
		parser.setContentHandler(this);
		return parser;
	}

	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// c => 单元格
		if (name.equals("c")) {

			// 如果下一个元素是 SST 的索引，则将nextIsString标记为true
			String cellType = attributes.getValue("t");
			if (cellType != null && cellType.equals("s")) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
		}
		// 置空
		lastContents = "";
	}
    /**
     * 字符串的填充
     * @param str
     * @param len
     * @param let
     * @param isPre
     * @return
     */
    String fillChar(String str, int len, char let, boolean isPre){
        int len_1 = str.length();
        if(len_1 <len){
            if(isPre){
                for(int i=0;i<(len-len_1);i++){
                    str = let+str;
                }
            }else{
                for(int i=0;i<(len-len_1);i++){
                    str = str+let;
                }
            }
        }
        return str;
    }

    /**
     * 计算两个单元格之间的单元格数目(同一行)
     * @param ref
     * @param preRef
     * @return
     */
    public int countNullCell(String ref, String preRef){
        //excel2007最大行数是1048576，最大列数是16384，最后一列列名是XFD
        String xfd = ref.replaceAll("\\d+", "");
        String xfd_1 = preRef.replaceAll("\\d+", "");

        xfd = fillChar(xfd, 3, '@', true);
        xfd_1 = fillChar(xfd_1, 3, '@', true);

        char[] letter = xfd.toCharArray();
        char[] letter_1 = xfd_1.toCharArray();
        int res = (letter[0]-letter_1[0])*26*26 + (letter[1]-letter_1[1])*26 + (letter[2]-letter_1[2]);
        return res-1;
    }

	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// 根据SST的索引值的到单元格的真正要存储的字符串
		// 这时characters()方法可能会被调用多次
		if (nextIsString) {
			try {
				System.out.println("lastContents:"+lastContents);
                int idx = Integer.parseInt(lastContents);

				lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
						.toString();
                System.out.println("lastContents2222:"+lastContents);
			} catch (Exception e) {

			}
		}

		// v => 单元格的值，如果单元格是字符串则v标签的值为该字符串在SST中的索引
		// 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
		if (name.equals("v")) {
			String value = lastContents.trim();
            System.out.println("value:"+value);
			value = value.equals("")?" ":value;
			rowlist.add(curCol, value);
			curCol++;
		}else {
			//如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
			if (name.equals("row")) {
                String value = "";
                //默认第一行为表头，以该行单元格数目为最大数目
                if(curRow == 0){
                    maxRef = ref;
                }
                //补全一行尾部可能缺失的单元格
                if(maxRef != null){
                    int len = countNullCell(maxRef, ref);
                    for(int i=0;i<=len;i++){
                        rowlist.add(curCol, "");
                        curCol++;
                    }
                }
				try {
                    System.out.println("rowlist:"+rowlist);
                    optRows(sheetIndex, curRow, rowlist);
				} catch (Exception e) {
					System.out.println("解析optRows失败"+e);
				}
				rowlist.clear();
				curRow++;
				curCol = 0;
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		//得到单元格内容的值
		lastContents += new String(ch, start, length);
	}
}
