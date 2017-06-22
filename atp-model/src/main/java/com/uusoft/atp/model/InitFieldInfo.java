package com.uusoft.atp.model;

public class InitFieldInfo {
	/**
     * 参数id
     */
	private int para_id;
	/**
     * bean属性序列
     */
	private int field_no;
	/**
     * 属性名字
     */
	private String field_name;
	/**
     * 属性类型
     */
	private String field_type;
	
	public int getPara_id() {
		return para_id;
	}
	public void setPara_id(int para_id) {
		this.para_id = para_id;
	}

	public int getField_no() {
		return field_no;
	}
	public void setField_no(int field_no) {
		this.field_no = field_no;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getField_type() {
		return field_type;
	}
	public void setField_type(String field_type) {
		this.field_type = field_type;
	}
	
	
}
