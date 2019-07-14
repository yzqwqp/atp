package com.uusoft.atp.service;
import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.utils.ResultTool;

public interface TestExecutionService {
	
	/**
	 * 执行一条用例，包含多个步骤
	 */
	ResultTool<TestExecutionInfo> execution(int execution_type, int execution_type_value, String execution_type_name);
	
}
