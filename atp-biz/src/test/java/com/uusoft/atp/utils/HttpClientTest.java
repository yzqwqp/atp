package com.uusoft.atp.utils;

import org.junit.Test;

public class HttpClientTest {
	
//	private static final  Logger LOGGER = LoggerFactory.getLogger(HttpClientTest.class);

	@SuppressWarnings("unused")
	//@Test
	public void test() throws Exception {
		String url = "https://test-wx.wbtech.com/tma/carrier/login";
        String jsonStr = "postdata={\"deviceInfo\":\"{\\\"androidRelease\\\":\\\"8.0.0\\\",\\\"phoneModel\\\":\\\"SM-G9350\\\",\\\"sdkVersion\\\":\\\"26\\\",\\\"verCode\\\":\\\"186\\\",\\\"verName\\\":\\\"3.3.0.1\\\"}\",\"ifPush\":\"1\",\"locationLat\":1.0,\"locationLng\":1.0,\"loginName\":\"qiuguoyuan\",\"password\":\"111111\",\"Lat\":31.34254,\"Lng\":121.500701,\"globalFromType\":\"app\",\"globalLoginType\":\"APP\",\"globalPlatformId\":\"SX*HSY*0001\",\"globalRoleType\":\"CYR\",\"globalUserIp\":\"192.168.188.105\",\"globalUserPart\":\"ZH\",\"globalUserType\":\"GR\",\"imeiCode\":\"356156070893136\"}";
        String httpOrgCreateTestRtn = HttpClientUtil.doPost(url, jsonStr, "utf-8");
//        LOGGER.info(httpOrgCreateTestRtn);
        System.out.println(httpOrgCreateTestRtn);
	}
	
	@Test
	public void test1() {
		
		String url = "http://10.0.160.215:9980/v1/login";
//		String jsonStr = "{			\"authType\": 0,			\"deviceInfo\": {				\"brand\": \"iphone\",				\"cameraAuthorized\": true,				\"language\": \"en\",				\"latitude\": 51.8474,				\"locationAuthorized\": true,				\"longitude\": 127.3987,				\"model\": \"xr\",				\"platform\": \"ios\",				\"sdkVersion\": 27,				\"verCode\": 107,				\"verName\": \"2.0.7\",				\"version\": \"10.3.1\"			},			\"jsCode\": \"wx.jscode\",			\"password\": \"111111\",			\"userId\": 0,			\"username\": \"18252726081\"		}";
		String jsonStr = "{			 \"authType\": \"0\",			 \"password\": \"111111\",			 \"username\": \"18252726081\"			 }			}";
		//		String url = "https://www.easy-mock.com/mock/5d1c5578722b2036875672b0/example/i2dbank/C19SingleUnionpaySttInq.API";
//        String jsonStr = "{\"bindCardNo\":\"6212260510002532792\",\"custName\":\"张旭聪\",\"idNo\":\"142623198901123718\",\"elementNo\":\"3\"}";
        String httpOrgCreateTestRtn;
		try {
			httpOrgCreateTestRtn = HttpClientUtil.doPost(url, jsonStr, "");
			System.out.println(httpOrgCreateTestRtn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        LOGGER.info(httpOrgCreateTestRtn);
        
	}
	

}
