package com.ngx20080110.others;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import oracle.jdbc.pool.OracleDataSource;

public class TestJDBCVersion {

	public static void main(String[] args) {
		try {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:lyodba/lyo@10.225.8.96:1521:ICIST6");
			Connection connection = ods.getConnection();
			DatabaseMetaData meta = connection.getMetaData();
			
			System.out.println("JDBC driver version is " + meta.getDriverVersion());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
