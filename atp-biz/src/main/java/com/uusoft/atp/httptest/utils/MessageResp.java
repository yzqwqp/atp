package com.uusoft.atp.httptest.utils;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 类描述： 
 *    返回数据 实体类
 * @author pc
 * @data 2014年10月14日
 */
@XmlRootElement(name = "Message")
@XStreamAlias("Message")
public class MessageResp  implements Serializable
{

    private static final long serialVersionUID = 1L;
    @XStreamOmitField
    private Logger logger = LoggerFactory.getLogger(MessageResp.class);

    private String responseBody; //请求参数集合,经过加密/封装
	private String id;
	

	private int version;
	private String instId;
	private String transTime;
	private String extension;
	private String sstoken;
	private String businType;
	private String signature;

    public enum DataFormatType  //数据格式
    {
        Xml, Json
    }; 
    public MessageResp(String id, int version, String instId, String transTime,
        String extension, String sstoken, String businType,
        String responseBody, String signature)
    {

        this.id = id;
        this.version = version;
        this.instId = instId;
        this.transTime = transTime;
        this.extension = extension;
        this.sstoken = sstoken;
        this.businType = businType;
        this.responseBody = responseBody;
        this.signature = signature;

    }

    public MessageResp()
    {
    }

    public String getResponseBody()
    {
        return responseBody;
    }

    @XmlElement
    public void setResponseBody(String responseBody)
    {
        this.responseBody = responseBody;
    }

    
    public static MessageResp getMessageResp(String id,int version,String instId,String businTpe)
    {
    	//(String id, int version, String instId, String transTime, String extension, String sstoken, String businType, String responseBody, String signature)
        return new MessageResp(id, 
            version, instId,"", "", "no-sstoken",businTpe, "", "");
    }
    
    public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getSstoken() {
		return sstoken;
	}

	public void setSstoken(String sstoken) {
		this.sstoken = sstoken;
	}

	public String getBusinType() {
		return businType;
	}

	public void setBusinType(String businType) {
		this.businType = businType;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
