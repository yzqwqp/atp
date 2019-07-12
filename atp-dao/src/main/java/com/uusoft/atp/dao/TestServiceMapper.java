package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestServiceInfo;

public interface TestServiceMapper {
	
	int insert(TestServiceInfo testServiceInfo);
	
	TestServiceInfo selectByServiceId(@Param("service_id") int service_id);

	List<TestServiceInfo> selectAll();
	
	int updateById(TestServiceInfo testServiceInfo);
	
	int deleteById(@Param("service_id") int service_id);
	
}
