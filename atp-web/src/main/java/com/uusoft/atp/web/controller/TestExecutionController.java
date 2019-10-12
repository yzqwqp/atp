package com.uusoft.atp.web.controller;

import java.util.ArrayList;
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
import com.uusoft.atp.model.TestSuiteInfo;
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
    //@ResponseBody
    public String selectByExecutionId(HttpServletRequest request, Integer sid){
		TestExecutionInfo testExecutionInfo = testExecutionService.selectByExecutionId(sid);
		List<TestExecutionInfo> executionInfoList = new ArrayList<TestExecutionInfo>();
		executionInfoList.add(testExecutionInfo);
		request.setAttribute("executionInfoList", executionInfoList);//筛选列的[服务名称]数据
		return "testexecution/index";
    }
	
	@RequestMapping("/selectResultByExecutionId")
    //@ResponseBody
    public String selectResultByExecutionId(HttpServletRequest request,Integer executionId){
		List<TestResultInfo> resultInfoList =  testExecutionService.selectResultByExecutionId(executionId);
		request.setAttribute("resultList", resultInfoList);
		return "testexecution/resultlist";
    }
	
	@RequestMapping("/selectFailureResultByExecutionId")
    //@ResponseBody
    public String selectFailureResultByExecutionId(HttpServletRequest request,Integer executionId){
		List<TestResultInfo> resultInfoList =  testExecutionService.selectFailureResultByExecutionId(executionId);
		request.setAttribute("resultList", resultInfoList);
		return "testexecution/resultlist";
    }
	
	@RequestMapping("/selectUnrunResultByExecutionId")
    public String selectUnrunResultByExecutionId(HttpServletRequest request,Integer executionId){
		List<TestResultInfo> resultInfoList =  testExecutionService.selectUnrunResultByExecutionId(executionId);
		request.setAttribute("resultList", resultInfoList);
		return "testexecution/resultlist";
    }
	
	
	
//	@RequestMapping("/selectResultByExecutionId")
////    @ResponseBody
//    public ModelAndView  selectResultByExecutionId(HttpServletRequest request,Integer executionId){
//		LOGGER.info("******selectResultByExecutionId :" +executionId+" *****");
//		List<TestResultInfo> resultInfoList =  testExecutionService.selectResultByExecutionId(executionId);
//		for (TestResultInfo t : resultInfoList) {
//			LOGGER.info(t.toString());
//		}
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.addObject("resultList", resultInfoList);
//		modelAndView.setViewName("testexecution/resultlist");
//		return modelAndView;
//    }
	
	
}
