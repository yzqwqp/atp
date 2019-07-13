package com.uusoft.atp.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.dao.TestCaseMapper;
import com.uusoft.atp.dao.TestDataMapper;
import com.uusoft.atp.model.ParameterVo;
import com.uusoft.atp.model.TestDataInfo;

/** 
* 类说明 ：
* 
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2017年2月10日 下午3:30:15 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@Transactional
public class TestDataServiceImplTest2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataServiceImplTest2.class);

	@Resource
	private TestDataServiceImpl testDataImp;
	@Resource
	private TestDataMapper dataMapper;
	@Resource
	TestExecutionServiceImpl testExecutionService;
	
	@Test
	public void test(){
		ParameterVo vo = new ParameterVo();
		Integer case_id = 10;
		vo = testDataImp.parseTestData(case_id);
		LOGGER.info("#################");
		String[] para = vo.getParamTypes();
		Object[] value = vo.getParamValues();
		for (int i=0;i<para.length;i++){
			LOGGER.info(para[i]);
		}
		LOGGER.info("#################");
		for (int j=0;j<value.length;j++) {
			LOGGER.info(value[j].toString()+"  " + value[j].getClass());
		}
		LOGGER.info("#################");
//		JSON.parseObject(JSON.toJSONString(tmpMap), Map.class);
	}
	
	@Test
	public void test02(){
		LOGGER.info(testExecutionService.execution(1, 2, "3").getMessage());
	}
}
