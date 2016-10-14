package com.ngx20080110.objinh;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.ngx20080110.TestCase;

import oracle.jdbc.OracleTypes;

public class TestObjectInherit extends TestCase {

	@Override
	public void dbAction(Connection conn, CallableStatement cs) throws SQLException {
		Map<String, Class<?>> typeMaps = conn.getTypeMap(); 
		typeMaps.put(Teacher.ORACLE_OBJECT_NAME, Teacher.class);
		cs = conn.prepareCall("{call test_be_teacher(?)}");
		
		Teacher teacher = new Teacher();
		teacher.setId(1);
		teacher.setName("wugy");
		
		cs.setObject(1, teacher);
		cs.registerOutParameter(1, OracleTypes.STRUCT, Teacher.ORACLE_OBJECT_NAME);
		
		cs.execute();
		
		teacher = (Teacher)cs.getObject(1);
		System.out.println(teacher);
	}

	public static void main(String[] args) {
		TestCase test = new TestObjectInherit();
		test.test();
	}
}
