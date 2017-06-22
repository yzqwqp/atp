package com.uusoft.atp.model;

public class TestDataInfo {
	/**
     * 用例id
     */
	private int case_id;
	/**
     * 数据id
     */
	private int data_id;
	/**
     * 入参名称
     */
	private String para_name;
	/**
     * 入参类型
     */
	private String para_type;
	/**
     * 属性名称
     */
	private String field_name;
	/**
     * 属性类型
     */
	private String field_type;
	/**
	 * 数据值
	 */
	private String value_data;
	
	public int getCase_id() {
		return case_id;
	}
	public void setCase_id(int case_id) {
		this.case_id = case_id;
	}
	public int getData_id() {
		return data_id;
	}
	public void setData_id(int data_id) {
		this.data_id = data_id;
	}
	public String getPara_name() {
		return para_name;
	}
	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}
	public String getPara_type() {
		return para_type;
	}
	public void setPara_type(String para_type) {
		this.para_type = para_type;
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
	public String getValue_data() {
		return value_data;
	}
	public void setValue_data(String value_data) {
		this.value_data = value_data;
	}
	
}
