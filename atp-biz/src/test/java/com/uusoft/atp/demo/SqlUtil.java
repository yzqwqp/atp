package com.uusoft.atp.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtil {

	public static void main(String[] args) {
		//mysql test
		String sqlStr = "select * from ba_userinvited a where a.invite = 'eae190'";
//		String valueStr = SqlUtil.executeSql(openConn("mysql","mss"), sqlStr, "status");
		
		//oracle test 
//		String sqlStr = "update sale_app_awatrans a set a.custfullname = '王3' where a.appsheetserialno = '201703090000000008235224'";
//		String valueStr = SqlUtil.executeSql(openConn("oracle",""), sqlStr, "");
		
//		System.out.println(valueStr);
	}
	
	
	public static Connection openConn(String driver,String sid) {
		String url = null;
		String dbuser = null;
		String dbpwd = null;
		Connection conn = null;
		//109测试环境对应的oracle数据库和mysql数据库
		if (driver.isEmpty() || driver.equals("oracle")){
			url = "jdbc:oracle:thin:@192.168.10.95:1521:ORCL";
			driver = "oracle.jdbc.driver.OracleDriver";
			dbuser = "kd_sale_dx";
			dbpwd = "zxcvfr";
		} else {
			url = "jdbc:mysql://192.168.10.95:3306/"+sid+"?characterEncoding=utf8&allowMultiQueries=true";
			driver = "com.mysql.jdbc.Driver";
			dbuser = "66money";
			dbpwd = "qwerfv";
		}
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbuser, dbpwd);  
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return conn;
	}
  	 
	public static Object[] executeSql(Connection conn, String sql_query, String column) {
		ResultSet rs = null;  
		PreparedStatement pst = null;  
		int length = 0;
		Object[] value = null;
		try { 
			pst = conn.prepareStatement(sql_query);
			rs = pst.executeQuery();
			rs.last(); 
			length = rs.getRow();
			value = new Object[length];
			System.out.println(length);
			rs.beforeFirst();
			if (!column.isEmpty()) {
				int i = 0;
				while(rs.next()) {
					System.out.println(i);
					System.out.println(rs.getObject(column).toString());
					value[i] = rs.getObject(column);
					i++;
				} 
			}
		} catch (SQLException e) {  
			e.printStackTrace();  
		} finally {  
			try {  
				if(rs != null) {  
					rs.close();  
				}  
				if(pst != null) {  
					pst.close();  
				}  
				if(conn != null) {  
					conn.close();  
				}  
			} catch (SQLException e) { 
				e.printStackTrace();  
			}  
		}  
		return value;
	} 
}
