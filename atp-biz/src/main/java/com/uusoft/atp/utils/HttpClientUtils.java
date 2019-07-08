package com.uusoft.atp.utils;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtils {
	private static final  Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

	private String host;
	private int port;
	private ThreadLocal<CloseableHttpClient> httpClient;
	/**
	 *
	 * @param host 服务器地址
	 * @param port 服务器端口号
	 * @param key
	 * @param pfxPath
	 * @param pfxPwd
	 *
	 */
	public HttpClientUtils(String host, int port) {
		super();
		this.host = host;
		this.port = port;
		httpClient=new ThreadLocal<CloseableHttpClient>(){

			@Override
			protected CloseableHttpClient initialValue() {
				CloseableHttpClient client = null;
				client = getHttpsClient();
				return client;
			}
		};
	}

	public JSONObject post(String servletPath,String params) throws HttpException, IOException{
		LOGGER.info("------------------------------------------------------------------------\n" +
				"host:" + this.host + "\n" +
				"port:" + this.port + "\n" +
				"params：" + JSON.toJSONString(JSON.parseObject(params), true) + "\n" +
				"------------------------------------------------------------------------");
		CloseableHttpClient client = null;
		try {
			HttpPost post = new HttpPost( servletPath );
			StringEntity requestEntity = new StringEntity(params,"UTF-8");
			post.setEntity(requestEntity);
			//请求头
//			String u=String.format("%1$s---%2$s", params,key);
//			String [] flag=SignUtil.signData(URLEncoder.encode(u,"UTF-8"), pfxPath, pfxPwd);
//			logger.info("------------------------------------------------------------------------\n" +
//				"channels:" + channels + "\n" +
//				"signData:" + flag[0] + "\n" +
//				"signCert:" + flag[1] + "\n" +
//				"------------------------------------------------------------------------");
//			post.setHeader("channels", channels);
//			post.setHeader("signData", flag[0]);
//			post.setHeader("signCert", flag[1]);

			HttpHost httpHost = new HttpHost(host, port, "https");
			Long beforeSendTime = System.currentTimeMillis();
			LOGGER.info("before send time: " + beforeSendTime);

			client = getHttpsClient();
			CloseableHttpResponse httpResponse = client.execute(httpHost, post);
			Long afterReceiveTime = System.currentTimeMillis();
			LOGGER.info(("after receive time: " + afterReceiveTime));
			LOGGER.info("#################### deal spend time: " + (afterReceiveTime - beforeSendTime) + " ####################");

			JSONObject jsonObject = null;
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = httpResponse.getEntity();
				String ret = EntityUtils.toString(entity, "UTF-8");
				LOGGER.info(ret);
				jsonObject = JSON.parseObject(ret);
				LOGGER.info("***********************************************");
				LOGGER.info("return:"+JSON.toJSONString(jsonObject, true));
				LOGGER.info("***********************************************");
				EntityUtils.consume(entity);// 消耗响应实体，并关闭相关资源占用
			} else {
				throw new RuntimeException("服务器错误:"+EntityUtils.toString(httpResponse.getEntity(), "UTF-8"));
			}

			if (httpResponse != null) httpResponse.close();
			return jsonObject;
		} finally {
			if (client != null) {
				client.close();
			}
		}
	}

	public static CloseableHttpClient getHttpsClient() {
		CloseableHttpClient httpClient = null;
		SSLContext context;
		try {
			context = SSLContext.getInstance("SSL");
			context.init(null, new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

			} }, new SecureRandom());

			HostnameVerifier verifier = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(context, verifier);
			httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpClient;
	}

//	public void setChannels(String channels) {
//		this.channels = channels;
//	}
}
