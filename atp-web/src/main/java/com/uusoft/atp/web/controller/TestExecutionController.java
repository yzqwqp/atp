package com.uusoft.atp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.model.TestResultInfo;
import com.uusoft.atp.service.TestExecutionService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/testexecution")
public class TestExecutionController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestExecutionController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	@Resource
	TestExecutionService testExecutionService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<TestExecutionInfo> testExecutionInfoList = testExecutionService.selectAll();
		
		request.setAttribute("executionInfoList", testExecutionInfoList);//筛选列的[服务名称]数据
		return "testexecution/index";
	}
	
	@RequestMapping("/selectByExecutionId")
    @ResponseBody
    public TestExecutionInfo selectByExecutionId(Integer executionId){
		LOGGER.info("******selectByExecutionId查询suiteId :" +executionId+" *****");
        return testExecutionService.selectByExecutionId(executionId);
    }
	
//	@RequestMapping("/selectResultByExecutionId")
//    @ResponseBody
//    public String selectResultByExecutionId(HttpServletRequest request,Integer executionId){
//		LOGGER.info("******selectResultByExecutionId :" +executionId+" *****");
//		List<TestResultInfo> resultInfoList =  testExecutionService.selectResultByExecutionId(executionId);
//		for (TestResultInfo t : resultInfoList) {
//			LOGGER.info(t.toString());
//		}
//		request.setAttribute("resultList", resultInfoList);
//		return "testexecution/index";
//    }
	
	@RequestMapping("/selectResultByExecutionId")
//    @ResponseBody
    public ModelAndView  selectResultByExecutionId(HttpServletRequest request,Integer executionId){
		 ModelAndView modelAndView = new ModelAndView();
		LOGGER.info("******selectResultByExecutionId :" +executionId+" *****");
		List<TestResultInfo> resultInfoList =  testExecutionService.selectResultByExecutionId(executionId);
		for (TestResultInfo t : resultInfoList) {
			LOGGER.info(t.toString());
		}
		modelAndView.addObject("resultList", resultInfoList);
		modelAndView.setViewName("testexecution/resultlist");
		return modelAndView;
    }
}
