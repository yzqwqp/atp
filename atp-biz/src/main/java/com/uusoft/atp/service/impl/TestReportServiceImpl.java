package com.uusoft.atp.service.impl;

import javax.annotation.Resource;

import com.uusoft.atp.dao.TestReportMapper;
import com.uusoft.atp.model.TestReportInfo;
import com.uusoft.atp.service.TestReportService;

public class TestReportServiceImpl implements TestReportService {
	@Resource
	TestReportMapper testReportMapper;
	
	@Override
	public int insert(TestReportInfo testReportInfo) {
		testReportMapper.insert(testReportInfo);
		return 0;
	}

}
