package com.uusoft.atp.service;

import java.util.List;

import com.uusoft.atp.model.TestCaseInfo;

public interface TestCaseService {
	
	int insert(TestCaseInfo testCaseInfo);
	
	TestCaseInfo selectByCaseId(int case_id);

	List<TestCaseInfo> selectBySuiteId(int suite_id);

	List<TestCaseInfo> selectByMethodId(int method_id);
	
	List<TestCaseInfo> selectByServiceId(int service_id);
	
	List<TestCaseInfo> selectAll();
	
	int update(TestCaseInfo testCaseInfo);
	
	int deleteById(int case_id);
	
//	List<LinkedHashMap<String, String>> selectParasByMethod(String service,String method);
}
