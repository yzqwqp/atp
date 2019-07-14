package com.uusoft.atp.service.impl;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.utils.ResultTool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@Transactional
public class TestExecutionServiceImplTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestExecutionServiceImplTest.class);

	@Resource
	TestExecutionServiceImpl testExecutionService;
	
//	@Test
//	public void testGetParaTypes(){
//		ResultTool<String> res = testExecutionService.execution(30, 3, "司机登录-正常密码登录");
//		LOGGER.info(res.getObj());
//	}
	
	@Test
	public void executionTest(){
		ResultTool<TestExecutionInfo> res = testExecutionService.execution(1, 27, "tset");
		LOGGER.info(res.getObj().toString());
	}

}
