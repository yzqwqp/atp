/**   
 * <p>Title: ParameterVo.java</p>
 * @Package com.uusoft.atp.model 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author Adele
 * @since 2016年12月21日 上午11:16:58 
 * @version V1.0   
 */
package com.uusoft.atp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
public class ParameterNameVo implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/***
	 * 出参方法和类型
	 */
	List<Map<String,String>> paramethod;
	/**
	 * 出参属性名称和类型
	 */
	List<Map<String,String>>  fieldname;
	public List<Map<String, String>> getParamethod() {
		return paramethod;
	}
	public void setParamethod(List<Map<String, String>> paramethod) {
		this.paramethod = paramethod;
	}
	public List<Map<String, String>> getFieldname() {
		return fieldname;
	}
	public void setFieldname(List<Map<String, String>> fieldname) {
		this.fieldname = fieldname;
	}

	
}
