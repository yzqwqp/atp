package com.uusoft.atp.model;

public class TestCaseVo {
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
	private String case_assert_type;
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
	public String getCase_assert_type() {
		return case_assert_type;
	}
	public void setCase_assert_type(String case_assert_type) {
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
	
}
