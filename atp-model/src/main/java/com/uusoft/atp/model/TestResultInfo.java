package com.uusoft.atp.model;

import java.util.Date;

public class TestResultInfo {
	/**
     * 执行结果id
     */
	private int result_id;
	/**
     * 执行id
     */
	private int execution_id;
	/**
     * 用例组ID
     */
	private int suite_id;
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
     * 请求返回断言实际值
     */
	private String response_assert_value;
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
	/**
     * 创建时间
     */
	private Date create_date;
	/**
     * token
     */
	private int tokenFlag;
	/**
     * token
     */
	private String token;
	
	@Override
	public String toString() {
		return "TestResultInfo : {result_id=" + result_id + ", execution_id=" + execution_id + ", suite_id=" + suite_id + ", case_id=" + case_id + ", case_des=" + case_des + ", method_address=" + method_address + ", case_data=" + case_data + ", response_data=" + response_data + ", case_assert_type=" + case_assert_type + ", case_assert_value=" + case_assert_value + ", response_assert_value=" + response_assert_value + ", http_status=" + http_status+ ", assert_status=" + assert_status+ ", http_error=" + http_error+ ", assert_error=" + assert_error+ ", create_date=" + create_date+ ", 11=" + 11 +"}";
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
	public String getResponse_assert_value() {
		return response_assert_value;
	}
	public void setResponse_assert_value(String response_assert_value) {
		this.response_assert_value = response_assert_value;
	}
	public int getResult_id() {
		return result_id;
	}
	public void setResult_id(int result_id) {
		this.result_id = result_id;
	}
	public int getExecution_id() {
		return execution_id;
	}
	public void setExecution_id(int execution_id) {
		this.execution_id = execution_id;
	}
	public int getSuite_id() {
		return suite_id;
	}
	public void setSuite_id(int suite_id) {
		this.suite_id = suite_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getTokenFlag() {
		return tokenFlag;
	}

	public void setTokenFlag(int tokenFlag) {
		this.tokenFlag = tokenFlag;
	}
	
}
