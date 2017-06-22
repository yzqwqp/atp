package com.uusoft.atp.model;

import java.io.Serializable;

public class TestDataVo implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 参数名称
	 */
	private String paraname;
	/**
	 * 参数类型
	 */
	private String paratype;
	/**
	 * 属性名称
	 */
	private String fieldname;
	/**
	 * 属性类型
	 */
	private String fieldtype;

	public String getParaname() {
		return paraname;
	}
	public void setParaname(String paraname) {
		this.paraname = paraname;
	}
	public String getParatype() {
		return paratype;
	}
	public void setParatype(String paratype) {
		this.paratype = paratype;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getFieldtype() {
		return fieldtype;
	}
	public void setFieldtype(String fieldtype) {
		this.fieldtype = fieldtype;
	}
	
}
