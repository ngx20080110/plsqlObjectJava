package com.ngx20080110.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleDriver;

public class TestClientInfo {

	public static void main(String[] args) throws SQLException {
		Properties props = new Properties();
		props.setProperty("user", "scott");
		props.setProperty("password", "oracle");
		props.put("v$session.machine", "server IP"); // Web server IP
		props.put("v$session.program", "icis_web"); // Web application name
		props.put("v$session.terminal", "browser"); // Default browser
		Connection conn = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection("jdbc:oracle:thin:@10.225.11.74:1521:db03", props);
			conn.prepareCall("begin  "
					+ "dbms_application_info.set_module(module_name => 'BDYMNT',action_name => 'UPDATE'); " // Current module and action 
					+ "dbms_application_info.set_client_info('login_name client_terminal client_ip'); " // login name, client terminal(IE, Chrome, Firefox), client IP
					+ "end;").execute();			
			// set_client_info max 64 chars
			Thread.sleep(1 * 60 * 1000);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

}
