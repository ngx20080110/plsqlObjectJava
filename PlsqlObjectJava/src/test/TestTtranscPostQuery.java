package test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;

public class TestTtranscPostQuery {

	public static void main(String[] args) {
		Connection connection = null;
		CallableStatement cs = null;
		try {
			//Connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.225.8.96:1521:ICIST6", "lyodba", "lyo");
			
			BaseBlock baseBlock = new BaseBlock();
			baseBlock.setStart(1);
			baseBlock.setEnd(5);
			baseBlock.setOrderbyClause("dt_cr desc");
			baseBlock.setWhereClause("body_nb = :1 and acct_nb = :2 and nb = :3");
			BindObject bindObject1 = new BindObject();
			bindObject1.setType("int");
			bindObject1.setIntValue(2235196);
			BindObject bindObject2 = new BindObject();
			bindObject2.setType("int");
			bindObject2.setIntValue(1);
			BindObject bindObject3 = new BindObject();
			bindObject3.setType("int");
			bindObject3.setIntValue(45);
			baseBlock.setBindObjects(new BindObject[] {bindObject1, bindObject2, bindObject3});
			baseBlock.setupArrays((OracleConnection)connection);
			
			//Mapping necessary types 
			Map<String, Class<?>> typeMaps = connection.getTypeMap(); 
			typeMaps.put(BaseBlock.ORACLE_OBJECT_NAME, BaseBlock.class);
			typeMaps.put(BindObject.ORACLE_OBJECT_NAME, BindObject.class);
			typeMaps.put(PayeTtransc.ORACLE_OBJECT_NAME, PayeTtransc.class);
			
			cs = connection.prepareCall("{call pkg_page_paye.ttransc_post_query(?,?)}");
			cs.setObject(1, baseBlock);
			cs.registerOutParameter(1, OracleTypes.STRUCT, BaseBlock.ORACLE_OBJECT_NAME);
			cs.registerOutParameter(2, OracleTypes.ARRAY, "ARR_PAYE_TTRANSC");
			
			cs.execute();
			BaseBlock returnBaseBlock = (BaseBlock)cs.getObject(1);
			
			if (returnBaseBlock.getState().equals("OK")) {
				Object[] objects = (Object[])cs.getArray(2).getArray();
				for (Object object : objects) {
					PayeTtransc pt = (PayeTtransc)object;
					System.out.println(pt.toString());
				}
			}
			else {
				System.out.println("msg=" + returnBaseBlock.getMsg());
			}
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
