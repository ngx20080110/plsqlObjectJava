package com.ngx20080110.objinobj;

import java.sql.Array;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import oracle.jdbc.OracleConnection;

public class BaseBlock2 implements SQLData {
	public static final String ORACLE_OBJECT_NAME = "BASE_BLOCK2";
	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setRowStart(stream.readInt());
		setRowEnd(stream.readInt());
		setOrderbyClause(stream.readString());
		setWhereClause(stream.readString());
		
		Object[] objects = (Object[])stream.readArray().getArray();
		BindObject[] bindObjects = new BindObject[objects.length];
		for (int i = 0; i < objects.length; i++) {
			bindObjects[i] = (BindObject)objects[i];
		}
		setBindObjects(bindObjects);
		
		setTotal(stream.readInt());
		setUsername(stream.readString());
		setRm((RetMsg)stream.readObject());
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeInt(rowStart);
		stream.writeInt(rowEnd);
		stream.writeString(orderbyClause);
		stream.writeString(whereClause);
		stream.writeArray(bindObjectsArray);
		stream.writeInt(total);
		stream.writeString(username);
		stream.writeObject(rm);
	}
	
	public void setupArrays(OracleConnection oconn) throws SQLException {
		bindObjectsArray = oconn.createOracleArray("BIND_OBJECT_ARRAY", bindObjects);
	}

	private int rowStart;
	private int rowEnd;
	private String orderbyClause;
	private String whereClause;
	private BindObject[] bindObjects;
	private int total;
	private String username;
	private RetMsg rm;
	private Array bindObjectsArray;
	public int getRowStart() {
		return rowStart;
	}
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}
	public int getRowEnd() {
		return rowEnd;
	}
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}
	public String getOrderbyClause() {
		return orderbyClause;
	}
	public void setOrderbyClause(String orderbyClause) {
		this.orderbyClause = orderbyClause;
	}
	public String getWhereClause() {
		return whereClause;
	}
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RetMsg getRm() {
		return rm;
	}
	public void setRm(RetMsg rm) {
		this.rm = rm;
	}
	public BindObject[] getBindObjects() {
		return bindObjects;
	}
	public void setBindObjects(BindObject[] bindObjects) {
		this.bindObjects = bindObjects;
	}
	
}
