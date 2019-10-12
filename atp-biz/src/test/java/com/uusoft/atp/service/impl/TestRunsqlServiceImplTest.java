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
import com.uusoft.atp.service.TestRunsqlService;

/**
 * 类说明 ： TestMethodServiceImpl测试类
 * 
 * @author 邱鹏
 * @email qiupeng@toutoujinrong.com
 * @since 2019年7月28日 下午2:58:31
 */
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class TestRunsqlServiceImplTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestRunsqlServiceImplTest.class);

	@Resource
	TestRunsqlService impl;

	/**
	 * 测试TestMethodServiceImpl的DeleteById方法
	 */
	@Test
	public void test05selectByConstantName() {
		String sqlStr = "UPDATE transportationdelivery SET amount=100 where delivery_id = 2872185";
		Integer num = impl.updateBySql(sqlStr);
		Assert.assertTrue(num>0);
	}
		
}
