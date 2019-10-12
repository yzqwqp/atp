package com.uusoft.atp.service;
import org.apache.ibatis.annotations.Param;

public interface TestRunsqlService {
	
	Integer updateBySql(@Param(value="sqlStr") String sqlStr);
}
