package com.uusoft.atp.utils;

import java.util.UUID;

/**
 * 常量类
 * @author qiupeng
 *
 */
public class ConstantUtils {
	
	public static final String REDIS_KEYSPILT 	= ":";
	public static final String REDIS_INSTID 	= "LT0000001";
	public static final String REDIS_TYPE_REGISTER 	= "1";
	public static final String REDIS_TYPE_SETPWD 	= "2";
	public static final String REDIS_TYPE_FINDPWD 	= "3";
	
	
	 public static String generateSerialno()
	    {
	        return UUID.randomUUID().toString().replace("-", "");
	    }

}
