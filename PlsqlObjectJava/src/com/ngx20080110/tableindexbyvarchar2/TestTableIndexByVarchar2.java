package com.ngx20080110.tableindexbyvarchar2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ngx20080110.TestCase;

import oracle.jdbc.OracleTypes;

public class TestTableIndexByVarchar2 extends TestCase {

	@Override
	public void dbAction(Connection conn, CallableStatement cs) throws SQLException {
		
		cs = conn.prepareCall("{call test_table_ib_varchar2(?, ?)}");
		cs.registerOutParameter(1, OracleTypes.ARRAY, "ARR_TIV");
		cs.registerOutParameter(2, OracleTypes.VARCHAR);
		
		cs.execute();
		
		String result = cs.getString(2);
		if ("OK".equals(result)) {
			String[] strings = (String[])cs.getArray(1).getArray();
			for (String string : strings) {
				System.out.println(string);
			}
		}
		else {
			System.out.println(result);
		}
	}

}
