package com.uusoft.atp.service.impl;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.TestRunsqlMapper;
import com.uusoft.atp.dao.utils.DataSource;
import com.uusoft.atp.service.TestRunsqlService;


@Service("TestRunsqlService")
@Transactional
@DataSource(name = DataSource.APPTMS_DATASOURCE)
public class TestRunsqlServiceImpl implements TestRunsqlService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestRunsqlServiceImpl.class);

	@Resource
	TestRunsqlMapper mapper; 
	
	@Transactional
	public Integer updateBySql(String sqlStr) {
		LOGGER.info("### 执行的SQL是："+sqlStr);
		return mapper.updateBySql(sqlStr);
	}
	
}
