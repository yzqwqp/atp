package com.uusoft.atp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.service.TestCaseService;
import com.uusoft.atp.service.TestMethodService;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/testcase")
public class TestCaseController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestCaseController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	@Resource
	TestCaseService testCaseService;
	@Resource
	TestMethodService testMethodService;
	@Resource
	TestServiceService testServiceService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestCaseInfo> caseData = null;
		List<TestMethodInfo> initMethodList = null;
		String sid = request.getParameter("initserviceselect");
		String initmethodselect = request.getParameter("initmethodselect");
		LOGGER.info("******TestCase  index  ******sid:["+sid +"] initmethodselect:["+initmethodselect + "] begin******");
		if (!StringUtils.isEmpty(sid))
		{
			TestMethodInfo res = testMethodService.selectByMethodId(Integer.parseInt(sid));
				initMethodList.add(res);
		}
		
		if (!StringUtils.isEmpty(initmethodselect))
		{
			caseData = testCaseService.selectByMethodId(Integer.parseInt(initmethodselect));//当前选中的methodid，查出所有methodid下的测试用例
		}
		else if (!StringUtils.isEmpty(sid))
		{
			caseData = testCaseService.selectByServiceId(Integer.parseInt(sid));//当前选中的serviceid，查出所有serviceid下的测试用例
		} else
		{
			caseData = testCaseService.selectAll();//查出所有测试用例
		}
		
//		List<InitServiceInfo> initData = initServiceService.selectAllService();
//		request.setAttribute("serviceList", allData);//筛选列的[服务名称]数据
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("caseList", caseData);//查询结果列的数据
		return "testcase/index";
	}
	
	@RequestMapping("/selectById")
    @ResponseBody
    public TestCaseInfo selectById(Integer sid){
		LOGGER.info("******TestCaseController开始查询caseId :" +sid+" *****");
		TestCaseInfo result = testCaseService.selectByCaseId(sid);
        return result;
    }
	
	@RequestMapping("/selectByMethodId")
	public String selectByMethodId(HttpServletRequest request, int sid) {
		
		//--1--从method页面，根据methodId查询出来的testcase结果集
		LOGGER.info("✈--TestCase--selectByMethodId--begin--✈");
		List<TestCaseInfo> caseData = testCaseService.selectByMethodId(sid);
		request.setAttribute("caseList", caseData);
		
		//--3--根据methodId搜索的method结果集
		List<TestMethodInfo> listMethod = new ArrayList<TestMethodInfo>();
//		listMethod.add(testMethodService.selectById(sid).getObj());
		request.setAttribute("initMethodList", listMethod);
		
		//--2--根据methodId搜索的service结果集
//		int serviceId = testMethodService.selectById(sid).getObj().getService_id();
//		ResultTool<TestServiceInfo> res = testServiceService.selectById(serviceId);
		List<TestServiceInfo> listService = new ArrayList<TestServiceInfo>();
//		listService.add(res.getObj());
		request.setAttribute("initServiceList", listService);
		
		return "testcase/index";
	}
	
	@RequestMapping("/add")
	@ResponseBody
    public ResultTool<String> add(TestCaseInfo testCaseInfo) {
		int i = testCaseService.insert(testCaseInfo);
		if (i>0) {
			result.setObj("【"+testCaseInfo.getCase_des()+"】新增成功");;
		} else {
			result.setObj("【"+testCaseInfo.getCase_des()+"】新增失败");;
		}
        return result;
    }
	
	@RequestMapping("/updateById")
	@ResponseBody
    public ResultTool<String> update(TestCaseInfo testCaseInfo) {
		LOGGER.info("******TestCaseController开始updateById :" +testCaseInfo.getCase_id()+" *****");
		LOGGER.info(testCaseInfo.toString());
		int i = testCaseService.update(testCaseInfo);
		if (i>0) {
			result.setObj("【"+testCaseInfo.getCase_des()+"】更新成功");;
		} else {
			result.setObj("【"+testCaseInfo.getCase_des()+"】更新失败");;
		}
        return result;
    }
	
	@RequestMapping("/deleteById")
    @ResponseBody
    public ResultTool<String> deleteById(int sid){
		LOGGER.info("******TestCaseController开始deleteById :" +sid+" *****");
		int i = testCaseService.deleteById(sid);
		if (i>0) {
			result.setObj("【"+sid+"】删除成功");;
		} else {
			result.setObj("【"+sid+"】删除失败");;
		}
        return result;
    }
	
	@RequestMapping("/run")
    @ResponseBody
    public ResultTool<String> run(int sid){
		LOGGER.info("******TestCaseController开始run :" +sid+" *****");
		ResultTool<String> result = testCaseService.runById(sid);
		LOGGER.info(result.toString());
		return result;
    }
	
	@RequestMapping("/createdMethod")
	@ResponseBody
	public TestMethodInfo createdMethod(int sid){
        return testMethodService.selectByMethodId(sid);
	}
	
	@RequestMapping("/selectMethodId")
    @ResponseBody
	public List<TestCaseInfo> selectMethodId(Integer sid) {
		return testCaseService.selectByMethodId(sid);
	}
}
