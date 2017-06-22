package com.uusoft.atp.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.model.TestMethodInfo;

/** 
* 类说明 ：
* 	TestMethodServiceImpl测试类
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2016年12月13日 下午2:58:31 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestMethodServiceImplTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestMethodServiceImplTest.class);
	
	@Autowired
	TestMethodServiceImpl impl;
	
	TestMethodInfo info = new TestMethodInfo();
	
	/**
	 * 测试TestMethodServiceImpl的DeleteById方法
	 */
	@Test
	public void test04DeleteById() {
		int i =impl.deleteById(3);
		LOGGER.info("###########################");
		LOGGER.info(Thread.currentThread() .getStackTrace()[1].getMethodName());
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Assert.assertTrue(i>0);
	}

}
