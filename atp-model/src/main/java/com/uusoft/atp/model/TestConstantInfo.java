package com.uusoft.atp.model;

import java.io.Serializable;
import java.util.Date;

public class TestConstantInfo implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 常量id
     */
	private int constant_id;
	/**
     * 常量名称
     */
	private String constant_name;
	/**
     * 常量值
     */
	private String constant_value;
	/**
     * 常量值的类型
     */
	private int constant_value_type;
	/**
     * 常量描述
     */
	private String constant_des;
	/**
     * 常量类型
     */
	private String constant_type;
	/**
     * 创建时间
     */
	private Date create_date;
	
	@Override
	public String toString() {
		return "TestServiceInfo ： {constant_id=" + constant_id + ", constant_name=" + constant_name + ", constant_value=" + constant_value + ", constant_value_type=" + constant_value_type +",create_date=" +create_date+ ",constant_des=" +constant_des+",constant_type="+constant_type+ "}";
	}
	
	public int getConstant_id() {
		return constant_id;
	}

	public void setConstant_id(int constant_id) {
		this.constant_id = constant_id;
	}

	public String getConstant_name() {
		return constant_name;
	}

	public void setConstant_name(String constant_name) {
		this.constant_name = constant_name;
	}

	public String getConstant_value() {
		return constant_value;
	}

	public void setConstant_value(String constant_value) {
		this.constant_value = constant_value;
	}

	public int getConstant_value_type() {
		return constant_value_type;
	}

	public void setConstant_value_type(int constant_value_type) {
		this.constant_value_type = constant_value_type;
	}

	public String getConstant_des() {
		return constant_des;
	}

	public void setConstant_des(String constant_des) {
		this.constant_des = constant_des;
	}

	public String getConstant_type() {
		return constant_type;
	}

	public void setConstant_type(String constant_type) {
		this.constant_type = constant_type;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
}
