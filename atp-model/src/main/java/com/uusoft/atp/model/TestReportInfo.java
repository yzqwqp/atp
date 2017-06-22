package com.uusoft.atp.model;

import java.io.Serializable;

public class TestReportInfo implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 测试结果id
     */
	private int report_id;
	/**
     * 服务id
     */
	private int service_id;
	/**
     * 方法id
     */
	private int method_id;
	/**
     * 用例id
     */
	private int case_id;
	/**
     * 数据id
     */
	private int data_id;
	/**
     * 测试结果
     */
	private String report_data;
	
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
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
	public int getCase_id() {
		return case_id;
	}
	public void setCase_id(int case_id) {
		this.case_id = case_id;
	}
	public String getReport_data() {
		return report_data;
	}
	public void setReport_data(String report_data) {
		this.report_data = report_data;
	}
	public int getData_id() {
		return data_id;
	}
	public void setData_id(int data_id) {
		this.data_id = data_id;
	}
	
	
}
