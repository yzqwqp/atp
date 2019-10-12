package com.uusoft.atp.model;

import java.util.Date;

public class TestExecutionInfo {
	/**
     * 执行id
     */
	private int execution_id;
	/**
     * 执行类型
     */
	private int execution_type;
	/**
     * 执行类型的名称
     */
	private String execution_type_name;
	/**
     * 执行的[服务ID]/[测试集ID][测试组ID][测试用例ID]
     */
	private int execution_type_value;
	/**
     * 执行开始时间
     */
	private Date execution_start_time;
	/**
     * 执行结束时间
     */
	private Date execution_end_time;
	/**
     * 执行耗时
     */
	private int execution_time;
	/**
     * 执行总数
     */
	private int total_num;
	/**
     * 执行成功数量
     */
	private int true_num;
	/**
     * 执行失败数量
     */
	private int failure_num;
	/**
     * 未执行数量
     */
	private int unrun_num;
	/**
     * 执行类型的名称
     */
	private String execution_detail;
	
	@Override
	public String toString() {
		return "TestExecutionInfo : {execution_id=" + execution_id +", execution_type=" + execution_type +", execution_type_name=" + execution_type_name + ", execution_type_value=" + execution_type_value + ", execution_start_time=" + execution_start_time + ", execution_end_time=" + execution_end_time + ", total_num=" + total_num  + ", true_num=" + true_num  + ", failure_num=" + failure_num  + ", unrun_num=" + unrun_num  + ", execution_time=" + execution_time  + ", 11=" + 11 +"}";
	}
	public int getExecution_id() {
		return execution_id;
	}
	public void setExecution_id(int execution_id) {
		this.execution_id = execution_id;
	}
	public int getExecution_type() {
		return execution_type;
	}
	public void setExecution_type(int execution_type) {
		this.execution_type = execution_type;
	}
	public int getExecution_type_value() {
		return execution_type_value;
	}
	public void setExecution_type_value(int execution_type_value) {
		this.execution_type_value = execution_type_value;
	}
	public Date getExecution_start_time() {
		return execution_start_time;
	}
	public void setExecution_start_time(Date execution_start_time) {
		this.execution_start_time = execution_start_time;
	}
	public Date getExecution_end_time() {
		return execution_end_time;
	}
	public void setExecution_end_time(Date execution_end_time) {
		this.execution_end_time = execution_end_time;
	}
	public int getTotal_num() {
		return total_num;
	}
	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}
	public int getTrue_num() {
		return true_num;
	}
	public void setTrue_num(int true_num) {
		this.true_num = true_num;
	}
	public int getFailure_num() {
		return failure_num;
	}
	public void setFailure_num(int failure_num) {
		this.failure_num = failure_num;
	}
	public int getUnrun_num() {
		return unrun_num;
	}
	public void setUnrun_num(int unrun_num) {
		this.unrun_num = unrun_num;
	}
	public int getExecution_time() {
		return execution_time;
	}
	public void setExecution_time(int execution_time) {
		this.execution_time = execution_time;
	}
	public String getExecution_type_name() {
		return execution_type_name;
	}
	public void setExecution_type_name(String execution_type_name) {
		this.execution_type_name = execution_type_name;
	}
	public String getExecution_detail() {
		return execution_detail;
	}
	public void setExecution_detail(String execution_detail) {
		this.execution_detail = execution_detail;
	}

}
