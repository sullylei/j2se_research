package com.sully.excel.operate;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class XxlsBig extends SheetHandler {
	public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
		XxlsBig howto = new XxlsBig("tbl_fin_wph_sales_detail_monthly_statement");
		howto.processOneSheet("E:\\wph\\月结.xlsx");
//		howto.process("F:/wph/月结.xlsx");
		howto.close();
        long end = System.currentTimeMillis();
        System.out.println("花费时间："+(end-start));;
	}
	
	public XxlsBig(String tableName) throws SQLException{
        this.conn = getNew_Conn();
		this.statement = conn.createStatement();
		this.tableName = tableName;
	}

	private Connection conn = null;
	private Statement statement = null;
	private PreparedStatement newStatement = null;

	private String tableName = "temp_table";
	private boolean create = false;
	
	public void optRows(int curRow, List<String> rowlist) throws Exception {
		if (curRow == 0) {
//			StringBuffer preSql = new StringBuffer("insert into " + tableName
//					+ " values(");
//			StringBuffer table = new StringBuffer("create table " + tableName
//					+ "(");
//			int c = rowlist.size();
//			for (int i = 0; i < 2; i++) {
//				preSql.append("?,");
//                table.append(rowlist.get(i));
//
//				table.append("  varchar(200) ,");
//			}
//
//			table.deleteCharAt(table.length() - 1);
//			preSql.deleteCharAt(preSql.length() - 1);
//			table.append(")");
//			preSql.append(")");
//			if (create) {
//				statement = conn.createStatement();
//				try{
//					statement.execute("drop table "+tableName);
//				}catch(Exception e){
//
//				}finally{
//					System.out.println("表 "+tableName+" 删除成功");
//				}
//				if (!statement.execute(table.toString())) {
//					System.out.println("创建表 "+tableName+" 成功");
//					// return;
//				} else {
//					System.out.println("创建表 "+tableName+" 失败");
//					return;
//				}
//			}
            StringBuffer preSql = new StringBuffer("insert into " + tableName
					+ " values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?)");
			conn.setAutoCommit(false);
			newStatement = conn.prepareStatement(preSql.toString());

		} else
        if(curRow>0) {
			// 一般行
//			int col = rowlist.size();
//			for (int i = 0; i < col; i++) {
//				newStatement.setString(i + 2, rowlist.get(i).toString());
//			}
            newStatement.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
            newStatement.setInt(2, 1);
            newStatement.setString(3, rowlist.get(0).toString());
            newStatement.setString(4, rowlist.get(1).toString());
            System.out.println(rowlist.get(3).toString());
            newStatement.setString(5, rowlist.get(3).toString());
            newStatement.setInt(6, 1);
            newStatement.setString(7, rowlist.get(4).toString());
            newStatement.setString(8, rowlist.get(5).toString());
            newStatement.setString(9, rowlist.get(6).toString());
            newStatement.setString(10, rowlist.get(6).toString());
            newStatement.setString(11, rowlist.get(7).toString());

            newStatement.setString(12, rowlist.get(12).toString());
            newStatement.setString(13, rowlist.get(13).toString());
            System.out.println(rowlist.get(15).toString());
            newStatement.setInt(14, Integer.parseInt(rowlist.get(15).toString()));
            newStatement.setDouble(15, Double.parseDouble(rowlist.get(16).toString()));

            newStatement.setDouble(16, Double.parseDouble(rowlist.get(18).toString()));
            newStatement.setDouble(17, Double.parseDouble(rowlist.get(21).toString()));
            newStatement.setDouble(18, Double.parseDouble(rowlist.get(22).toString()));
            newStatement.setDouble(19, Double.parseDouble(rowlist.get(23).toString()));
            newStatement.setDouble(20, Double.parseDouble(rowlist.get(24).toString()));
            newStatement.setInt(21, 1);
            newStatement.setString(22, "201702");

			newStatement.addBatch();
            if (curRow % 1000 == 0) {
				newStatement.executeBatch();
				conn.commit();
			}
		}
	}
	
    private static Connection getNew_Conn() {
        Connection conn = null;
        Properties props = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream("e:/wph/database.properties");
            props.load(fis);
//            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            StringBuffer jdbcURLString = new StringBuffer();
            jdbcURLString.append("jdbc:mysql://");
            jdbcURLString.append(props.getProperty("host"));
            jdbcURLString.append(":");
            jdbcURLString.append(props.getProperty("port"));
            jdbcURLString.append("/");
            jdbcURLString.append(props.getProperty("database"));
            conn = DriverManager.getConnection(jdbcURLString.toString(), props
                    .getProperty("user"), props.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    
	public int close() {
		try {
			newStatement.executeBatch();
			conn.commit();
			System.out.println("数据写入完毕");
			this.newStatement.close();
			this.statement.close();
			this.conn.close();
			return 1;
		} catch (SQLException e) {
			return 0;
		}
	}
}
