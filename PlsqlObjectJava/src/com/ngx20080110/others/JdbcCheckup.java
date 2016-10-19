package com.ngx20080110.others;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class JdbcCheckup {

	public static void main(String[] args) throws Exception {
		System.out.println("Please enter information to test connection to the database");
		String user;
		String password;
		String database;
		
		user = readEntry("user:");
		int slashIndex = user.indexOf('/');
		if (slashIndex != -1) {
			password = user.substring(slashIndex + 1);
			user = user.substring(0, slashIndex);
		}
		else {
			password = readEntry("password:");
		}
		database = readEntry("database:");
		
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:" + user + "/" + password + "@" + database);
		System.out.println("Connecting to the database...");
		System.out.flush();
		
		System.out.println("Connecting...");
		Connection conn = ods.getConnection();
		System.out.println("Connected.");
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select 'Hello World' from dual");
		while (rset.next()) {
			System.out.println(rset.getString(1));
		}
		System.out.println("Your JDBC installation is correct.");
		rset.close();
		stmt.close();
		conn.close();
	}

	static String readEntry(String prompt) {
		try {
			StringBuffer buffer = new StringBuffer();
			System.out.println(prompt);
			System.out.flush();
			int c = System.in.read();
			while (c != '\n' && c != -1) {
				buffer.append((char)c);
				c = System.in.read();
			}
			return buffer.toString().trim();
		}
		catch (IOException e) {
			return "";
		}
	}
}
