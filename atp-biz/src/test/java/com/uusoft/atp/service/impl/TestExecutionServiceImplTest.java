package com.uusoft.atp.service.impl;
import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.utils.DynamicDataSource;
import com.uusoft.atp.model.TestConstantInfo;
import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.service.TestConstantService;
import com.uusoft.atp.utils.ResultTool;

//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class TestExecutionServiceImplTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestExecutionServiceImplTest.class);

	@Resource
	TestExecutionServiceImpl testExecutionService;
	@Resource
	TestConstantService testConstantService;

//	@Ignore
	@Test
	public void test01Execution(){
		ResultTool<TestExecutionInfo> res = testExecutionService.execution(1, 62, "tset");
		LOGGER.info(res.getObj().toString());
	}
	
//	@Test
//	public void test02UpdateBySql(){
//		String sqlStrOld = "UPDATE test_case SET user_id = 100 where case_id = ?";
////		String sqlStrOld = "UPDATE transportationdelivery SET `status`=90 where delivery_id = ?";
//		TestConstantInfo testConstantInfo =testConstantService.selectByConstantId(13);
//		String value = testConstantInfo.getConstant_value();
//		String sqlStrNew = sqlStrOld.replace("?", value);
//		LOGGER.info("######### "+sqlStrNew);
//		// 切换至APPTMS数据源
////		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_APPTMS);
//		DynamicDataSource.setCustomerType(DynamicDataSource.DATASOURCE_ATP);
//		testExecutionService.updateBySql(sqlStrNew);
//		// 清空数据源选择
//		DynamicDataSource.clearCustomerType();
//	}
}
