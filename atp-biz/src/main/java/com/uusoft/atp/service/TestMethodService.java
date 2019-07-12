package com.uusoft.atp.service;

import java.util.List;

import com.uusoft.atp.model.TestMethodInfo;

public interface TestMethodService {
	
	int insert(TestMethodInfo testMethodInfo);
	
	TestMethodInfo selectByMethodId(int method_id);

	List<TestMethodInfo> selectAll();
	
	int updateById(TestMethodInfo testMethodInfo);
	
	int deleteById(int method_id);
	
	List<TestMethodInfo> selectByServiceId(int service_id);
}
