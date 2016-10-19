package com.ngx20080110.objinobj;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class RetMsg implements SQLData {
	public static final String ORACLE_OBJECT_NAME = "RETMSG";

	private String rcd;
	private String msg;
	private String backTrace;
	public String getRcd() {
		return rcd;
	}
	public void setRcd(String rcd) {
		this.rcd = rcd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBackTrace() {
		return backTrace;
	}
	public void setBackTrace(String backTrace) {
		this.backTrace = backTrace;
	}
	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setRcd(stream.readString());
		setMsg(stream.readString());
		setBackTrace(stream.readString());
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(getRcd());
		stream.writeString(getMsg());
		stream.writeString(getBackTrace());
	}
	@Override
	public String toString() {
		return "RetMsg [rcd=" + rcd + ", msg=" + msg + ", backTrace=" + backTrace + "]";
	}
}
