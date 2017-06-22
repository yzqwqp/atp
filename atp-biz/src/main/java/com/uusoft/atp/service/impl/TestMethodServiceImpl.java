package com.uusoft.atp.service.impl;

import java.util.ArrayList;
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
import com.uusoft.atp.utils.ResultTool;

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
		int serviceId = serviceMapper.selectSeviceIdByName(testMethodInfo.getService_name());
		testMethodInfo.setService_id(serviceId);
		LOGGER.info("testMethodInfo getService_id is :" +testMethodInfo.getService_id());
		LOGGER.info("testMethodInfo getMethod_name is :" +testMethodInfo.getMethod_name());
		LOGGER.info("testMethodInfo getMethod_des is :" +testMethodInfo.getMethod_des());
		LOGGER.info("testMethodInfo getIs_run is :" +testMethodInfo.getIs_run());
		return mapper.insert(testMethodInfo);
	}

	@Override
	public List<TestMethodInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public ResultTool<TestMethodInfo> selectById(int method_id) {
		info = mapper.selectById(method_id);
		LOGGER.info("id: ["+info.getService_id()+"] name: ["+info.getMethod_name()+"] des: ["+info.getMethod_des()+"] isrun: ["+info.getIs_run());
		return ResultTool.setResult("0000", "查询成功", info);
	}
	
	public int deleteById(int method_id){
		return mapper.deleteById(method_id);
	}

	@Override
	public int updateById(TestMethodInfo testMethodInfo) {
		return mapper.updateById(testMethodInfo);
	}
	
	@Override
	public List<TestMethodInfo> selectByServiceId(int service_id) {
		List<TestMethodInfo> info = mapper.selectByServiceId(service_id);
		return info;
	}

	@Override
	public ResultTool<List<String>> unCreateMethod(String service_name) {
		return ResultTool.setResult("0000", "查询成功", mapper.unCreateMethod(service_name));
	}

	@Override
	public ResultTool<List<String>> createdMethod(String service_name) {
		int serviceId = serviceMapper.selectSeviceIdByName(service_name);
		List<TestMethodInfo> info = mapper.selectByServiceId(serviceId);
		List<String> methods = new ArrayList<String>();
		for (TestMethodInfo t:info) {
			methods.add(t.getMethod_name());
		}
		return ResultTool.setResult("0000", "查询成功", methods);
	}

	@Override
	public ResultTool<TestMethodInfo> selectByServiceNameAndMethodName(String service_name, String method_name) {
		info = mapper.selectByServiceNameAndMethodName(service_name, method_name);
		return ResultTool.setResult("0000", "查询成功", info);
	}

}
