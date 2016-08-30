package test;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

public class TestTableObject implements SQLData {
	public static final String ORACLE_OBJECT_NAME = "OBJ_TEST";
	
	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		ANumber(stream.readDouble());
		AChar(stream.readString());
		AVarchar2(stream.readString());
		ANVarchar2(stream.readNString());
		ADate(stream.readDate());
		AClob(stream.readClob());
		ABlob(stream.readBlob());
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeDouble(ANumber());
		stream.writeString(AChar());
		stream.writeString(AVarchar2());
		stream.writeNString(ANVarchar2());
		stream.writeDate(ADate() != null ? new java.sql.Date(ADate().getTime()) : null);
		stream.writeClob(AClob());
		stream.writeBlob(ABlob());
	}
	
	private String aChar;
	private String aVarchar2;
	private String aNVarchar2;
	private Date aDate;
	private Blob aBlob;
	private double aNumber;
	private Clob aClob;

	public String AChar() {
		return aChar;
	}
	public void AChar(String aChar) {
		this.aChar = aChar;
	}
	public String AVarchar2() {
		return aVarchar2;
	}
	public void AVarchar2(String aVarchar2) {
		this.aVarchar2 = aVarchar2;
	}
	public String ANVarchar2() {
		return aNVarchar2;
	}
	public void ANVarchar2(String aNVarchar2) {
		this.aNVarchar2 = aNVarchar2;
	}
	public Date ADate() {
		return aDate;
	}
	public void ADate(Date aDate) {
		this.aDate = aDate;
	}
	public Blob ABlob() {
		return aBlob;
	}
	public void ABlob(Blob aBlob) {
		this.aBlob = aBlob;
	}
	public double ANumber() {
		return aNumber;
	}
	public void ANumber(double aNumber) {
		this.aNumber = aNumber;
	}
	public Clob AClob() {
		return aClob;
	}
	public void AClob(Clob aClob) {
		this.aClob = aClob;
	}
}
