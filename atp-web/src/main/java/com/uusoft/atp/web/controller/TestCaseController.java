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
		LOGGER.info("******TestCaseController  index   begin******");
		
		List<TestCaseInfo> allData = testCaseService.selectAll();
		List<TestServiceInfo> initData = testServiceService.selectAll();
		request.setAttribute("serviceList", initData);//筛选列的[服务名称]数据
		request.setAttribute("caseList", allData);
		return "testcase/index";
	}
	
	@RequestMapping("/selectById")
    @ResponseBody
    public ResultTool<TestCaseInfo> selectById(int sid){
		LOGGER.info("******TestCaseController开始查询caseId :" +sid+" *****");
		ResultTool<TestCaseInfo> result = testCaseService.selectById(sid);
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
    public ResultTool<String> add(String service_name, String method_name, String case_des, String is_run, String case_assert_value, String case_assert_type) {
		TestCaseInfo info = new TestCaseInfo();
		int method_id = testMethodService.selectByServiceNameAndMethodName(service_name, method_name).getObj().getMethod_id();
		info.setMethod_id(method_id);
		info.setCase_des(case_des);
		info.setIs_run(Integer.parseInt(is_run));
		info.setCase_assert_value(case_assert_value);
		info.setCase_assert_type(case_assert_type);
		int i = testCaseService.insert(info);
		if (i>0) {
			result.setObj("【"+method_name+"的"+case_des+"】新增成功");;
		} else {
			result.setObj("【"+method_name+"的"+case_des+"】新增失败");;
		}
        return result;
    }
	
	@RequestMapping("/update")
	@ResponseBody
    public ResultTool<String> update(TestCaseInfo testCaseInfo) {
		LOGGER.info("******TestCaseController开始updateById :" +testCaseInfo.getCase_id()+" *****");
		LOGGER.info("id: ["+testCaseInfo.getCase_id()
					+"] des: ["+testCaseInfo.getCase_des()+"] isrun: ["+testCaseInfo.getIs_del()+"] "
							+ "case_assert_type: [" + testCaseInfo.getCase_assert_type() +"] "
									+ "case_assert_value: ["+testCaseInfo.getCase_assert_value()+"] "
											+ "case_data: ["+testCaseInfo.getCase_data()
					);
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
}
