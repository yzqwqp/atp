package com.uusoft.atp.utils;

public class HttpUtilsTest {
	public static void main(String[] args) throws Exception
	{
	String url = "https://test-wx.wbtech.com/tma/carrier/login";//请求接口地址，以实际地址为准
	String test = "postdata={\"deviceInfo\":\"{\\\"androidRelease\\\":\\\"8.0.0\\\",\\\"phoneModel\\\":\\\"SM-G9350\\\",\\\"sdkVersion\\\":\\\"26\\\",\\\"verCode\\\":\\\"186\\\",\\\"verName\\\":\\\"3.3.0.1\\\"}\",\"ifPush\":\"1\",\"locationLat\":1.0,\"locationLng\":1.0,\"loginName\":\"qiuguoyuan\",\"password\":\"111111\",\"Lat\":31.34254,\"Lng\":121.500701,\"globalFromType\":\"app\",\"globalLoginType\":\"APP\",\"globalPlatformId\":\"SX*HSY*0001\",\"globalRoleType\":\"CYR\",\"globalUserIp\":\"192.168.188.105\",\"globalUserPart\":\"ZH\",\"globalUserType\":\"GR\",\"imeiCode\":\"356156070893136\"}";
	
	
	HttpUtils.sendHttpMsg(url, test);
	}
}
