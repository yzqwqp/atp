package com.uusoft.atp.service.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.atp.model.TestConstantInfo;
import com.uusoft.atp.service.TestConstantService;

/**
 * 类说明 ： TestMethodServiceImpl测试类
 * 
 * @author 邱鹏
 * @email qiupeng@toutoujinrong.com
 * @since 2016年12月13日 下午2:58:31
 */
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class TestConstantServiceImplTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestConstantServiceImplTest.class);

	@Resource
	TestConstantService impl;

	/**
	 * 测试TestMethodServiceImpl的DeleteById方法
	 */
//	@Test
	public void test04DeleteById() {
		TestConstantInfo testConstantInfo = impl.selectByConstantId(1);
		Assert.assertTrue(!testConstantInfo.equals(null));
		LOGGER.info(testConstantInfo.toString());
	}
	
	/**
	 * 测试TestMethodServiceImpl的DeleteById方法
	 */
	@Test
	public void test05selectByConstantName() {
		TestConstantInfo testConstantInfo = impl.selectByConstantName("httpurl");
		Assert.assertTrue(!testConstantInfo.equals(null));
		LOGGER.info(testConstantInfo.toString());
	}
		
}
