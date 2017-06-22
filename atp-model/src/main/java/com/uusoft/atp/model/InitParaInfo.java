package com.uusoft.atp.model;

public class InitParaInfo {
	/**
     * 参数id
     */
	private int para_id;
	/**
     * 服务id
     */
	private int method_id;
	/**
     * 参数名字
     */
	private String para_name;
	/**
     * 参数类型
     */
	private String para_type;
	
	public int getPara_id() {
		return para_id;
	}
	public void setPara_id(int para_id) {
		this.para_id = para_id;
	}
	
	
	public int getMethod_id() {
		return method_id;
	}
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
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
	
}
