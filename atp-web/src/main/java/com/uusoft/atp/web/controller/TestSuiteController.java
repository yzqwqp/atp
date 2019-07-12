package com.uusoft.atp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.model.TestSuiteInfo;
import com.uusoft.atp.service.TestCaseService;
import com.uusoft.atp.service.TestMethodService;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.service.TestSuiteService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/testsuite")
public class TestSuiteController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestSuiteController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	@Resource
	TestSuiteService testSuiteService;
	@Resource
	TestMethodService testMethodService;
	@Resource
	TestServiceService testServiceService;
	@Resource
	TestCaseService testCaseService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		LOGGER.info("******TestCase  index   begin******");
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestSuiteInfo> suiteData = null;
		List<TestMethodInfo> initMethodList = null;
		String sid = request.getParameter("initserviceselect");
		if (!StringUtils.isEmpty(sid))
		{
			TestMethodInfo res = testMethodService.selectByMethodId(Integer.parseInt(sid));
				initMethodList.add(res);
		}
		
		String initmethodselect = request.getParameter("initmethodselect");
		if (!StringUtils.isEmpty(initmethodselect))
		{
			suiteData = testSuiteService.selectByMethodId(Integer.parseInt(initmethodselect));//当前选中的methodid，查出所有methodid下的测试组
		}
		else if (!StringUtils.isEmpty(sid))
		{
			suiteData = testSuiteService.selectByServiceId(Integer.parseInt(sid));//当前选中的serviceid，查出所有serviceid下的测试用例
		} else
		{
			suiteData = testSuiteService.selectAll();//查出所有测试用例
		}
		
//		List<InitServiceInfo> initData = initServiceService.selectAllService();
//		request.setAttribute("serviceList", allData);//筛选列的[服务名称]数据
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("suiteList", suiteData);//查询结果列的数据
		return "testsuite/index";
	}
	
	@RequestMapping("/selectById")
    @ResponseBody
    public TestSuiteInfo selectById(Integer sid){
		LOGGER.info("******TestCaseController开始查询caseId :" +sid+" *****");
		TestSuiteInfo result = testSuiteService.selectBySuiteId(sid);
        return result;
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public ResultTool<String> add(TestSuiteInfo testSuiteInfo) {
		int i = testSuiteService.insert(testSuiteInfo);
		if (i>0) {
			result.setObj("【"+testSuiteInfo.getSuite_des()+"】新增成功");;
		} else {
			result.setObj("【"+testSuiteInfo.getSuite_des()+"】新增失败");;
		}
        return result;
    }
	
	@RequestMapping("/updateById")
	@ResponseBody
    public ResultTool<String> update(TestSuiteInfo testSuiteInfo) {
		LOGGER.info("******TestCaseController开始updateById :" +testSuiteInfo.getSuite_id()+" *****");
		LOGGER.info(testSuiteInfo.toString());
		int i = testSuiteService.update(testSuiteInfo);
		if (i>0) {
			result.setObj("【"+testSuiteInfo.getSuite_des()+"】更新成功");;
		} else {
			result.setObj("【"+testSuiteInfo.getSuite_des()+"】更新失败");;
		}
        return result;
    }
	
	@RequestMapping("/deleteById")
    @ResponseBody
    public ResultTool<String> deleteById(int sid){
		LOGGER.info("******TestCaseController开始deleteById :" +sid+" *****");
		int i = testSuiteService.deleteById(sid);
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
		ResultTool<String> result = testSuiteService.runBySuiteId(sid);
		LOGGER.info(result.toString());
		return result;
    }
	
	@RequestMapping("/selectMethodId")
    @ResponseBody
	public List<TestSuiteInfo> selectMethodId(Integer sid) {
		LOGGER.info("******开始查询methodId :" + sid + " *****");
		return testSuiteService.selectByMethodId(sid);
	}
}
