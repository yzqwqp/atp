package com.uusoft.atp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestDataInfo;
import com.uusoft.atp.service.TestDataService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/testdata")
public class TestDataController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestDataController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	TestCaseInfo info = new TestCaseInfo();

	@Resource
	TestDataService testDataService;
	
	@RequestMapping("/editCaseData")
	@ResponseBody
    public ResultTool<List<TestDataInfo>> add(int case_id) {
		LOGGER.info("******TestDataController  editCaseData   begin******");
        return testDataService.selectById(case_id);
    }
	
	@RequestMapping("/updateData")
	@ResponseBody
    public ResultTool<String> updateData(HttpServletRequest request) {
		LOGGER.info("******TestDataController开始updateData *****");
		String json = request.getParameter("param");
		//--1--解析result的json字符串
		LOGGER.info("******"+json);
		int i = testDataService.updateMap(json);
		if (i>0) {
			result.setObj("更新成功");;
		} else {
			result.setObj("更新失败");;
		}
        return result;
    }
	
}
