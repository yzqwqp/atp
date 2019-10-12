package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestResultInfo;

/**
 * @author qiupeng
 *
 */
public interface TestResultMapper {
	
	int insert(TestResultInfo testResultInfo);
	
	List<TestResultInfo> selectByExecutionId(@Param("execution_id") int execution_id);

	List<TestResultInfo> selectFailureResultByExecutionId(@Param("execution_id") int execution_id);
	
	List<TestResultInfo> selectUnrunResultByExecutionId(@Param("execution_id") int execution_id);
	
}
