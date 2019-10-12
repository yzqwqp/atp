package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.TestConstantInfo;

/**
 * @author qiupeng
 *
 */
public interface TestConstantMapper {
	
	int insert(TestConstantInfo testConstantInfo);
	
	int update(TestConstantInfo testConstantInfo);
	
	List<TestConstantInfo> selectAll();
	
	TestConstantInfo selectByConstantName(@Param("constant_name") String constant_name);

	TestConstantInfo selectByConstantId(@Param("constant_id") int constant_id);
}
