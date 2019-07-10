package com.uusoft.atp.model;

public class TestCaseInfo {
	/**
     * 方法id
     */
	private int method_id;
	/**
     * 用例id
     */
	private int case_id;
	/**
     * 用例描述
     */
	private String case_des;
	/**
     * 用例数据（json格式）
     */
	private String case_data;
	/**
     * 用例断言类型
     */
	private int case_assert_type;
	/**
     * 用例断言的值
     */
	private String case_assert_value;
	/**
     * 是否执行
     */
	private Integer is_run;
	/**
     * 是否删除
     */
	private String is_del;
	
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
	public String getCase_des() {
		return case_des;
	}
	public void setCase_des(String case_des) {
		this.case_des = case_des;
	}
	public String getCase_data() {
		return case_data;
	}
	public void setCase_data(String case_data) {
		this.case_data = case_data;
	}
	public int getCase_assert_type() {
		return case_assert_type;
	}
	public void setCase_assert_type(int case_assert_type) {
		this.case_assert_type = case_assert_type;
	}
	public String getCase_assert_value() {
		return case_assert_value;
	}
	public void setCase_assert_value(String case_assert_value) {
		this.case_assert_value = case_assert_value;
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
	
}
