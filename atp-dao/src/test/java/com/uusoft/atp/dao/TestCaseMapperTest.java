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

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.atp.dao.TestCaseMapper;
import com.uusoft.atp.model.TestCaseInfo;

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class TestCaseMapperTest {

	/** 
	 * <p>Description:TODO(这里用一句话描述这个方法的作用)</p>
	 * @throws java.lang.Exception
	 * @author Adele
	 * @date 2016年12月12日 上午9:35:27   
	 */
	@Resource
	private TestCaseMapper testCaseMapper;
	TestCaseInfo testCase = new TestCaseInfo();
	
	@Before
	public void setUp() throws Exception {
		String json="{loginName:\"Albert\",com.uusoft.cfg.service.model.TaInfo: {tano:\"55\",orgname:\"testTaname\",registercode:\"56\" ,cfctacode:\"tacf\"}com.xxx.Person:{name:\"Adele\",age:15}}";
		testCase.setCase_data(json);
		testCase.setCase_des("adele's test");
	}

	@Test
	public void test01() {
		assertEquals(1, testCaseMapper.insert(testCase));
	}
	
	@Test
	public void testInsert(){
		
	}

}
