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
     * 是否执行
     */
	private Integer is_run;
	/**
     * 是否删除
     */
	private String is_del;
	
	public String toString(){
		return "test";
	}
	public String getIs_del() {
		return is_del;
	}
	public void setIs_del(String is_del) {
		this.is_del = is_del;
	}
	public Integer getIs_run() {
		return is_run;
	}
	public void setIs_run(Integer is_run) {
		this.is_run = is_run;
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
	
}
