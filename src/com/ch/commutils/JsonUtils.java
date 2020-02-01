package com.ch.commutils;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonUtils {
	
	public static <T> String ObjectToJson(Object object){
		String str = null;
		try {
			Gson gson = new Gson();
			str = gson.toJson(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	public static <T> List<T> getResultFromJsonString(String jsonString, Type type) {
		Gson g = new Gson();
		List<T> result = null;
		try {
			result = g.fromJson(jsonString, type);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
