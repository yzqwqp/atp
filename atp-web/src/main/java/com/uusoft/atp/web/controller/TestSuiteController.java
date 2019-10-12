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

import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.model.TestSuiteInfo;
import com.uusoft.atp.service.TestExecutionService;
import com.uusoft.atp.service.TestMethodService;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.service.TestSuiteService;
import com.uusoft.atp.utils.ResultTool;
import com.uusoft.atp.utils.StringUtil;

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
	TestExecutionService testExecutionService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestMethodInfo> initMethodList = new ArrayList<TestMethodInfo>();
		List<TestSuiteInfo> suiteData = new ArrayList<TestSuiteInfo>();
		String slid = request.getParameter("initserviceselect");
		String mlid = request.getParameter("initmethodselect");

		//slid选择不为空
		if (!StringUtil.isBlank(slid)) {
			
			initMethodList = testMethodService.selectByServiceId(Integer.parseInt(slid));// 1
			// mlid选择不为空，查单条
			if (!StringUtil.isBlank(mlid)) {
				LOGGER.info("******TestMethod selectByMethodId -1- [slid] :["+ slid +"] [mlid] :["+ mlid +"]  begin******");
				suiteData = testSuiteService.selectByMethodId(Integer.parseInt(mlid));
			} else {
				LOGGER.info("******TestMethod selectByMethodId -2- [slid] :["+ slid +"] [mlid] :["+ mlid +"]  begin******");
				// mlid选择为空，查serviceId的集合
				suiteData = testSuiteService.selectByServiceId(Integer.parseInt(slid));
			}
		} 
		//slid选择为空
		else {
			// mlid选择不为空
			if (!StringUtil.isBlank(mlid)) {
				LOGGER.info("******TestMethod selectByMethodId -3- [slid] :["+ slid +"] [mlid] :["+ mlid +"]  begin******");
				TestMethodInfo minfo = testMethodService.selectByMethodId(Integer.parseInt(mlid));
				initMethodList.add(minfo);// 1
				suiteData = testSuiteService.selectByMethodId(Integer.parseInt(mlid));
			} else {
				LOGGER.info("******TestMethod selectByMethodId -4- [slid] :["+ slid +"] [mlid] :["+ mlid +"]  begin******");
				// mlid选择为空
				initMethodList = testMethodService.selectAll();// 1
				suiteData = testSuiteService.selectAll();
			}
		}
//		List<InitServiceInfo> initData = initServiceService.selectAllService();
//		request.setAttribute("serviceList", allData);//筛选列的[服务名称]数据
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("suiteList", suiteData);//查询结果列的数据
		return "testsuite/index";
	}
	
	@RequestMapping("/selectBySuiteId")
    @ResponseBody
    public TestSuiteInfo selectById(Integer suiteId){
		LOGGER.info("******TestSuiteController开始selectById查询suiteId :" +suiteId+" *****");
        return testSuiteService.selectBySuiteId(suiteId);
    }
	
	@RequestMapping("/selectMethodId")
	@ResponseBody
	public List<TestSuiteInfo> selectMethodId(Integer methodId) {
		LOGGER.info("******TestSuiteController开始selectMethodId查询methodId :" +methodId+" *****");
		return testSuiteService.selectByMethodId(methodId);
	}
	
	@RequestMapping("/selectByServiceId")
	@ResponseBody
	public List<TestSuiteInfo> selectByServiceId(Integer serviceId) {
		LOGGER.info("******TestSuiteController开始selectByServiceId查询serviceId :" +serviceId+" *****");
		return testSuiteService.selectByMethodId(serviceId);
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
		LOGGER.info("******TestSuiteController开始updateById :" +testSuiteInfo.getSuite_id()+" *****");
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
		LOGGER.info("******TestSuiteController开始deleteById :" +sid+" *****");
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
    public ResultTool<TestExecutionInfo> run(int sid, String sname){
		LOGGER.info("******开始执行【测试用例】，类型是：[testSuite], suiteId :[" +sid+"], suiteDes : ["+sname+ "]*****");
		return  testExecutionService.execution(1, sid, sname);//1：testSuite测试用例 2：testMethod测试集 3：testService测试服务
    }
	
	@RequestMapping("/selectByMethodIdToSuite")
	public String selectByMethodIdToSuite(HttpServletRequest request, int sid) {
		// 【测试用例集】页面直接跳转到【测试用例】页面
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestMethodInfo> initMethodList = new ArrayList<TestMethodInfo>();
		List<TestSuiteInfo> testSuiteInfoList = new ArrayList<TestSuiteInfo>();
		if (StringUtil.isBlank(String.valueOf(sid))){
			initMethodList = testMethodService.selectAll();
			testSuiteInfoList = testSuiteService.selectAll();
			request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
			request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
			request.setAttribute("methodInfo", initMethodList);//筛选列的[方法名称]数据
			request.setAttribute("suiteList", testSuiteInfoList);
			return "testsuite/index";
		}
		testSuiteInfoList = testSuiteService.selectByMethodId(sid);
		TestMethodInfo minfo = testMethodService.selectByMethodId(sid);
		initMethodList.add(minfo);// 1
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("methodInfo", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("suiteList", testSuiteInfoList);
		return "testsuite/index";
	}
}
