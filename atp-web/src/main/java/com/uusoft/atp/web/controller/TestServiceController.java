package com.uusoft.atp.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.uusoft.atp.model.TestExecutionInfo;
import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.service.TestExecutionService;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/testservice")
public class TestServiceController {
	
	@Autowired
	private HttpSession session;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestServiceController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
//	UserInfo userInfo = (UserInfo)session.getAttribute("user");
	
	@Resource
	TestServiceService testServiceService;
	@Resource
	TestExecutionService testExecutionService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestServiceInfo> serviceList = new ArrayList<TestServiceInfo>();

		String sid = request.getParameter("initserviceselect");
		if (!StringUtils.isBlank(sid))
		{	
//			TestServiceInfo info = );
//			if (!info.equals(null))
			serviceList.add(testServiceService.selectByServiceId(Integer.parseInt(sid)));
		}
		else
		{
			serviceList = testServiceService.selectAll();
		}
//		List<InitServiceInfo> initData = initServiceService.selectAllService();
//		request.setAttribute("serviceList", allData);//筛选列的[服务名称]数据
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("serviceList", serviceList);//查询结果列的数据
		return "testservice/index";
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "/login";
	}
	
	@RequestMapping("/selectById")
    @ResponseBody
    public TestServiceInfo selectById(int sid){
		LOGGER.info("******开始查询serviceId :" +sid+" *****");
        return  testServiceService.selectByServiceId(sid);
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public ResultTool<String> add(TestServiceInfo testServiceInfo) {
		int i = testServiceService.insert(testServiceInfo);
		if (i>0) {
			result.setObj("【"+testServiceInfo.getService_name()+"】新增成功");;
		} else {
			result.setObj("【"+testServiceInfo.getService_name()+"】新增失败");;
		}
        return result;
    }
	
	@RequestMapping("/updateById")
	@ResponseBody
    public ResultTool<String> updateById(TestServiceInfo testServiceInfo) {
		int i = testServiceService.updateById(testServiceInfo);
		if (i>0) {
			result.setObj("【"+testServiceInfo.getService_name()+"】更新成功");;
		} else {
			result.setObj("【"+testServiceInfo.getService_name()+"】更新失败");;
		}
        return result;
    }
	
	@RequestMapping("/deleteById")
    @ResponseBody
    public ResultTool<String> deleteById(int sid,String sname){
		LOGGER.info("******开始deleteById :" +sid+" *****");
		int i = testServiceService.deleteById(sid);
		if (i>0) {
			result.setObj("【"+sname+"】删除成功");;
		} else {
			result.setObj("【"+sname+"】删除失败");;
		}
        return result;
    }
	
	@RequestMapping("/run")
    @ResponseBody
    public ResultTool<TestExecutionInfo> run(int sid, String sname){
		LOGGER.info("******开始执行【测试用例】，类型是：[testSuite], suiteId :[" +sid+"], suiteDes : ["+sname+ "]*****");
		return  testExecutionService.execution(3, sid, sname);//1：testSuite测试用例 2：testMethod测试集 3：testService测试服务
    }
}
