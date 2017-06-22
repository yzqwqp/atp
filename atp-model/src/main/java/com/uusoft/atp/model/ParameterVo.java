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

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
public class ParameterVo implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	String[] paramTypes;
	Object[]  paramValues;
	int paramSize;
	/** 
	 * @return paramTypes 
	 */
	public String[] getParamTypes() {
		return paramTypes;
	}
	/**
	 * @param paramTypes the paramTypes to set
	 */
	public void setParamTypes(String[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	/** 
	 * @return paramValues 
	 */
	public Object[] getParamValues() {
		return paramValues;
	}
	/**
	 * @param paramValues the paramValues to set
	 */
	public void setParamValues(Object[] paramValues) {
		this.paramValues = paramValues;
	}
	/** 
	 * @return paramSize 
	 */
	public int getParamSize() {
		return paramTypes.length;
	}
	/**
	 * @param paramSize the paramSize to set
	 */
	public void setParamSize(int paramSize) {
		this.paramSize = paramSize;
	}
	
	

}
