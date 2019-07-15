package com.uusoft.atp.model;

import java.util.Date;

public class TestSuiteInfo {
	/**
     * 用例组id
     */
	private int suite_id;
	/**
     * 测试集id
     */
	private int method_id;
	/**
     * 用例组描述
     */
	private String suite_des;
	/**
     * 是否执行
     */
	private int is_run;
	/**
     * 是否删除
     */
	private int is_del;
	/**
     * 创建时间
     */
	private Date create_date;
	/**
     * 更新时间
     */
	private Date update_date;
	
	@Override
	public String toString() {
		return "TestSuiteInfo : {suite_id=" + suite_id +", method_id=" + method_id + ", suite_des=" + suite_des + ", is_run=" + is_run + ", is_del=" + is_del  +",create_date=" +create_date+ ",update_date=" +update_date+ "}";
	}
	
	public int getSuite_id() {
		return suite_id;
	}
	public void setSuite_id(int suite_id) {
		this.suite_id = suite_id;
	}
	public String getSuite_des() {
		return suite_des;
	}
	public void setSuite_des(String suite_des) {
		this.suite_des = suite_des;
	}
	public int getMethod_id() {
		return method_id;
	}
	public void setMethod_id(int method_id) {
		this.method_id = method_id;
	}
	public int getIs_run() {
		return is_run;
	}
	public void setIs_run(int is_run) {
		this.is_run = is_run;
	}
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
}
