package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestCaseInfo;

/**
 * @author qiupeng
 *
 */
public interface TestCaseMapper {
	
	int insert(TestCaseInfo testCaseInfo);
	
	TestCaseInfo selectByCaseId(@Param("case_id") int case_id);
	
	List<TestCaseInfo> selectBySuiteId(@Param("suite_id")int suite_id);
	
	List<TestCaseInfo> selectByMethodId(@Param("method_id")int method_id);
	
	List<TestCaseInfo> selectByServiceId(@Param("service_id")int service_id);
	
	List<TestCaseInfo> selectAll();
	
	int update(TestCaseInfo testCaseInfo);
	
	int deleteById(@Param("case_id") int case_id);
	
}
