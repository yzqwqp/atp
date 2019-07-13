package com.uusoft.atp.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.TestResultMapper;
import com.uusoft.atp.model.TestResultInfo;
import com.uusoft.atp.service.TestResultService;


@Service("TestResultService")
@Transactional
public class TestResultServiceImpl implements TestResultService {
	
	@Resource
	TestResultMapper mapper;
	
	@Override
	public int insert(TestResultInfo testResultInfo) {
		return mapper.insert(testResultInfo);
	}


}
