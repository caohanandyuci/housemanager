package com.ch.commutils;

import java.util.UUID;

public class Utils {

	public static String createUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
