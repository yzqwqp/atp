package com.uusoft.atp.model;

public class TestMethodInfo {
	/**
	 * 测试集id
	 */
	private int method_id;
	/**
     * 服务id
     */
	private int service_id;
	/**
     * 测试集名称
     */
	private String method_name;
	/**
     * 测试集描述
     */
	private String method_des;
	/**
     * 是否删除
     */
	private int is_del;
	
	
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
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	@Override
	public String toString() {
		return "TestMethodInfo ：{service_id=" + service_id +  ", method_id=" + method_id + ", method_name=" + method_name + ", method_des=" + method_des + ", is_run=" +", is_del=" + is_del + "}";
	}
	
}
