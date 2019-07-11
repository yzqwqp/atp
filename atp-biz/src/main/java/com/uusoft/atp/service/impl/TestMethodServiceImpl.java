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
		LOGGER.info(testMethodInfo.toString());
		return mapper.insert(testMethodInfo);
	}

	@Override
	public List<TestMethodInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<TestMethodInfo> selectByMethodId(int method_id) {
		List<TestMethodInfo> linfo = new ArrayList<TestMethodInfo>();
		info = mapper.selectByMethodId(method_id);
		linfo.add(info);
		LOGGER.info("id: ["+info.getMethod_id()+"] name: ["+info.getMethod_name()+"] des: ["+info.getMethod_des()+"] ");
		return linfo;
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
	public ResultTool<List<TestMethodInfo>> createdMethod(int serviceId) {
		List<TestMethodInfo> info = mapper.selectByServiceId(serviceId);
		return ResultTool.setResult("0000", "查询成功", info);
	}
	
	@Override
	public ResultTool<List<String>> unCreateMethod(String service_name) {
		return ResultTool.setResult("0000", "查询成功", mapper.unCreateMethod(service_name));
	}

	@Override
	public ResultTool<TestMethodInfo> selectByServiceNameAndMethodName(String service_name, String method_name) {
		info = mapper.selectByServiceNameAndMethodName(service_name, method_name);
		return ResultTool.setResult("0000", "查询成功", info);
	}

	@Override
	public List<TestMethodInfo> selectMethodsByServiceId(int service_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
