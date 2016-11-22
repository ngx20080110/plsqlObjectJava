package com.ngx20080110.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	public static String object2String(Object o) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.toJson(o);
	}
	
	public static <T> Object string2Object(String s, Class<T> clazz) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(s, clazz);
	}
}