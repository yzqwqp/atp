package com.uusoft.atp.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.utils.ResultTool;

public interface TestCaseService {
	
	int insert(TestCaseInfo testCaseInfo);
	
	List<TestCaseInfo> selectAll();
	
	ResultTool<TestCaseInfo> selectById(int case_id);
	
	int update(TestCaseInfo testCaseInfo);
	
	int deleteById(int case_id);
	
	Object run(int case_id);
	
	String getDataById(int caseId);
	
	List<LinkedHashMap<String, String>> selectParasByMethod(String service,String method);
	
	List<String>  getDatasByMethod(String method);
	
	List<TestCaseInfo> selectByMethodId(int method_id);
}
