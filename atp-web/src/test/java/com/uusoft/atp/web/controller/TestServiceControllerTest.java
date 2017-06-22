package com.uusoft.atp.web.controller;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class TestServiceControllerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceControllerTest.class);
	private static String configLocation = "classpath:spring/spring-application.xml";  
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocation);
	
    /*
     * 验证@Controller注解是否正常可用
     * */
	@Test
	public void test() {
		TestServiceController bean = ctx.getBean("testServiceController", TestServiceController.class);
		LOGGER.info("***********" + bean.toString() + "#############");
		Assert.assertNotNull(bean);
	}

}
