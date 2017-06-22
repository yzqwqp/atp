package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestCaseVo;

/**
 * @author qiupeng
 *
 */
public interface TestCaseMapper {
	
	int insert(TestCaseInfo testCaseInfo);
	
	List<TestCaseInfo> selectAll();
	
	TestCaseInfo selectById(@Param("case_id") int case_id);
	
	int updateById(TestCaseInfo testCaseInfo);
	
	List<String> selectDatasByMethodId(@Param("method_id")int method_id);
	
	String selectDataById(@Param("case_id")int case_id);
	
	int update(TestCaseInfo testCaseInfo);
	
	int deleteById(@Param("case_id") int case_id);
	
	/**
	 * 用于查询case对应的methodName,serviceName等信息
	 * @author qiupeng
	 */
	TestCaseVo selectByCaseId(@Param("case_id") int case_id);

	List<TestCaseInfo> selectByMethodId(@Param("method_id")int method_id);
}
