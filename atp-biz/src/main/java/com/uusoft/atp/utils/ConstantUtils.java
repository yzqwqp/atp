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
	
	//================= demo begin ===================//
	//================= 项目用到的所有常量数值请在此定义===================//
	public static final String INTERFACE_VERSION = "1.0";
	public static final String CHARSET = "UTF-8";
	public static final String CHARSET_GBK = "GBK";
	public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
	public static final String SCHEMAL_JSON = "json";
	public static final String REGOIN = "zh_cn";
	
	public static final String FILTER_CHARS = "~!@#$%^&*()-_+=?<>':;\\/|";
	public static final String KJ_USERID = "kj_userid";
	public static final String KJ_PWD = "kj_pwd";
	public static final String KJ_CUSTOMS_CODE = "kj_customs_code";
	public static final String KJ_ORG_NAME = "kj_org_name";
	public static final String KJ_URL = "kj_url";
	public static final String REPORT_URL = "report.url";
	
	
	//================= Http Proxy ===================//
	public static final String PROXY_ENABLED = "proxyEnabled";
	public static final String PROXY_HOST = "proxyHost";
	public static final String PROXY_PORT = "proxyPort";
	public static final String PROXY_ENABLED_VALUE = "1";
	
    public static final String PRI_KEY = ".PRK";
    public static final String PUB_KEY = ".PUK";
    //================= demo end ===================//
    
    
	 public static String generateSerialno()
	    {
	        return UUID.randomUUID().toString().replace("-", "");
	    }

}
