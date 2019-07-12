package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestMethodInfo;


public interface TestMethodMapper {
	
	int insert(TestMethodInfo testMethodInfo);
	
	TestMethodInfo selectByMethodId(@Param("method_id") int method_id);

	List<TestMethodInfo> selectByServiceId(@Param("service_id") int service_id);
	
	List<TestMethodInfo> selectAll();
	
	int updateById(TestMethodInfo testMethodInfo);
	
	int deleteById(@Param("method_id") int method_id);
	
}
