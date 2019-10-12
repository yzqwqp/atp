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

import com.uusoft.atp.model.TestCaseInfo;
import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.model.TestSuiteInfo;
import com.uusoft.atp.service.TestCaseService;
import com.uusoft.atp.service.TestMethodService;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.service.TestSuiteService;
import com.uusoft.atp.utils.ResultTool;
import com.uusoft.atp.utils.StringUtil;

@Controller
@RequestMapping("/testcase")
public class TestCaseController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestCaseController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	@Resource
	TestCaseService testCaseService;
	@Resource
	TestSuiteService testSuiteService;
	@Resource
	TestMethodService testMethodService;
	@Resource
	TestServiceService testServiceService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestMethodInfo> initMethodList = new ArrayList<TestMethodInfo>();
		List<TestSuiteInfo> initSuiteList = new ArrayList<TestSuiteInfo>();
		List<TestCaseInfo> caseData = new ArrayList<TestCaseInfo>();
		String serviceselectid = request.getParameter("initserviceselect");
		String methodselectid = request.getParameter("initmethodselect");
		String suiteselectid = request.getParameter("initsuiteselect");
		
		// serviceselectid选择不为空
		if (!StringUtil.isBlank(serviceselectid)) {
			// serviceselectid选择不为空，用serviceselectid查methodList用于初始化
			initMethodList = testMethodService.selectByServiceId(Integer.parseInt(serviceselectid));// 初始化initMethodList -- 1
			if (!StringUtil.isBlank(methodselectid)) {
				// serviceselectid选择不为空，methodselectid选择不为空，用methodselectid查suiteList
				initSuiteList = testSuiteService.selectByMethodId(Integer.parseInt(methodselectid));// 初始化initSuiteList --1
				if (!StringUtil.isBlank(suiteselectid)) {
					LOGGER.info("******TestMethod selectByMethodId -1- [serviceselectid] :["+ serviceselectid +"] [mlid] :["+ methodselectid +"] [suiteselectid] :["+ suiteselectid +"]  begin******");
					// serviceselectid选择不为空，methodselectid选择不为空，suiteselectid 不为空，用suiteselectid查caseList --1
					caseData = testCaseService.selectBySuiteId(Integer.parseInt(suiteselectid));
				} else {
					LOGGER.info("******TestMethod selectByMethodId -2- [serviceselectid] :["+ serviceselectid +"] [mlid] :["+ methodselectid +"] [suiteselectid] :["+ suiteselectid +"]  begin******");
					// serviceselectid选择不为空，methodselectid选择不为空，suiteselectid 为空，用methodselectid查caseList --1
					caseData = testCaseService.selectByMethodId(Integer.parseInt(methodselectid));
				}
			} else {
			// serviceselectid选择不为空，methodselectid选择为空，查serviceId的集合，查suiteList
				initSuiteList = testSuiteService.selectByServiceId(Integer.parseInt(serviceselectid));// 初始化initSuiteList --1
				if (!StringUtil.isBlank(suiteselectid)) {
					LOGGER.info("******TestMethod selectByMethodId -3- [serviceselectid] :["+ serviceselectid +"] [mlid] :["+ methodselectid +"] [suiteselectid] :["+ suiteselectid +"]  begin******");
					// serviceselectid选择不为空，methodselectid选择为空，suiteselectid 不为空，用suiteselectid查caseList
					caseData = testCaseService.selectBySuiteId(Integer.parseInt(suiteselectid)); // --1
				} else {
					LOGGER.info("******TestMethod selectByMethodId -4- [serviceselectid] :["+ serviceselectid +"] [mlid] :["+ methodselectid +"] [suiteselectid] :["+ suiteselectid +"]  begin******");
					// serviceselectid选择不为空，methodselectid选择为空，suiteselectid 为空，用serviceselectid查caseData
					caseData = testCaseService.selectByServiceId(Integer.parseInt(serviceselectid)); // --1
				}
			}
		} 
		// serviceselectid选择为空
		else {
			// serviceselectid选择为空，查所有methodList用于初始化
			initMethodList = testMethodService.selectAll();// 初始化initMethodList --1
			if (!StringUtil.isBlank(methodselectid)) {
				// serviceselectid选择为空，methodselectid选择不为空，用methodselectid查suiteList
				initSuiteList = testSuiteService.selectByMethodId(Integer.parseInt(methodselectid));// 初始化initSuiteList 
				if (!StringUtil.isBlank(suiteselectid)) {
					LOGGER.info("******TestMethod selectByMethodId -1- [serviceselectid] :["+ serviceselectid +"] [mlid] :["+ methodselectid +"] [suiteselectid] :["+ suiteselectid +"]  begin******");
					// serviceselectid选择为空，methodselectid选择不为空，suiteselectid 不为空，用suiteselectid查caseList
					caseData = testCaseService.selectBySuiteId(Integer.parseInt(suiteselectid));
				} else {
					LOGGER.info("******TestMethod selectByMethodId -2- [serviceselectid] :["+ serviceselectid +"] [mlid] :["+ methodselectid +"] [suiteselectid] :["+ suiteselectid +"]  begin******");
					// serviceselectid选择为空，methodselectid选择不为空，suiteselectid 为空，用methodselectid查caseList
					caseData = testCaseService.selectByMethodId(Integer.parseInt(methodselectid));
				}
			} else {
			// serviceselectid选择为空，methodselectid选择为空，查所有suiteList
				initSuiteList = testSuiteService.selectAll();// 初始化initSuiteList
				if (!StringUtil.isBlank(suiteselectid)) {
					LOGGER.info("******TestMethod selectByMethodId -3- [serviceselectid] :["+ serviceselectid +"] [mlid] :["+ methodselectid +"] [suiteselectid] :["+ suiteselectid +"]  begin******");
					// serviceselectid选择为空，methodselectid选择为空，suiteselectid 不为空，用suiteselectid查caseList
					caseData = testCaseService.selectBySuiteId(Integer.parseInt(suiteselectid));
				} else {
					LOGGER.info("******TestMethod selectByMethodId -4- [serviceselectid] :["+ serviceselectid +"] [mlid] :["+ methodselectid +"] [suiteselectid] :["+ suiteselectid +"]  begin******");
					// serviceselectid选择为空，methodselectid选择为空，suiteselectid 为空，查所有caseList
					caseData = testCaseService.selectAll();
				}
			}
		}
