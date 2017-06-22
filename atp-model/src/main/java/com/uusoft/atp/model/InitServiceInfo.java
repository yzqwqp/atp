package com.uusoft.atp.model;

public class InitServiceInfo {
	/**
     * 初始化服务id
     */
	private int method_id;
	/**
     * 服务名称
     */
	private String service_name;
	/**
     * 服务描述
     */
	private String method_name;
	
	
	public int getMethod_id() {
		return method_id;
	}
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	
}
