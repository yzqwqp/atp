package com.uusoft.atp.httptest.utils;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XmlRootElement(name = "Message")
@XStreamAlias("Message")
public class MessageReq implements Serializable
{
    private static final long serialVersionUID = 62866123327326986L;
    private String requestBody;
	private String id;
	private int version;
	private String instId;
	private String transTime;
	private String extension;
	private String sstoken;
	private String businType;
	private String signature;
	private Object bodyType;
    private static Logger logger = Logger.getLogger(MessageReq.class);

    public String getRequestBody()
    {
        return requestBody;
    }

    @XmlElement
    public void setRequestBody(String requestBody)
    {
        this.requestBody = requestBody;
    }

    public MessageReq(String id, int version, String instId, String transTime,
        String extension, String sstoken, String businType,
        String requestBody, String signature)
    {

        this.id = id;
        this.version = version;
        this.instId = instId;
        this.transTime = transTime;
        this.extension = extension;
        this.sstoken = sstoken;
        this.businType = businType;
        this.requestBody = requestBody;
        this.signature = signature;

    }

    public MessageReq()
    {
    }

   /**
    * 
    * 方法描述：
    *    获取消息体对象
    * @param bodyType
    * @return
    * @author 周辉
    */
    @SuppressWarnings("unchecked")
    public <T> T getBody(Class<T> bodyType)
    {
		if (bodyType == null)
        {
            // 将数据反序列化为对象
            XStream xs = new XStream();
            xs.autodetectAnnotations(true);
            xs.alias("Request", bodyType);
            this.bodyType = xs.fromXML(this.requestBody);
        }
        return (T) this.bodyType;
    }

    /**
     * 
     * 方法描述：
     *    解析XML文件，反序列化为对象
     * @param xml
     * @return
     * @author 周辉
     */
    public static MessageReq fromXml(String xml)
    {
        XStream xs = new XStream();
        xs.autodetectAnnotations(true);
        xs.alias("Message", MessageReq.class);
        return (MessageReq) xs.fromXML(xml);
    }

    /**
     * 
     * 方法描述：
     *    解析json文件，反序列化为对象
     * @param xml
     * @return
     * @author 周辉
     */
    public static MessageReq fromJson(String xml)
    {

        MessageReq obj = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            String ss = xml.substring(11, xml.length() - 1);
            obj = mapper.readValue(ss, MessageReq.class);
        }
        catch (Exception e)
        {
            logger.error(e);
        }

        return (MessageReq) obj;
    }
}
