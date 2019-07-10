package com.uusoft.atp.service.impl;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.TestResultMapper;
import com.uusoft.atp.model.TestResultInfo;
import com.uusoft.atp.service.TestResultService;


@Service("TestCaseService")
@Transactional
public class TestResultServiceImpl implements TestResultService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestResultServiceImpl.class);
	
	@Resource
	TestResultMapper mapper;
	
	@Override
	public int insert(TestResultInfo testResultInfo) {
		return mapper.insert(testResultInfo);
	}


}
