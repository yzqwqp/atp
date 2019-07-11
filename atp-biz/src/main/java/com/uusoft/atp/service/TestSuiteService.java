package com.uusoft.atp.service;
import java.util.List;

import com.uusoft.atp.model.TestSuiteInfo;

public interface TestSuiteService {
	
	/**
	 * 插入一条suite用例组
	 */
	int insert(TestSuiteInfo testSuiteInfo);
	/**
	 * suite_id查单条suite用例组
	 */
	TestSuiteInfo selectBySuiteId(int suite_id);
	/**
	 * 查所有method_id下的suite用例组
	 */
	List<TestSuiteInfo> selectByMethodId(int method_id);
	/**
	 * 查所有service_id下的suite用例组
	 */
	List<TestSuiteInfo> selectByServiceId(int service_id);
	/**
	 * 查所有suite用例组
	 */
	List<TestSuiteInfo> selectAll();
	
}
