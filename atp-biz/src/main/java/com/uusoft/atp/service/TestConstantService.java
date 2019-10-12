package com.uusoft.atp.service;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestConstantInfo;
import com.uusoft.atp.utils.ResultTool;

public interface TestConstantService {
	
	/**
	 * 插入一条常量数据
	 */
	int insert(TestConstantInfo testConstantInfo);
	/**
	 * 更新一条常量数据
	 */
	int update(TestConstantInfo testConstantInfo);
	/**
	 * 查所有常量
	 */
	List<TestConstantInfo> selectAll();
	/**
	 * 按constant_id查询常量实体
	 */
	TestConstantInfo selectByConstantId(int constant_id);
	/**
	 * 按constant_name查询常量实体
	 */
	TestConstantInfo selectByConstantName(String constant_name);
	/**
	 * 按TestCaseInfo中的before_run_type=2替换json数据（before_run=constant_id)
	 */
	TestCaseInfo mofiyCaseInfo(TestCaseInfo testCaseInfo);
	/**
	 * 按TestCaseInfo中的after_run_type=2替换constant数据进行业务变量替换 （after_run=constant_id)
	 */
	ResultTool<String> mofiyConstantInfoAfterRun(TestCaseInfo testCaseInfo, JSONObject jsonObj);
	/**
	 * 按TestCaseInfo中的before_run_type=3替换SQL数据（before_run=constant_id)
	 */
	ResultTool<TestCaseInfo> mofiySqlCaseInfo(TestCaseInfo testCaseInfo);
}
