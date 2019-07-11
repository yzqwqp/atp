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
	
	List<TestSuiteInfo> selectAll();
	/**
	 * 用于查询case对应的methodName,serviceName等信息
	 * @author qiupeng
	 */
	TestSuiteInfo selectBySuiteId(@Param("suite_id") int suite_id);

	List<TestSuiteInfo> selectByMethodId(@Param("method_id")int method_id);
	
	List<TestSuiteInfo> selectByServiceId(@Param("service_id")int service_id);
}
