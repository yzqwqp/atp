package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.InitFieldInfo;

public interface InitFieldMapper {
	
	int insert(InitFieldInfo testServiceInfo);
	
	List<InitFieldInfo> selectAll();
	
	List<InitFieldInfo> selectById(@Param("para_id") int para_id);
	
	int updateById(InitFieldInfo InitFieldInfo);
	
	int deledteinitfield();
}
