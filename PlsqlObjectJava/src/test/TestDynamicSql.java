package test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;

public class TestDynamicSql {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement cs = null;
		try {
			//Connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.225.8.96:1521:ICIST3", "lyodba", "lyo");
			
			BaseBlock baseBlock = new BaseBlock();
			baseBlock.setStart(1);
			baseBlock.setEnd(5);
			baseBlock.setOrderbyClause("body_nb desc");
			baseBlock.setWhereClause("nb > :1 and lb_surname like :2 and pers_nb is not null");
			BindObject bindObject1 = new BindObject();
			bindObject1.setType("number");
			bindObject1.setIntValue(100000);
			BindObject bindObject2 = new BindObject();
			bindObject2.setType("string");
			bindObject2.setStringValue("%AI%");
			baseBlock.setBindObjects(new BindObject[] {bindObject1, bindObject2});
			baseBlock.setupArrays((OracleConnection)connection);
			
			//Mapping necessary types 
			Map<String, Class<?>> typeMaps = connection.getTypeMap(); 
			typeMaps.put(BaseBlock.ORACLE_OBJECT_NAME, BaseBlock.class);
			typeMaps.put(BindObject.ORACLE_OBJECT_NAME, BindObject.class);
			
			cs = connection.prepareCall("{call test_dynamic_sql(?)}");
			cs.setObject(1, baseBlock);
			cs.registerOutParameter(1, OracleTypes.STRUCT, BaseBlock.ORACLE_OBJECT_NAME);
			
			cs.execute();
			BaseBlock returnBaseBlock = (BaseBlock)cs.getObject(1);
			System.out.println("msg=" + returnBaseBlock.getMsg());
		}
		catch (Exception ex) {
			ex.printStackTrace();

		} finally{
			closeConnection(connection, cs);
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
