package com.sully.excel.operate;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Created by lei.s on 2017/2/23.
 */

public class ExcelReader1 {

    /**
     * 解析并显示一个表的内容和使用指定的样式
     *
     * @param styles
     * @param strings
     * @param sheetInputStream
     */
    public static List<String[]> processSheet(StylesTable styles, ReadOnlySharedStringsTable strings,
                                              InputStream sheetInputStream, int minColumns)
            throws IOException, ParserConfigurationException, SAXException {

        InputSource sheetSource = new InputSource(sheetInputStream);
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        XMLReader sheetParser = saxParser.getXMLReader();
        ExcelReaderHandler handler = new ExcelReaderHandler(styles, strings, minColumns, System.out);

        sheetParser.setContentHandler(handler);
        sheetParser.parse(sheetSource);
        return handler.getRows();
    }

    /**
     * 解析第一个sheet
     *
     * @param path
     * @param minColumns
     * @return List<String[]>
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static List<String[]> processOneSheet(String path, int minColumns)
            throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        OPCPackage p = OPCPackage.open(path, PackageAccess.READ);
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(p);
        XSSFReader xssfReader = new XSSFReader(p);
        StylesTable styles = xssfReader.getStylesTable();
        InputStream stream = xssfReader.getSheet("rId1");
        List<String[]> list = processSheet(styles, strings, stream, minColumns);
        stream.close();
        return list;
    }

    public static void main(String[] args) throws Exception {
        /*
         * long begin = System.currentTimeMillis() ; List<String[]> list =
         * ExcelReader.processOneSheet("d:\\201401-ds.xlsx" , 18);
         * //List<String[]> list = ExcelReader.processOneSheet("d:\\out.xlsx" ,
         * 10); for (String cell : list.get(1)) { System.out.print(cell + "  ");
         * System.out.println(cell == null); } long end =
         * System.currentTimeMillis() ; System.out.println("用时：" + (end - begin)
         * /1000 + "秒");
         */
        long begin = System.currentTimeMillis();
        System.out.println(begin);
        List<String[]> list = ExcelReader1.processOneSheet("e:/wph/月结.xlsx", 5);
        long end = System.currentTimeMillis();
        System.out.println("读取用时：" + (end - begin) / 1000 + "秒，总量：" + list.size());
        Connection conn = getNew_Conn();
        conn.setAutoCommit(false);
        PreparedStatement pstmt = conn.prepareStatement("insert into temp_table values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (int i = 0; i < list.size(); i++) {
            String[] row = list.get(i);
            for (int index = 1; index <= 18; index++) {
                if (row[index - 1] == null) {
                    pstmt.setNull(index, Types.NULL);
                } else {
                    pstmt.setObject(index, row[index - 1]);
                }
            }

            pstmt.addBatch();
            if (i > 0 && i % 10000 == 0) {
                pstmt.executeBatch();
                System.out.println("提交：" + i);
            }
        }
        pstmt.executeBatch();
        conn.commit();
        pstmt.close();
        conn.close();
        end = System.currentTimeMillis();
        System.out.println("插入用时：" + (end - begin) / 1000 + "秒");
    }

    private static Connection getNew_Conn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jeeframe_cms?useUnicode=true&amp;characterEncoding=UTF-8", "root",
                    "1392010");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;
    }

}