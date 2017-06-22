package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestMethodInfo;


public interface TestMethodMapper {
	
	int insert(TestMethodInfo testMethodInfo);
	
	List<TestMethodInfo> selectAll();
	
	TestMethodInfo selectById(@Param("method_id") int method_id);
	
	TestMethodInfo selectByServiceNameAndMethodName(@Param("service_name") String service_name, @Param("method_name") String method_name);
	
	int updateById(TestMethodInfo testMethodInfo);
	
	int selectMethodIdByNameAndService(@Param("methodName") String methodName,@Param("serviceId") int serviceId);

	int selectMethodIdByName(@Param("method")String method);

	List<TestMethodInfo> selectByServiceId(@Param("service_id") int service_id);
	
	int deleteById(@Param("method_id") int method_id);
	
	List<String> unCreateMethod(@Param("service_name")String service_name);
}
