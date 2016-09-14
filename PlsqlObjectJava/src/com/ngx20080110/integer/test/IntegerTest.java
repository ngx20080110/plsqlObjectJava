package com.ngx20080110.integer.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.ngx20080110.TestCase;
import com.ngx20080110.integer.entity.Person;

import oracle.jdbc.OracleTypes;

public class IntegerTest extends TestCase {

	@Override
	public void dbAction(Connection conn, CallableStatement cs) throws SQLException {	
			Map<String, Class<?>> typeMaps = conn.getTypeMap(); 
			typeMaps.put(Person.ORACLE_OBJECT_NAME, Person.class);
			
			Person person = new Person();
			person.setId(1);
			person.setAge(34);
			
			cs = conn.prepareCall("{call pkg_test.test_integer_object(?, ?)}");
			cs.setObject(1, person);
			cs.registerOutParameter(1, OracleTypes.STRUCT, Person.ORACLE_OBJECT_NAME);
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			
			cs.execute();
			
			String result = cs.getString(2);
			if ("OK".equals(result)) {
				person = (Person)cs.getObject(1);
				System.out.println("Name=" + person.getName());
				System.out.println("Age=" + person.getAge());
			}
			else {
				System.out.println(result);
			}
	}

}
