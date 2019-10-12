package com.uusoft.atp.service;
import java.util.List;

import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.model.TestResultInfo;
import com.uusoft.atp.utils.ResultTool;

public interface TestExecutionService {
	
	/**
	 * APP INTERFACE AUTOMATION TEST MANAGEMENT AND PRACTICE
	 * execution_type : 1：testSuite测试用例 2：testMethod测试集 3：testService测试服务
	 * execution_type_value : testSuite测试用例的ID / testMethod测试集的ID / testService测试服务的ID
	 * execution_type_name : testSuite测试用例ID描述 / testMethod测试集ID描述 / testService测试服务ID描述
	 * */
	ResultTool<TestExecutionInfo> execution(int execution_type, int execution_type_value, String execution_type_name);
	
	TestExecutionInfo selectByExecutionId(int execution_id);
	
	List<TestExecutionInfo> selectAll();
	
	List<TestResultInfo> selectResultByExecutionId(int execution_id);
	
	List<TestResultInfo> selectFailureResultByExecutionId(int execution_id);

	List<TestResultInfo> selectUnrunResultByExecutionId(int execution_id);
}
