package com.ngx20080110.integer.entity;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Person implements SQLData {
	public static final String ORACLE_OBJECT_NAME = "OBJ_PERSON";

	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setId(stream.readInt());
		setAge(stream.readInt());
		setName(stream.readString());
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeInt(id);
		if (age == null) {
			stream.writeObject(null);
		}
		else {
			stream.writeInt(age);
		}
		stream.writeString(name);
	}
	private Integer id;
	private Integer age;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
