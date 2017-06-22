/**   
 * <p>Title: TestCaseMapperTest.java</p>
 * @Package com.atp.dao 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author Adele
 * @since 2016年12月12日 上午9:35:27 
 * @version V1.0   
 */
package com.uusoft.atp.dao;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author 邱鹏
 * @version V1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class TestDataMapperTest {

	@Resource
	private TestDataMapper testDataMapper;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test01() {
		testDataMapper.insertInitTestData(10, "specialTradeService", "agentBuy");
//		testDataMapper.selectAll();
	}
	

}
