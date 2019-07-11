package com.uusoft.atp.model;

import java.io.Serializable;

public class TestServiceInfo implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 服务id
     */
	private int service_id;
	/**
     * 服务名称
     */
	private String service_name;
	/**
     * 服务描述
     */
	private String service_des;
	/**
     * 是否删除
     */
	private int is_del;
	
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public int getService_id() {
		return service_id;
	}
	public void setService_id(int service_id) {
		this.service_id = service_id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getService_des() {
		return service_des;
	}
	public void setService_des(String service_des) {
		this.service_des = service_des;
	}
	@Override
	public String toString() {
		return "TestServiceInfo ： {service_id=" + service_id + ", service_name=" + service_name + ", service_des=" + service_des + ", is_del=" + is_del + "}";
	}
}
