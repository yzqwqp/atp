package com.uusoft.atp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  调用HttpURLConnection接口方法
 */
public class HttpUtils {
	
	private static final  Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);
	
    public static void main(String[] args) throws Exception
	{
    	String url = "http://10.0.161.2:8882/authorization/virOpenIdentifyCard";
    	String content = "{\"bindCardNo\":\"6212260510002532792\",\"custName\":\"张旭聪\",\"idNo\":\"142623198901123718\",\"elementNo\":\"3\"}";
//    	System.out.println("发送请求。url:{}, content:{}"+ url + content);
    	LOGGER.info("发送请求11。url:{}, content:{}", url, content);
    	HttpUtils.sendHttpMsg(url, content);
	}

    /**
     * 发送请求
     *
     * @param url url地址
     * @param content 参数
     * @return
     * @throws Exception
     */
    public static String sendHttpMsg(String url, String content) {
        LOGGER.info("发送请求。url:{}, content:{}", url, content);
        String result;
        PrintWriter pw = null;
        StringBuilder sb = new StringBuilder ();
        BufferedReader rd = null;
        HttpURLConnection conn = null;
        String line = null ;
        try {
            long start = System.currentTimeMillis();
            URL u = new URL(url);
            conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.connect();
            pw = new PrintWriter(conn.getOutputStream());
            pw.print(content);
            pw.flush();
            pw.close();

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            while ((line = rd.readLine()) != null ) {
                sb.append(line);
            }
            
            result = sb.toString();
//            System.out.println(result);
            long end = System.currentTimeMillis();
//            System.out.println("发送请求返回。"+result);
            LOGGER.info("发送请求返回。result:{}, 耗时(毫秒):{}", result, end - start);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("调用接口发送失败。url:{}, content:{}, {}", url, e.getMessage());
            throw new RuntimeException("调用接口发送请求失败");
        } finally {
            if (pw != null) {
                pw.close();
            }
            try {
                if (rd != null) {
                    rd.close();
                }
            } catch (IOException e) {
                LOGGER.error("", e.getMessage());
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return result;
    }
    
    
}
