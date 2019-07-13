package com.uusoft.atp.service;
import com.uusoft.atp.utils.ResultTool;

public interface TestExecutionService {
	
	/**
	 * 执行一条用例，包含多个步骤
	 */
	ResultTool<String> execution(int execution_type, int execution_type_value, String execution_type_name);
	
}
