package com.zyproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private String jdbcName="com.mysql.jdbc.Driver"; //驱动类
	private String dbUrl="jdbc:mysql://localhost:3306/db_blog?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&&useSSL=false"; // // 数据库连接地址ַ
	private String dbUserName="root"; // 用户名
	private String dbPassword="123456"; // 密码


	/*
	private static final String jdbcName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbUrl="jdbc:sqlserver://localhost:1433;databaseName=db_student";
	private String dbUserName="sa"; // 用户名
	private String dbPassword="123456"; // 密码
		*/
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon(){
		try {
			Class.forName(jdbcName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}

