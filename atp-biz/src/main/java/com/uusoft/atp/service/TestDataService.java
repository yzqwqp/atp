package com.uusoft.atp.service;

import java.util.List;

import com.uusoft.atp.model.TestDataInfo;
import com.uusoft.atp.utils.ResultTool;

public interface TestDataService {
	
	int insert(TestDataInfo testDataInfo);
	
	List<TestDataInfo> selectAll();
	
//	ResultTool<List<TestDataInfo>> selectById(int case_id);
	
	int updateById(TestDataInfo testDataInfo);
	
	List<TestDataInfo> selectCaseValue(int method_id);
	
	int updateMap(String json);

}
