package com.ngx20080110.objinh;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class Teacher extends Person implements SQLData{
	public static final String ORACLE_OBJECT_NAME = "TEACHER_OBJ";
	
	private String course;

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		super.readSQL(stream, typeName);
		setCourse(stream.readString());
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		super.writeSQL(stream);
		stream.writeString(getCourse());
	}

	@Override
	public String toString() {
		return "Teacher [course=" + course + ", " + super.toString() + "]";
	}
}
