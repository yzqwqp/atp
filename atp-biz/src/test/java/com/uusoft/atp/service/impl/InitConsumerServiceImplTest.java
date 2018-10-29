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

import com.uusoft.atp.dao.InitConsumerMapper;
import com.uusoft.atp.dao.TestCaseMapper;
import com.uusoft.atp.model.InitConsumerInfo;
import com.uusoft.atp.model.ParameterVo;

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class InitConsumerServiceImplTest {

	/** 
	 * <p>Description:TODO(这里用一句话描述这个方法的作用)</p>
	 * @throws java.lang.Exception
	 * @author guishuai
	 */
	@Resource
	private InitConsumerMapper consumermapper;
	@Resource
//	private initConsumerServiceImpl impl;
	
	InitConsumerInfo info = new InitConsumerInfo();

	@Test
	public void test() { 
		info.setGeneric("true");
		info.setInterfacename("name");
		info.setOwner("trs111");
		info.setServername("2");
		info.setVersion("${trs.version}");
//		System.out.println(impl.insertxml(info));
	}
}
