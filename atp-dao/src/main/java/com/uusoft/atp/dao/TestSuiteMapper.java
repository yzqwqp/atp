package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestSuiteInfo;

/**
 * @author qiupeng
 *
 */
public interface TestSuiteMapper {
	
	int insert(TestSuiteInfo testSuiteInfo);

	TestSuiteInfo selectBySuiteId(@Param("suite_id") int suite_id);

	List<TestSuiteInfo> selectByMethodId(@Param("method_id")int method_id);
	
	List<TestSuiteInfo> selectByServiceId(@Param("service_id")int service_id);
	
	List<TestSuiteInfo> selectAll();
	
	int update(TestSuiteInfo testSuiteInfo);
	
	int deleteById(@Param("suite_id") int suite_id);
	
	List<TestSuiteInfo> selectCanRunByMethodId(@Param("method_id")int method_id);
}
