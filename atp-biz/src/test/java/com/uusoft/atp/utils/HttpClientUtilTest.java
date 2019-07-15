package com.uusoft.atp.utils;

import static org.junit.Assert.*;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.uusoft.atp.httptest.utils.SSLClient;

public class HttpClientUtilTest {

	@Test
	public void test() throws Exception {
		String urlStr = "https://www.easy-mock.com/mock/5d1c5578722b2036875672b0/example/i2dbank/C19SingleUnionpaySttInq.API";
		String jsonStr = "{ \"data\":{ \"flowNo\":\"9920190703220600xDdmha5yObQbQFhn\" }, \"resultCode\":\"00000001\", \"resultDesc\":\"交易已受理\" }";
		String charSet = "utf-8";
		
		String responseStr =  doPost(urlStr,jsonStr,charSet);
		
		System.out.println(responseStr);
		
	}
	/*
	http://10.0.160.215:9980/v1/login
	{
		  "authType": 0,
		  "deviceInfo": {
		    "brand": "iphone",
		    "cameraAuthorized": true,
		    "language": "en",
		    "latitude": 51.8474,
		    "locationAuthorized": true,
		    "longitude": 127.3987,
		    "model": "xr",
		    "platform": "ios",
		    "sdkVersion": 27,
		    "verCode": 107,
		    "verName": "2.0.7",
		    "version": "10.3.1"
		  },
		  "jsCode": "wx.jscode",
		  "password": "111111",
		  "userId": 0,
		  "username": "admin"
		}
	*/
	
	@SuppressWarnings("resource")
    public static String doPost(String url,String jsonstr,String charset) throws Exception{
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            StringEntity se = new StringEntity(jsonstr);
            se.setContentType("text/json");
            se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                Header header = resEntity.getContentEncoding();
                if (header != null) {
            		HeaderElement[] codecs = header.getElements();
            		for (int i = 0; i < codecs.length; i++) {
            			if (codecs[i].getName().equalsIgnoreCase("gzip")) {
            			response.setEntity(new GzipDecompressingEntity(resEntity));
            			}
            		}
            	}
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            throw ex;
        }
        return result;
    }
	
	
	
}
