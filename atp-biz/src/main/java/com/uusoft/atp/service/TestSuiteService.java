package com.uusoft.atp.service;
import java.util.List;

import com.uusoft.atp.model.TestSuiteInfo;

public interface TestSuiteService {
	
	int insert(TestSuiteInfo testSuiteInfo);
	List<TestSuiteInfo> selectByMethodId(int method_id);
	List<TestSuiteInfo> selectByServiceId(int service_id);
	List<TestSuiteInfo> selectAll();
	
}
