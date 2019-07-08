package com.uusoft.atp.httptest.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.uusoft.atp.utils.ConstantUtils;

public class HttpRequest
{
	public static final Logger logger = LoggerFactory.getLogger(HttpRequest.class);
	public static String addUrl(String head, String tail)
	{
		if (head.endsWith("/"))
		{
			if (tail.startsWith("/"))
			{
				return head.substring(0, head.length() - 1) + tail;
			} else
			{
				return head + tail;
			}
		} else
		{
			if (tail.startsWith("/"))
			{
				return head + tail;
			} else
			{
				return head + "/" + tail;
			}
		}
	}

	public synchronized static String postData(String url,
			Map<String, String> params, String codePage) throws Exception
	{

		final HttpClient httpClient = new HttpClient();
		
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);

		final PostMethod method = new PostMethod(url);
		if (params != null)
		{
			method.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, codePage);
			method.setRequestBody(assembleRequestParams(params));
			
		}
		String result = "";
		try
		{
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(),codePage);
		} catch (final Exception e)
		{
			throw e;
		} finally
		{
			method.releaseConnection();
		}
		return result;
	}
	
	public synchronized static String postDataNew(String url,
			Map<String, String> params, String codePage) throws Exception
	{

		final HttpClient httpClient = new HttpClient();
		
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(30000);

		final PostMethod method = new PostMethod(url);
		if (params != null)
		{
			method.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, codePage);
			method.setRequestBody(assembleRequestParams(params));
			
		}
		String result = "";
		try
		{
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(),ConstantUtils.CHARSET_GBK);
		} catch (final Exception e)
		{
			throw e;
		} finally
		{
			method.releaseConnection();
		}
		return result;
	}
	
	public synchronized static String postData(String url,
			String data, String codePage,List<Header> headers) throws Exception
	{
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(20000);
		final PostMethod method = new PostMethod(url);
		if (data != null)
		{
			method.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, codePage);
			method.setRequestEntity(new ByteArrayRequestEntity(data.getBytes(codePage)));
			for(Header header : headers)
			{
				method.addRequestHeader(header);
			}
		}
		String result = "";
		try
		{
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(), codePage);
		} catch (final Exception e)
		{
			throw e;
		} finally
		{
			method.releaseConnection();
		}
		return result;
	}
	
	public synchronized static String postData(String url, String codePage)
			throws Exception
	{
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);

		final GetMethod method = new GetMethod(url);
		String result = "";
		try
		{
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(), codePage);
		} catch (final Exception e)
		{
			throw e;
		} finally
		{
			method.releaseConnection();
		}
		return result;
	}
	

	/**
	 * 组装http请求参数
	 * 
	 * @param params
	 * @param menthod
	 * @return
	 */
	private synchronized static NameValuePair[] assembleRequestParams(
			Map<String, String> data)
	{
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			nameValueList.add(new NameValuePair((String) entry.getKey(),
					(String) entry.getValue()));
		}
		return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
	}
	
	/**
	 * 组装http请求参数
	 * 
	 * @param params
	 * @param menthod
	 * @return
	 */
	public synchronized static List<NameValuePair> assembleRequestParamsList(
			Map<String, String> data)
	{
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			nameValueList.add(new NameValuePair((String) entry.getKey(),
					(String) entry.getValue()));
		}
		return nameValueList;
	}
	
	public synchronized static MessageResp postData(String urlStr, String data,String charset) throws Exception
    {
        StringBuilder lines = new StringBuilder();
        MessageResp messageResp = new MessageResp();
        try
        {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
//            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
//            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            logger.info("接口URL地址： " + urlStr);
            logger.info("请求报文：" + data);
            out.write(new String(data.getBytes(charset)));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine())
            {
                lines.append(line);
            }
            XStream xstream = new XStream(new DomDriver());
            xstream.processAnnotations(MessageResp.class);
            String InsitId = messageResp.getInstId();
            messageResp = (MessageResp) xstream.fromXML(lines.toString());
            logger.info("InsitId=" + InsitId);
        }
        catch (Exception e)
        {
            logger.error("接口调用异常：", e);
        }
        return messageResp;
    }

}
