package com.uusoft.atp.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.TestSuiteMapper;
import com.uusoft.atp.model.TestSuiteInfo;
import com.uusoft.atp.service.TestSuiteService;


@Service("TestSuiteService")
@Transactional
public class TestSuiteServiceImpl implements TestSuiteService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestSuiteServiceImpl.class);
	
	@Resource
	TestSuiteMapper mapper;
	
	@Override
	public int insert(TestSuiteInfo testSuiteInfo) {
		return mapper.insert(testSuiteInfo);
	}

	@Override
	public List<TestSuiteInfo> selectByMethodId(int method_id) {
		return mapper.selectByMethodId(method_id);
	}

	@Override
	public List<TestSuiteInfo> selectByServiceId(int service_id) {
		return mapper.selectByServiceId(service_id);
	}

	@Override
	public List<TestSuiteInfo> selectAll() {
		return mapper.selectAll();
	}


}
