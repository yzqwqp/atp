/**   
 * <p>Title: InitMethodServiceImplTest.java</p>
 * @Package com.uusoft.atp.service.impl 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author Adele
 * @since 2016年12月17日 下午1:40:23 
 * @version V1.0   
 */
package com.uusoft.atp.service.impl;


import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.TestCaseMapper;
import com.uusoft.atp.model.ParameterVo;

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@Transactional
public class InitMethodServiceImplTest {

	/** 
	 * <p>Description:TODO(这里用一句话描述这个方法的作用)</p>
	 * @throws java.lang.Exception
	 * @author Adele
	 * @date 2016年12月17日 下午1:40:23   
	 */
	@Resource
	private TestCaseServiceImpl testCaseImp;
	@Resource
	private TestCaseMapper caseMapper;
	@Resource
	private InitMethodServiceImpl impl;
	String method="testMethod";
	String service="testService";
	String jsonData ="{loginName:\"Albert\",\"com.uusoft.cfg.service.model.TaInfo\":{tano:\"55\",orgname:\"testTaname\",registercode:\"56\" ,cfctacode:\"tacf\"},\"com.xxx.Person\":{name:\"Adele\",age:15}}";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Map<String, Object> reMap = impl.getParameterMap(service,method, jsonData);
		String[] objects=(String[]) reMap.get("paraTypes");
		for(int i=0;i<objects.length;i++)
			System.out.println("***********paraTypes"+i+objects[i]);
		Object[] valuess = (Object[]) reMap.get("paraValues");
		for(int j=0;j<objects.length;j++)
			System.out.println("############paraValues"+j+valuess[j]);
	}
	
	@Test
	public void test2() {
//		String method="merchantCheck";
//		String service="merchantService";
//		String jsonData ="{\"merchantno\":\"LT001\",\"tradeflag\":\"0\"}";
		ParameterVo vo = impl.getparameterVo(service,method, jsonData);
		String[] paramTypes =vo.getParamTypes();
		Object[]  paramValues=vo.getParamValues();
		System.out.println("***********paraTypes"+paramTypes[0]);
			System.out.println("############paraValues"+paramValues[0]);
	}

}
