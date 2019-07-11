package com.uusoft.atp.service;

import java.util.List;

import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.utils.ResultTool;

public interface TestMethodService {
	
	int insert(TestMethodInfo testMethodInfo);
	
	List<TestMethodInfo> selectByMethodId(int method_id);

	List<TestMethodInfo> selectAll();
	
	ResultTool<TestMethodInfo> selectByServiceNameAndMethodName(String service_name, String method_name);
	
	int updateById(TestMethodInfo testMethodInfo);
	
	int deleteById(int method_id);
	
	List<TestMethodInfo> selectByServiceId(int service_id);
	
	ResultTool<List<String>> unCreateMethod(String service_name);
	
	List<TestMethodInfo> selectMethodsByServiceId(int service_id);

	ResultTool<List<TestMethodInfo>> createdMethod(int serviceId);
	
	
	
}
