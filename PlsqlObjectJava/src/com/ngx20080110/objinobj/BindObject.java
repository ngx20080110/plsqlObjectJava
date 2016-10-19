package com.ngx20080110.objinobj;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

public class BindObject implements SQLData{
	public static final String ORACLE_OBJECT_NAME = "BIND_OBJECT";
	
	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setType(stream.readString());
		setIntValue(stream.readInt());
		setDecimalValue(stream.readDouble());
		setStringValue(stream.readString());
		setDateValue(stream.readDate());
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(type);
		stream.writeInt(intValue);
		stream.writeDouble(decimalValue);
		stream.writeString(stringValue);
		stream.writeDate(dateValue != null ? new java.sql.Date(dateValue.getTime()) : null);
	}
	private String type;
	private int intValue;
	private double decimalValue;
	private String stringValue;
	private Date dateValue;

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public double getDecimalValue() {
		return decimalValue;
	}
	public void setDecimalValue(double decimalValue) {
		this.decimalValue = decimalValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public Date getDateValue() {
		return dateValue;
	}
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	@Override
	public String toString() {
		return "BindObject [type=" + type + ", intValue=" + intValue + ", decimalValue=" + decimalValue
				+ ", stringValue=" + stringValue + ", dateValue=" + dateValue + "]";
	}
}
