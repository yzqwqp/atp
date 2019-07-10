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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uusoft.atp.model.InitServiceInfo;
import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.service.InitServiceService;
import com.uusoft.atp.service.TestMethodService;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/testmethod")
public class TestMethodController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestMethodController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	@Resource
	TestMethodService testMethodService;
	@Resource
	TestServiceService testServiceService;
	@Resource
	InitServiceService initServiceService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		LOGGER.info("******TestMethod  index   begin******");
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestMethodInfo> methodData = null;
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
			methodData = testMethodService.selectById(Integer.parseInt(initmethodselect));
		}
		else
		{
			methodData = testMethodService.selectAll();
		}
		
//		List<InitServiceInfo> initData = initServiceService.selectAllService();
//		request.setAttribute("serviceList", allData);//筛选列的[服务名称]数据
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("methodList", methodData);//查询结果列的数据
		return "testmethod/index";
	}
	
	@RequestMapping("/selectByServiceId")
	public String selectByServiceId(HttpServletRequest request, int sid) {
		LOGGER.info("******TestMethod  selectByServiceId   begin******");
		List<TestMethodInfo> methodData = testMethodService.selectByServiceId(sid);
		ResultTool<TestServiceInfo> res = testServiceService.selectById(sid);
		String serviceName = res.getObj().getService_name();
		List<InitServiceInfo> initMethodData = initServiceService.selectByName(serviceName);
		List<TestServiceInfo> listService = new ArrayList<TestServiceInfo>();
		listService.add(res.getObj());
		LOGGER.info("***打印initData***");
		for (InitServiceInfo is : initMethodData) {
			LOGGER.info(is.getMethod_name());
		}
		request.setAttribute("initServiceList", listService);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodData);//筛选列的[方法名称]数据
		request.setAttribute("methodList", methodData);//查询结果列的数据
		return "testmethod/index";
	}
	
	@RequestMapping("/selectById")
    @ResponseBody
	public TestMethodInfo selectById(Integer sid) {
		if (sid != null) {
			LOGGER.info("******开始查询methodId :" + sid + " *****");
			List<TestMethodInfo> result = testMethodService.selectById(sid);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
		}
		return null;
	}
	
//	@RequestMapping("/selectByServiceId")
//    @ResponseBody
//    public List<TestMethodInfo> selectByServiceId(int sid){
//		LOGGER.info("******开始查询methodId :" +sid+" *****");
//		List<TestMethodInfo> result = testMethodService.selectByServiceId(sid);
//        return result;
//    }
	
	@RequestMapping("/add")
	@ResponseBody
    public ResultTool<String> add(TestMethodInfo testMethodInfo) {
        int i = testMethodService.insert(testMethodInfo);
		if (i>0) {
			result.setObj("【"+testMethodInfo.getMethod_name()+"】新增成功");;
		} else {
			result.setObj("【"+testMethodInfo.getMethod_name()+"】新增失败");;
		}
        return result;
    }
	
	@RequestMapping("/updateById")
	@ResponseBody
    public ResultTool<String> updateById(TestMethodInfo testMethodInfo) {
		LOGGER.info("******开始updateById :" +testMethodInfo.getService_id()+" *****");
		LOGGER.info(testMethodInfo.toString());
		int i = testMethodService.updateById(testMethodInfo);
		if (i>0) {
			result.setObj("【"+testMethodInfo.getMethod_name()+"】更新成功");;
		} else {
			result.setObj("【"+testMethodInfo.getMethod_name()+"】更新失败");;
		}
        return result;
    }
	
	@RequestMapping("/deleteById")
    @ResponseBody
    public ResultTool<String> deleteById(int sid,String sname){
		LOGGER.info("******开始deleteById :" +sid+" *****");
		int i = testMethodService.deleteById(sid);
		if (i>0) {
			result.setObj("【"+sname+"】删除成功");;
		} else {
			result.setObj("【"+sname+"】删除失败");;
		}
        return result;
    }
	
	@RequestMapping("/addBefore")
	@ResponseBody
    public List<TestServiceInfo> addBefore() {
        LOGGER.info("******开始addBefore*****");
        List<TestServiceInfo> result = testServiceService.selectAll();
        return result;
    }
	
	@RequestMapping("/unCreateMethod")
	@ResponseBody
    public ResultTool<List<String>> unCreateMethod(String sname) {
        LOGGER.info("******开始addBefore*****");
        return testMethodService.unCreateMethod(sname);
    }
	
	@RequestMapping("/selectMethodsByServiceId")
    @ResponseBody
    public List<TestMethodInfo> selectMethodsByServiceId(int sid){
		LOGGER.info("******开始查询serviceid :" +sid+" 对应的method *****");
		List<TestMethodInfo> methodData = testMethodService.selectByServiceId(sid);
        return methodData;
    }
	
	@RequestMapping("/createdMethod")
	@ResponseBody
	public ResultTool<List<TestMethodInfo>> createdMethod(int sid){
		LOGGER.info("******开始查询serviceName :" +sid+" 对应的method *****");
		ResultTool<List<TestMethodInfo>> result = testMethodService.createdMethod(sid);
        return result;
	}
	
	@RequestMapping("/selectByServiceNameAndMethodName")
    @ResponseBody
    public ResultTool<TestMethodInfo> selectByServiceNameAndMethodName(String service_name, String method_name){
		LOGGER.info("******开始查询selectByServiceNameAndMethodName :" +service_name+" 的method "+method_name+"*****");
		ResultTool<TestMethodInfo> result = testMethodService.selectByServiceNameAndMethodName(service_name, method_name);
        return result;
    }
}
