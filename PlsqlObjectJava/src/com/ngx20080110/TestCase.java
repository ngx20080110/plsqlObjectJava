package com.ngx20080110;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class TestCase {

	public abstract void dbAction(Connection conn, CallableStatement cs) throws SQLException;
	
	public void test() {
		Connection conn = null;
		CallableStatement cs = null;
		
		try {
			//Connection
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@10.225.8.96:1521:ICIST6", "lyodba", "lyo");
			
			dbAction(conn, cs);
		}
		catch (Exception ex) {
			ex.printStackTrace();

		} finally{
			closeConnection(conn, cs);
		}
	}

	protected void closeConnection(Connection conn, PreparedStatement ps){
		try{
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
