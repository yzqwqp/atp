/**   
 * <p>Title: TestMethodMapperTest.java</p>
 * @Package com.uusoft.atp.dao 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author Adele
 * @since 2016年12月14日 下午2:22:29 
 * @version V1.0   
 */
package com.uusoft.atp.dao;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.atp.model.TestMethodInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class TestMethodMapperTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestMethodMapperTest.class);

	@Resource
	private TestMethodMapper methodMapper;
	
	@Test
	public void test2() {
		TestMethodInfo info = methodMapper.selectByMethodId(22);
		LOGGER.info(info.toString());
	}

}
