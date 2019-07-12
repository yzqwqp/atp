package com.uusoft.atp.service;

import java.util.List;

import com.uusoft.atp.model.TestServiceInfo;

public interface TestServiceService {
	
	int insert(TestServiceInfo testServiceInfo);

	TestServiceInfo selectByServiceId(int service_id);
	
	List<TestServiceInfo> selectAll();
	
	int updateById(TestServiceInfo testServiceInfo);
	
	int deleteById(int service_id);

}
