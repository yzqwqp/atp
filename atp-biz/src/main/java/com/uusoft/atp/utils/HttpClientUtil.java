package com.uusoft.atp.utils;

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

import com.uusoft.atp.httptest.utils.SSLClient;
/**
 * 利用HttpClient进行post请求的工具类
 * @ClassName: HttpClientUtil 
 * @author qiupeng
 */
public class HttpClientUtil {
    @SuppressWarnings("resource")
    public static String doPost2(String url,String jsonstr,String charset) throws Exception{
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
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            throw ex;
        }
        return result;
    }
    
    
    @SuppressWarnings("resource")
    public static String doPost(String url,String jsonstr,String tokenStr) throws Exception{
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            if (!tokenStr.isEmpty()) {
            	httpPost.addHeader("Cookie", tokenStr);
            }
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
                    result = EntityUtils.toString(resEntity,"utf-8");
                }
            }
        }catch(Exception ex){
            throw ex;
        }
        return result;
    }
}