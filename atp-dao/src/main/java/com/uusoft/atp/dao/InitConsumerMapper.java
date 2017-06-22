package com.uusoft.atp.dao;

import java.util.List;

import com.uusoft.atp.model.InitConsumerInfo;

public interface InitConsumerMapper {
	
	int insert(InitConsumerInfo initConsumerInfo);
	
	List<InitConsumerInfo> selectAll();
		
	int updateById(InitConsumerInfo initConsumerInfo);
}
