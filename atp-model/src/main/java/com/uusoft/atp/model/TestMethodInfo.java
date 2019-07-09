package com.uusoft.atp.model;

public class TestMethodInfo {
	/**
     * 服务id
     */
	private int service_id;
	/**
     * 服务name
     */
	private String service_name;
	/**
     * 方法id
     */
	private int method_id;
	/**
     * 方法名称
     */
	private String method_name;
	/**
     * 方法描述
     */
	private String method_des;
	/**
     * 是否执行
     */
	private Integer is_run;
	/**
     * 是否删除
     */
	private String is_del;
	
	
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public int getMethod_id() {
		return method_id;
	}
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	public String getMethod_des() {
		return method_des;
	}
	public void setMethod_des(String method_des) {
		this.method_des = method_des;
	}
	public Integer getIs_run() {
		return is_run;
	}
	public void setIs_run(Integer is_run) {
		this.is_run = is_run;
	}
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	@Override
	public String toString() {
		return "{service_id=" + service_id + ", service_name=" + service_name + ", method_id=" + method_id + ", method_name=" + method_name + ", method_des=" + method_des + ", is_run=" + is_run + ", is_del=" + is_del + "}";
	}
	
}
