package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestExecutionInfo;

/**
 * @author qiupeng
 *
 */
public interface TestExecutionMapper {
	
	int insert(TestExecutionInfo TestExecutionInfo);

	TestExecutionInfo selectByExecutionId(@Param("execution_id") int execution_id);

	List<TestExecutionInfo> selectAll();
	
	int updateById(TestExecutionInfo TestExecutionInfo);
	
	int updateBySql(@Param(value="sqlStr") String sqlStr);
}
