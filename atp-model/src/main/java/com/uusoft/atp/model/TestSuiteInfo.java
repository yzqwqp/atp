package com.uusoft.atp.model;

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
	@Override
	public String toString() {
		return "TestSuiteInfo : {suite_id=" + suite_id +", method_id=" + method_id + ", suite_des=" + suite_des + ", is_run=" + is_run + ", is_del=" + is_del + "}";
	}
}
