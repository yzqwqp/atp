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
import com.uusoft.atp.service.InitServiceService;
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
	TestMethodService testMethodService;
	@Resource
	TestServiceService testServiceService;
	@Resource
	TestCaseService testCaseService;
	@Resource
	InitServiceService initServiceService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		LOGGER.info("******TestCase  index   begin******");
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestCaseInfo> caseData = null;
		List<TestMethodInfo> initMethodList = null;
		String sid = request.getParameter("initserviceselect");
		if (!StringUtils.isEmpty(sid))
		{
			ResultTool<List<TestMethodInfo>> res = testMethodService.createdMethod(Integer.parseInt(sid));
			if (res.isSuccess())
			{
				initMethodList = res.getObj();
			}
		}
		
		String initmethodselect = request.getParameter("initmethodselect");
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
		TestCaseInfo result = testCaseService.selectById(sid);
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
    public void run(int sid){
		LOGGER.info("******TestCaseController开始run :" +sid+" *****");
		testCaseService.run(sid);
    }
	
	@RequestMapping("/createdMethod")
	@ResponseBody
	public ResultTool<List<TestMethodInfo>> createdMethod(int sid){
		LOGGER.info("******开始查询serviceName :" +sid+" 对应的method *****");
		ResultTool<List<TestMethodInfo>> result = testMethodService.createdMethod(sid);
        return result;
	}
	
	@RequestMapping("/selectMethodId")
    @ResponseBody
	public TestMethodInfo selectMethodId(Integer sid) {
		if (sid != null) {
			LOGGER.info("******开始查询methodId :" + sid + " *****");
			List<TestMethodInfo> result = testMethodService.selectById(sid);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
		}
		return null;
	}
}
