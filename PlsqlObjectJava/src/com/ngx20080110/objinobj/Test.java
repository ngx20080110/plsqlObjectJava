package com.ngx20080110.objinobj;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

public class Test {

	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cs = null;
		try {
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:lyodba/lyo@10.225.8.96:1521:ICIST6");
			conn = ods.getConnection();
			
			BaseBlock2 baseBlock = new BaseBlock2();
			baseBlock.setRowStart(1);
			baseBlock.setRowEnd(5);
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
			baseBlock.setupArrays((OracleConnection)conn);
			
			//Mapping necessary types 
			Map<String, Class<?>> typeMaps = conn.getTypeMap(); 
			typeMaps.put(BaseBlock2.ORACLE_OBJECT_NAME, BaseBlock2.class);
			typeMaps.put(BindObject.ORACLE_OBJECT_NAME, BindObject.class);
			typeMaps.put(RetMsg.ORACLE_OBJECT_NAME, RetMsg.class);
			
			cs = conn.prepareCall("{call test20161019(?)}");
			cs.setObject(1, baseBlock);
			cs.registerOutParameter(1, OracleTypes.STRUCT, BaseBlock2.ORACLE_OBJECT_NAME);
			
			cs.execute();
			BaseBlock2 returnBaseBlock = (BaseBlock2)cs.getObject(1);
			System.out.println("RetMsg=" + returnBaseBlock.getRm().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (cs != null) {
					cs.close();
				}
				if (conn != null) {
						conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
