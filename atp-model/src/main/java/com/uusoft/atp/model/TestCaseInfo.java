package com.uusoft.atp.model;

public class TestCaseInfo {
	/**
     * 用例id
     */
	private int case_id;
	/**
     * 用例组id
     */
	private int suite_id;
	/**
     * 方法地址
     */
	private String method_address;
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
     * 执行前处理
     */
	private int before_run;
	/**
     * 执行后处理
     */
	private int after_run;
	/**
     * 用例执行顺序 从  [before_run开始]-0-1-3-4
     */
	private int case_run_num;
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
	public int getIs_del() {
		return is_del;
	}
	public void setIs_del(int is_del) {
		this.is_del = is_del;
	}
	public String getMethod_address() {
		return method_address;
	}
	public void setMethod_address(String method_address) {
		this.method_address = method_address;
	}
	public int getBefore_run() {
		return before_run;
	}
	public void setBefore_run(int before_run) {
		this.before_run = before_run;
	}
	public int getAfter_run() {
		return after_run;
	}
	public void setAfter_run(int after_run) {
		this.after_run = after_run;
	}
	public int getCase_run_num() {
		return case_run_num;
	}
	public void setCase_run_num(int case_run_num) {
		this.case_run_num = case_run_num;
	}
	@Override
	public String toString() {
		return "TestCaseInfo : {case_id=" + case_id +", suite_id=" + suite_id +", method_address=" + method_address + ", case_des=" + case_des + ", case_data=" + case_data + ", case_assert_type=" + case_assert_type + ", case_assert_value=" + case_assert_value + ", is_del=" + is_del + ", case_assert_value=" + case_assert_value + "}";
	}
}
