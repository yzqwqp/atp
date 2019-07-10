package com.uusoft.atp.model;

public class TestResultInfo {
	/**
     * 执行id
     */
	private int test_id;
	/**
     * 用例id
     */
	private int case_id;
	/**
     * 用例描述
     */
	private String case_des;
	/**
     * 方法地址
     */
	private String method_address;
	/**
     * 用例数据（json格式）
     */
	private String case_data;
	/**
     * 执行结果
     */
	private String response_data;
	/**
     * 用例断言类型
     */
	private int case_assert_type;
	/**
     * 用例断言的值
     */
	private String case_assert_value;
	/**
     * HTTP请求状态
     */
	private int http_status;
	/**
     * 断言结果
     */
	private int assert_status;
	/**
     * HTTP请求状态异常信息
     */
	private String http_error;
	/**
     * 断言状态异常信息
     */
	private String assert_error;
	
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
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
	public String getMethod_address() {
		return method_address;
	}
	public void setMethod_address(String method_address) {
		this.method_address = method_address;
	}
	public String getCase_data() {
		return case_data;
	}
	public void setCase_data(String case_data) {
		this.case_data = case_data;
	}
	public String getResponse_data() {
		return response_data;
	}
	public void setResponse_data(String response_data) {
		this.response_data = response_data;
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
	public int getHttp_status() {
		return http_status;
	}
	public void setHttp_status(int http_status) {
		this.http_status = http_status;
	}
	public int getAssert_status() {
		return assert_status;
	}
	public void setAssert_status(int assert_status) {
		this.assert_status = assert_status;
	}
	public String getHttp_error() {
		return http_error;
	}
	public void setHttp_error(String http_error) {
		this.http_error = http_error;
	}
	public String getAssert_error() {
		return assert_error;
	}
	public void setAssert_error(String assert_error) {
		this.assert_error = assert_error;
	}
	
	
	
	
}
