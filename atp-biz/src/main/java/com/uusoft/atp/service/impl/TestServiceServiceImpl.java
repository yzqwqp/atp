package com.uusoft.atp.service.impl;
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

@Service("TestServiceService")
@Transactional
public class TestServiceServiceImpl implements TestServiceService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestServiceServiceImpl.class);
	
	@Resource
	private TestServiceMapper mapper;

	@Transactional
	@Override
	public int insert(TestServiceInfo testServiceInfo) {
		return mapper.insert(testServiceInfo);
	}

	@Override
	public TestServiceInfo selectByServiceId(int service_id) {
		return mapper.selectByServiceId(service_id);
	}
	
	@Override
	public List<TestServiceInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateById(TestServiceInfo testServiceInfo) {
		return mapper.updateById(testServiceInfo);
	}
	
	@Override
	public int deleteById(int service_id) {
		return mapper.deleteById(service_id);
	}

}
