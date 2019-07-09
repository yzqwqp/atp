package com.uusoft.atp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.service.InitServiceService;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/testservice")
public class TestServiceController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestServiceController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	@Resource
	TestServiceService testServiceService;
	@Resource
	InitServiceService initServiceService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		LOGGER.info("******testservice  index   begin******");
		
		List<TestServiceInfo> allData = testServiceService.selectAll();
//		List<InitServiceInfo> initData = initServiceService.selectAllService();
		request.setAttribute("serviceList", allData);
//		request.setAttribute("initServiceList", initData);
		return "testservice/index";
	}
	
	@RequestMapping("/selectById")
    @ResponseBody
    public ResultTool<TestServiceInfo> selectById(int sid){
		LOGGER.info("******开始查询serviceId :" +sid+" *****");
        return  testServiceService.selectById(sid);
    }
	
	@RequestMapping("/addBefore")
	@ResponseBody
    public ResultTool<List<String>> addBefore() {
        LOGGER.info("******开始查询unCreateService*****");
		ResultTool<List<String>> result = testServiceService.selectUnCreateService();
        return result;
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
		LOGGER.info("******开始updateById :" +testServiceInfo.getService_id()+" *****");
		LOGGER.info("id: ["+testServiceInfo.getService_id()+"] name: ["+testServiceInfo.getService_name()+"] des: ["+testServiceInfo.getService_des());
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
	
}
