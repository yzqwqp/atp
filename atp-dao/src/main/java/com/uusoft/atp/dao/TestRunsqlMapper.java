package com.uusoft.atp.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author qiupeng
 *
 */
public interface TestRunsqlMapper {
	
	Integer updateBySql(@Param(value="sqlStr") String sqlStr);
}
