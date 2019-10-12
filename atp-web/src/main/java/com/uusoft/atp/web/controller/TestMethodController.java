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
import com.uusoft.atp.utils.ResultTool;
import com.uusoft.atp.utils.StringUtil;

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
	TestExecutionService testExecutionService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestMethodInfo> initMethodList = new ArrayList<TestMethodInfo>();
		List<TestMethodInfo> methodData = new ArrayList<TestMethodInfo>();
		String slid = request.getParameter("initserviceselect");
		String mlid = request.getParameter("initmethodselect");
		LOGGER.info("******TestMethod  index slid :["+ slid +"] mlid :["+ mlid +"]  begin******");
//		// serviceid不为空
//		 if (StringUtil.isNotBlank(sid) && StringUtil.isNotBlank(smid) ) 
//		 {
//			// 取serviceId的所有用例集methodList
//			initMethodList = testMethodService.selectByServiceId(Integer.parseInt(sid));
//			
//			//如果serviceId不为空，且methodId不为空，查询methodId下的所有用例集methodList（应该只有1个）
//			
//			if (StringUtil.isNotBlank(smid))
//			{
//				LOGGER.info("******TestMethod selectByMethodId [smid] :["+ smid +"]  begin******");
//				methodData.add(testMethodService.selectByMethodId(Integer.parseInt(smid)));
//			}
//			else
//			{
//				LOGGER.info("******TestMethod selectByServiceId [sid] :["+ sid +"]  begin******");
//				methodData = testMethodService.selectByServiceId(Integer.parseInt(sid));
//			}
//		} else {
//			//service为空，查出所有的用例集method
//			LOGGER.info("******TestMethod selectAll begin******");
//			initMethodList = testMethodService.selectAll();
//			methodData = initMethodList;
//				
//		}

		//slid选择不为空
		if (!StringUtil.isBlank(slid)) {
			
			initMethodList = testMethodService.selectByServiceId(Integer.parseInt(slid));
			// mlid选择不为空，查单条
			if (!StringUtil.isBlank(mlid)) {
				LOGGER.info("******TestMethod selectByMethodId -1- [slid] :["+ slid +"] [mlid] :["+ mlid +"]  begin******");
				methodData.add(testMethodService.selectByMethodId(Integer.parseInt(mlid)));
			} else {
				LOGGER.info("******TestMethod selectByMethodId -2- [slid] :["+ slid +"] [mlid] :["+ mlid +"]  begin******");
				// mlid选择为空，查serviceId的集合
				methodData = initMethodList;
			}
		} 
		//slid选择为空
		else {
			// mlid选择不为空
			if (!StringUtil.isBlank(mlid)) {
				LOGGER.info("******TestMethod selectByMethodId -3- [slid] :["+ slid +"] [mlid] :["+ mlid +"]  begin******");
				TestMethodInfo minfo = testMethodService.selectByMethodId(Integer.parseInt(mlid));
				initMethodList.add(minfo);
				methodData.add(minfo);
			} else {
				LOGGER.info("******TestMethod selectByMethodId -4- [slid] :["+ slid +"] [mlid] :["+ mlid +"]  begin******");
				// mlid选择为空
				initMethodList = testMethodService.selectAll();
				methodData = initMethodList;
			}
		}
//		List<InitServiceInfo> initData = initServiceService.selectAllService();
//		request.setAttribute("serviceList", allData);//筛选列的[服务名称]数据
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("methodList", methodData);//查询结果列的数据
		return "testmethod/index";
	}
	
//	@RequestMapping("/selectByServiceId")
//	public String selectByServiceId(HttpServletRequest request, int sid) {
//		LOGGER.info("******TestMethod  selectByServiceId   begin******");
//		List<TestMethodInfo> methodData = testMethodService.selectByServiceId(sid);
//		TestServiceInfo res = testServiceService.selectByServiceId(sid);
//		String serviceName = res.getService_name();
//		List<InitServiceInfo> initMethodData = initServiceService.selectByName(serviceName);
//		List<TestServiceInfo> listService = new ArrayList<TestServiceInfo>();
//		listService.add(res);
//		LOGGER.info("***打印initData***");
//		for (InitServiceInfo is : initMethodData) {
//			LOGGER.info(is.getMethod_name());
//		}
//		request.setAttribute("initServiceList", listService);//筛选列的[服务名称]数据
//		request.setAttribute("initMethodList", initMethodData);//筛选列的[方法名称]数据
//		request.setAttribute("methodList", methodData);//查询结果列的数据
//		return "testmethod/index";
//	}
	
	@RequestMapping("/selectById")
    @ResponseBody
	public List<TestMethodInfo> selectById(Integer sid) {
		if (sid != null) {
			List<TestMethodInfo> info = new ArrayList<TestMethodInfo>();
			info.add(testMethodService.selectByMethodId(sid));
			return   info;
		} else {
			return testMethodService.selectAll();
		}
	}
	
	@RequestMapping("/selectByServiceId")
    @ResponseBody
    public List<TestMethodInfo> selectByServiceId(int sid){
		LOGGER.info("******开始查询methodId :" +sid+" *****");
		List<TestMethodInfo> result = testMethodService.selectByServiceId(sid);
        return result;
    }
	
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
	
	@RequestMapping("/selectByMethodId")
	@ResponseBody
	public TestMethodInfo selectByMethodId(int sid){
        return testMethodService.selectByMethodId(sid);
	}
	
	@RequestMapping("/run")
    @ResponseBody
    public ResultTool<TestExecutionInfo> run(int sid, String sname){
		LOGGER.info("******开始执行【测试用例】，类型是：[testSuite], suiteId :[" +sid+"], suiteDes : ["+sname+ "]*****");
		return  testExecutionService.execution(2, sid, sname);//1：testSuite测试用例 2：testMethod测试集 3：testService测试服务
    }
	
	@RequestMapping("/selectByServiceIdToMethod")
	public String selectByServiceIdToMethod(HttpServletRequest request, int sid) {
		// 【测试用例集】页面直接跳转到【测试用例】页面
		List<TestServiceInfo> initServiceList = testServiceService.selectAll();
		List<TestMethodInfo> initMethodList = testMethodService.selectByServiceId(sid);
		List<TestServiceInfo> serviceInfoList = new ArrayList<TestServiceInfo>();
		TestServiceInfo serviceInfo = testServiceService.selectByServiceId(sid);
		serviceInfoList.add(serviceInfo);
		request.setAttribute("initServiceList", initServiceList);//筛选列的[服务名称]数据
		request.setAttribute("initMethodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("methodList", initMethodList);//筛选列的[方法名称]数据
		request.setAttribute("serviceInfo", serviceInfoList);//筛选列的[方法名称]数据
		return "testmethod/index";
	}
}
