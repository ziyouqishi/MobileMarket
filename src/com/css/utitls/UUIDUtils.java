package com.css.utitls;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * �������ID
	 * @return
	 */
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static String getCode(){
		return getId();
	}

}