//		List<InitServiceInfo> initData = initServiceService.selectAllService();
//		request.setAttribute("serviceList", allData);//筛选列的[服务名称]数据
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("initSuiteList", initSuiteList);//筛选列的[方法名称]数据
		request.setAttribute("caseList", caseData);//查询结果列的数据
		return "testcase/index";
	}
	
	@RequestMapping("/selectByCaseId")
    @ResponseBody
    public TestCaseInfo selectById(Integer caseId){
		LOGGER.info("******TestCaseController开始selectById查询caseId :" +caseId+" *****");
        return testCaseService.selectByCaseId(caseId);
    }
	
	@RequestMapping("/selectBySuiteId")
    @ResponseBody
    public List<TestCaseInfo> selectBySuiteId(Integer suiteId){
		LOGGER.info("******TestCaseController开始selectBySuiteId查询suiteId :" +suiteId+" *****");
        return testCaseService.selectBySuiteId(suiteId);
    }
	
	@RequestMapping("/selectMethodId")
	@ResponseBody
	public List<TestCaseInfo> selectMethodId(Integer methodId) {
		LOGGER.info("******TestCaseController开始selectMethodId查询methodId :" +methodId+" *****");
		return testCaseService.selectByMethodId(methodId);
	}
	
	@RequestMapping("/selectByServiceId")
	@ResponseBody
	public List<TestCaseInfo> selectByServiceId(Integer serviceId) {
		LOGGER.info("******TestCaseController开始selectByServiceId查询serviceId :" +serviceId+" *****");
		return testCaseService.selectByMethodId(serviceId);
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
	
	@RequestMapping("/selectBySuiteIdToCase")
	public String selectBySuiteIdToCase(HttpServletRequest request, int sid) {
		// 【测试用例】页面直接跳转到【测试步骤】页面
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestMethodInfo> initMethodList = testMethodService.selectAll();
		List<TestSuiteInfo> initSuiteList = new ArrayList<TestSuiteInfo>();
		if (StringUtil.isBlank(String.valueOf(sid))){
			initSuiteList = testSuiteService.selectAll();
			List<TestCaseInfo> caseData = testCaseService.selectAll();
			request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
			request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
			request.setAttribute("methodInfo", initMethodList);//筛选列的[方法名称]数据
			request.setAttribute("suiteInfo", initMethodList);
			request.setAttribute("caseList", caseData);//查询结果列的数据
			return "testsuite/index";
		}
		TestSuiteInfo testSuiteInfo = testSuiteService.selectBySuiteId(sid);
		initSuiteList.add(testSuiteInfo);
		List<TestCaseInfo> caseData = testCaseService.selectBySuiteId(sid);
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("initSuiteList", initSuiteList);//筛选列的[方法名称]数据
		request.setAttribute("suiteInfo", initSuiteList);//
		request.setAttribute("caseList", caseData);//查询结果列的数据
		return "testcase/index";
	}
}
