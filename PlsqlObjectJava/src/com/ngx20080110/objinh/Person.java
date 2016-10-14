package com.ngx20080110.objinh;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Person implements SQLData{
	public static final String ORACLE_OBJECT_NAME = "PERSON_OBJ";
	
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setId(stream.readInt());
		setName(stream.readString());
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeInt(getId());
		stream.writeString(getName());
	}
}
