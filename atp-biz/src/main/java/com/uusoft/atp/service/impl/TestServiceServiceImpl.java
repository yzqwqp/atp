package com.uusoft.atp.service.impl;
import java.util.ArrayList;
/** 
* 类说明 ：
* 	TestServiceService实现类
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2016年12月13日 上午10:19:12 
*/
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.TestServiceMapper;
import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.utils.ResultTool;

@Service("TestServiceService")
@Transactional
public class TestServiceServiceImpl implements TestServiceService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestServiceServiceImpl.class);
	
	@Resource
	private TestServiceMapper mapper;

	@Transactional
	@Override
	public int insert(TestServiceInfo testServiceInfo) {
		LOGGER.info("testServiceInfo getService_name is :" +testServiceInfo.getService_name());
		LOGGER.info("testServiceInfo getService_des is :" +testServiceInfo.getService_des());
		return mapper.insert(testServiceInfo);
	}

	@Override
	public List<TestServiceInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public ResultTool<TestServiceInfo> selectById(int service_id) {
		TestServiceInfo info = mapper.selectById(service_id);
		LOGGER.info("id: ["+info.getService_id()+"] name: ["+info.getService_name()+"] des: ["+info.getService_des()+"]  isdel: ["+info.getIs_del());
		return ResultTool.setResult("0000", "查询成功", info);
	}

	@Override
	public int updateById(TestServiceInfo testServiceInfo) {
		return mapper.updateById(testServiceInfo);
	}
	
	@Override
	public int deleteById(int service_id) {
		return mapper.deleteById(service_id);
	}

	@Override
	public ResultTool<List<String>> selectUnCreateService() {
		List<String> info = mapper.selectUnCreateService();
		return ResultTool.setResult("0000", "查询成功", info);
	}

	@Override
	public ResultTool<List<String>> selectCreateService() {
		List<TestServiceInfo> info = mapper.selectAll();
		List<String> strList = new ArrayList<String>();
		for (TestServiceInfo t : info) {
			strList.add(t.getService_name());
		}
		return ResultTool.setResult("0000", "查询成功", strList);
	}

	@Override
	public int selectSeviceIdByName(String service_name) {
		return mapper.selectSeviceIdByName(service_name);
	}

}
