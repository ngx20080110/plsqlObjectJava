package test;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;

public class Test {

	public static void main(String[] args) {
		Connection connection = null;
		
		try {
			//Connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.225.8.96:1521:ICIST3", "lyodba", "lyo");
			
			TestTableObject tto = new TestTableObject();
			tto.ANumber(0.9999999999998);
			tto.AChar("02");
			tto.AVarchar2("Test varchar2 3");
			tto.ANVarchar2("Test測試água");
			Calendar cal = Calendar.getInstance();
			cal.set(2016, 4, 20, 16, 52, 45);
			tto.ADate(cal.getTime());
//			tto.AClob(DBHelper.file2Clob("D:\\connections.xml", connection));
//			tto.ABlob(DBHelper.file2Blob("D:\\image_resource\\mw_foot.jpg", connection));
//			testInsert(tto, connection);
			
//			testArrayObject(connection);
			testInOutArrayObject(connection);
		}
		catch (Exception ex) {
			ex.printStackTrace();

		} finally{
			closeConnection(connection, null);
		}
	}
	
	private static void testArrayObject(Connection conn) {
		CallableStatement cs = null;
		try {
			//Mapping necessary types 
			Map<String, Class<?>> typeMaps = conn.getTypeMap(); 
			typeMaps.put(TestTableObject.ORACLE_OBJECT_NAME, TestTableObject.class);
			
			cs = conn.prepareCall("{call pkg_test.test_array_object(?, ?)}");
			cs.registerOutParameter(1, OracleTypes.ARRAY, "ARR_OBJ_TEST");
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			
			cs.execute();
			
			System.out.println(cs.getString(2));
			Object[] ttos = (Object[])cs.getArray(1).getArray();
			for (Object tto : ttos) {
				TestTableObject to = (TestTableObject)tto;
				System.out.println("a_varchar=" + to.AVarchar2());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(null, cs);
		}
	}
	
	private static void testInOutArrayObject(Connection conn) {
		CallableStatement cs = null;
		try {
			//Mapping necessary types 
			Map<String, Class<?>> typeMaps = conn.getTypeMap(); 
			typeMaps.put(TestTableObject.ORACLE_OBJECT_NAME, TestTableObject.class);
			
			cs = conn.prepareCall("{call pkg_test.test_inout_array_object(?, ?)}");

			TestTableObject[] array = new TestTableObject[2];
			TestTableObject tto1 = new TestTableObject();
			tto1.AVarchar2("test1");
			TestTableObject tto2 = new TestTableObject();
			tto2.AVarchar2("test2");
			array[0] = tto1;
			array[1] = tto2;
			Array arrObjTest = ((OracleConnection)conn).createOracleArray("ARR_OBJ_TEST", array);
			
			cs.setArray(1, arrObjTest);
			cs.registerOutParameter(1, OracleTypes.ARRAY, "ARR_OBJ_TEST");
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			
			cs.execute();
			
			System.out.println(cs.getString(2));
			Object[] ttos = (Object[])cs.getArray(1).getArray();
			for (Object tto : ttos) {
				TestTableObject to = (TestTableObject)tto;
				System.out.println("a_varchar=" + to.AVarchar2());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(null, cs);
		}
	}
	
	private static void testInsert(TestTableObject tto, Connection connection) {
		CallableStatement cs = null;
		try {
			//Mapping necessary types 
			Map<String, Class<?>> typeMaps = connection.getTypeMap(); 
			typeMaps.put(TestTableObject.ORACLE_OBJECT_NAME, TestTableObject.class);
			
			cs = connection.prepareCall("{call pkg_test.add(?, ?)}");
			cs.setObject(1, tto);
			cs.registerOutParameter(1, OracleTypes.STRUCT, TestTableObject.ORACLE_OBJECT_NAME);
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			
			cs.execute();
			tto = (TestTableObject)cs.getObject(1);
			System.out.println(cs.getString(2));
			System.out.println("a_varchar=" + tto.AVarchar2());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeConnection(null, cs);
		}
	}

	private static void closeConnection(Connection conn, PreparedStatement ps){
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
