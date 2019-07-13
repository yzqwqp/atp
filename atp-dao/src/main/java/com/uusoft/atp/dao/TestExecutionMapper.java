package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestExecutionInfo;

/**
 * @author qiupeng
 *
 */
public interface TestExecutionMapper {
	
	long insert(TestExecutionInfo TestExecutionInfo);

	TestExecutionInfo selectByExecutionId(@Param("execution_id") int execution_id);

	List<TestExecutionInfo> selectAll();
}
