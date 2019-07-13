/**   
 * <p>Title: TestCaseServiceImplTest.java</p>
 * @Package com.uusoft.atp.service.impl 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author Adele
 * @since 2016年12月14日 上午10:47:50 
 * @version V1.0   
 */
package com.uusoft.atp.service.impl;
import javax.annotation.Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.TestCaseMapper;

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@Transactional
public class TestCaseServiceImplTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCaseServiceImplTest.class);
	
	/** 
	 * <p>Description:TODO(这里用一句话描述这个方法的作用)</p>
	 * @throws java.lang.Exception
	 * @author Adele
	 * @date 2016年12月14日 上午10:47:50   
	 */
	@Resource
	private TestCaseServiceImpl testCaseImp;
	@Resource
	private TestCaseMapper caseMapper;
	String method="testMethod";
	String service="userService";
	@Before
	public void setUp() throws Exception {
	}

	/*@Test
	public void testJson2Object(){
		String json = caseMapper.selectDataById(6);
		LinkedHashMap<String, String>ll=testCaseImp.selectParasByMethod(service,method).get(0);
		Object[] obb= JsonUtil.json2Object(json, ll);
		for(int i=0;i<obb.length;i++){
			System.out.println(obb[i]);

		}
	}*/
	
//	@Test
//	public void testGetParaTypes(){
//		String method2="merchantCheck";
//		String service2="merchantService";
//		String jsonData ="{merchantno:LT001,tradeflag:0}";
//		List<LinkedHashMap<String, String>> ll=testCaseImp.selectParasByMethod(service2,method2);
//		for(int i=0;i<ll.size();i++){
//			System.out.println(ll.get(i));
//		}
//	}
	
	

}
