package com.uusoft.atp.web.demo;

import java.sql.*;
import java.util.HashMap;



public class JDBCTestConect {
	public void sqlupdate(String sql) throws ClassNotFoundException, SQLException{

	//1.配置账户信息
	String ip = "192.168.2.43";
	String sid = "tradedb";
	String port = "1521";
	String dbUser = "act_trade";
	String dbPassword = "1";
	String driver= "oracle.jdbc.driver.OracleDriver";

	//2.加载oracle
	Class.forName(driver);
	String url= "jdbc:oracle:thin:@"+ ip + ":" + port + ":" + sid;
	Connection conn= DriverManager.getConnection(url, dbUser, dbPassword);
	//4.数据库信息
	//String sql = "select * from epb_sme_user " +"where username = '18721694074'";
	Statement stmt = conn.createStatement();
	//获取执行sql
	stmt.executeQuery(sql);
	stmt.close();
	conn.close();
	}
	
	
	public String sqlselect (String sql) throws ClassNotFoundException, SQLException{

	//1.配置账户信息
	String ip = "192.168.2.43";
	String sid = "tradedb";
	String port = "1521";
	String dbUser = "act_trade";
	String dbPassword = "1";
	String driver= "oracle.jdbc.driver.OracleDriver";

	//2.加载oracle
	Class.forName(driver);
	String url= "jdbc:oracle:thin:@"+ ip + ":" + port + ":" + sid;
	Connection conn= DriverManager.getConnection(url, dbUser, dbPassword);
	//4.数据库信息
	//String sql = "select * from epb_sme_user " +"where username = '18721694074'";
	Statement stmt = conn.createStatement();
	//获取执行sql

	ResultSet rs= stmt.executeQuery(sql);
	String idStr = null;
	while(rs.next()){
		   HashMap<String,String> map = new HashMap<String,String>();
           map.put("platformno", rs.getString("platformno"));
           for(Object se:map.keySet()){
        	   idStr = (String)map.get(se);
        	   }
	}
	stmt.close();
	conn.close();
	return idStr;
	}
//	public static void main(String[] args) throws ClassNotFoundException, SQLException{
//		JDBCTestConect jd = new JDBCTestConect();
//		System.out.println(jd.sqlselect("select a.platformno from ACCT_PLATFORM a where a.custno=831"));
//	}
}
