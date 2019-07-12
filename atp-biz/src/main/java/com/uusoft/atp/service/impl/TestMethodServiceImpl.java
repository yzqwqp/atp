package com.uusoft.atp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.TestMethodMapper;
import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.service.TestMethodService;
import com.uusoft.atp.service.TestServiceService;

@Service("TestMethodService")
@Transactional
public class TestMethodServiceImpl implements TestMethodService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestMethodServiceImpl.class);
	
	@Resource
	TestMethodMapper mapper;
	
	@Resource
	TestServiceService serviceMapper;
	
	TestMethodInfo info;
	
	@Override
	public int insert(TestMethodInfo testMethodInfo) {
		LOGGER.info(testMethodInfo.toString());
		return mapper.insert(testMethodInfo);
	}

	@Override
	public TestMethodInfo selectByMethodId(int method_id) {
		return mapper.selectByMethodId(method_id);
	}
	
	@Override
	public List<TestMethodInfo> selectByServiceId(int service_id) {
		List<TestMethodInfo> info = mapper.selectByServiceId(service_id);
		return info;
	}
	
	@Override
	public List<TestMethodInfo> selectAll() {
		LOGGER.info("### TestMethodServiceImpl selectAll begin ###");
		return mapper.selectAll();
	}

	@Override
	public int deleteById(int method_id){
		return mapper.deleteById(method_id);
	}

	@Override
	public int updateById(TestMethodInfo testMethodInfo) {
		return mapper.updateById(testMethodInfo);
	}

}
