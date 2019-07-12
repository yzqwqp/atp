
package com.uusoft.atp.service.impl;

/** 
* 类说明 ：
* 	Junit测试类TestServiceServiceImpl
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2016年12月13日 上午10:19:12 
*/
import java.util.List;

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

import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.utils.ResultTool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class TestServiceServiceImplTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceServiceImplTest.class);
	
	@Autowired
	TestServiceServiceImpl impl;
	
	TestServiceInfo info = new TestServiceInfo();
	
	/**
	 * 测试TestServiceServiceImpl的insert方法
	 */
	@Test
	public void test01insert() {
		info.setService_name("测试插入服务1");
		info.setService_des("测试服务描述1");
		int i = impl.insert(info);
		LOGGER.info("###########################");
		LOGGER.info(Thread.currentThread() .getStackTrace()[1].getMethodName());
		LOGGER.info(String.valueOf(i));
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Assert.assertTrue(i>=0);
	}
	
	/**
	 * 测试TestServiceServiceImpl的selectAll方法
	 */
	@Test
	public void test02selectAll() {
		List<TestServiceInfo> i = impl.selectAll();
		LOGGER.info("###########################");
		LOGGER.info(Thread.currentThread() .getStackTrace()[1].getMethodName());
		for (TestServiceInfo info2 : i) {
			LOGGER.info(info2.getService_id() + info2.getService_name() + info2.getService_des());
		}
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Assert.assertTrue(!i.isEmpty());
	}
	
	/**
	 * 测试TestServiceServiceImpl的UpdateById方法
	 */
	@Test
	public void test04UpdateById() {
		info.setService_id(3);
		info.setService_name("测试插入服务3");
		info.setService_des("测试服务描述3");
		int i =impl.updateById(info);
		LOGGER.info("###########################");
		LOGGER.info(Thread.currentThread() .getStackTrace()[1].getMethodName());
		LOGGER.info(info.getService_des());
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Assert.assertTrue(i>0);
	}
	
	/**
	 * 测试TestServiceServiceImpl的DeleteById方法
	 */
	@Test
	public void test04DeleteById() {
//		int i =impl.deleteById(3);
		LOGGER.info("###########################");
		LOGGER.info(Thread.currentThread() .getStackTrace()[1].getMethodName());
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		Assert.assertTrue(i>0);
	}
	
}
