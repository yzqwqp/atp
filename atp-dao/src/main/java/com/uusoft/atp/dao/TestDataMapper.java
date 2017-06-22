package com.uusoft.atp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestDataInfo;


public interface TestDataMapper {
	
	int insert(TestDataInfo testDataInfo);
	
	int insertInitTestData(@Param("case_id") int case_id, @Param("service_name") String service_name, @Param("method_name") String method_name);
	
	List<TestDataInfo> selectAll();
	
	List<TestDataInfo> selectById(@Param("case_id") int case_id);
	
	int updateById(TestDataInfo testDataInfo);
	
	List<TestDataInfo> selectCaseValue(@Param("case_id") int case_id);
	
	int updateMap(@Param("dataMap")Map<Integer, String> dataMap);
}
