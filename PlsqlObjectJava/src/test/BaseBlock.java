package test;

import java.sql.Array;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import oracle.jdbc.OracleConnection;


public class BaseBlock implements SQLData {
	public static final String ORACLE_OBJECT_NAME = "BASE_BLOCK";
	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setStart(stream.readInt());
		setEnd(stream.readInt());
		setState(stream.readString());
		setMsg(stream.readString());
		setOrderbyClause(stream.readString());
		setWhereClause(stream.readString());
		
		Object[] objects = (Object[])stream.readArray().getArray();
		BindObject[] bindObjects = new BindObject[objects.length];
		for (int i = 0; i < objects.length; i++) {
			bindObjects[i] = (BindObject)objects[i];
		}
		setBindObjects(bindObjects);
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeInt(start);
		stream.writeInt(end);
		stream.writeString(state);
		stream.writeString(msg);
		stream.writeString(orderbyClause);
		stream.writeString(whereClause);
		stream.writeArray(bindObjectsArray);
	}
	
	public void setupArrays(OracleConnection oconn) throws SQLException {
		bindObjectsArray = oconn.createOracleArray("BIND_OBJECT_ARRAY", bindObjects);
	}

	private int start;
	private int end;
	private String state;
	private String msg;
	private String orderbyClause;
	private String whereClause;
	private BindObject[] bindObjects;
	private Array bindObjectsArray;
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public BindObject[] getBindObjects() {
		return bindObjects;
	}
	public void setBindObjects(BindObject[] bindObjects) {
		this.bindObjects = bindObjects;
	}
	public Array getBindObjectsArray() {
		return bindObjectsArray;
	}
	public void setBindObjectsArray(Array bindObjectsArray) {
		this.bindObjectsArray = bindObjectsArray;
	}
}
