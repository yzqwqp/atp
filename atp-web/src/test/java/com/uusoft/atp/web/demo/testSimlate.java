package com.uusoft.atp.web.demo;


import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.atp.service.TestDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testSimlate {
	private final static Logger LOGGER = LoggerFactory.getLogger(testSimlate.class);
	
//	@Resource
//	private IUserService userService;
	
	@Resource
	private TestDataService testdataservice;
	
	@Test
	public void test() {
//		List<TestDataInfo> alldata = testdataservice.selectAll();
//		System.out.println("test result is :" + SpringUtil.executeInvoke("userService", "isActivityDuration", alldata));
//		LOGGER.info("test result is :" + SpringUtil.executeInvoke("userService", "isActivityDuration", alldata));
//		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		SpringUtil.executeReferenceConfig();
//		LOGGER.info("###########################");
		
	}
	
	

}
