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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class TestMethodMapperTest {

	/** 
	 * <p>Description:TODO(这里用一句话描述这个方法的作用)</p>
	 * @throws java.lang.Exception
	 * @author Adele
	 * @date 2016年12月14日 下午2:22:29   
	 */
	@Resource
	private TestMethodMapper methodMapper;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		int id = methodMapper.selectMethodIdByName("testMethod");
		System.out.println("************"+id);
	}

}
