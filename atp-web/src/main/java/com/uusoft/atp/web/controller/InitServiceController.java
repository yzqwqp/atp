package com.uusoft.atp.web.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uusoft.atp.model.InitServiceInfo;
import com.uusoft.atp.model.TestServiceInfo;
import com.uusoft.atp.service.InitServiceService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/initService")
public class InitServiceController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(InitServiceController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	@Resource
	InitServiceService initServiceService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		LOGGER.info("******initservice******");
		//根据参数名获取参数值
		Enumeration em = request.getParameterNames();
		if(!em.hasMoreElements()) {
			List<InitServiceInfo> allData = initServiceService.selectAll();
			List<InitServiceInfo> servername = initServiceService.selectAllService();
			request.setAttribute("serviceList", allData);
			request.setAttribute("servicenameList", servername);	
			return "initservice/index";
		}
		ArrayList listdata = new ArrayList();
		while (em.hasMoreElements()){
			String name = (String) em.nextElement();
			String value = request.getParameter(name);
			listdata.add(value);
		}
		List<InitServiceInfo> allData = initServiceService.getbyname(listdata.get(0).toString(), listdata.get(1).toString());
		List<InitServiceInfo> servername = initServiceService.selectAllService();
		request.setAttribute("serviceList", allData);
		request.setAttribute("servicenameList", servername);	
		return "initservice/index";
	}
	
	
	@RequestMapping("/selectByName")
    @ResponseBody
    public List<InitServiceInfo>  selectByName(String sname){
		LOGGER.info("******InitServiceController开始查询selectByName :" +sname+" *****");
		List<InitServiceInfo> info = initServiceService.selectByName(sname);
        return info;
    }
	
	
	@RequestMapping("/initser")
	@ResponseBody
	public String initser(HttpServletRequest request) {
		LOGGER.info("******init.....service******");
		initServiceService.insert();
		return "{'a':'1'}";
	}
	
	@RequestMapping("/getmethodname")
	@ResponseBody
    public ResultTool<List<String>> getmethodname(HttpServletRequest request,String sname) {
		LOGGER.info("******开始查询serviceName :" +sname+" 对应的method *****");
		ResultTool<List<String>> result = initServiceService.getmethodname(sname);
        return result;
    }
	
	@RequestMapping("/querydata")
	@ResponseBody
	public String querydata(InitServiceInfo initserviceinfo) {
		LOGGER.info("******initservice******");
		List<InitServiceInfo> allData = initServiceService.selectByName(initserviceinfo.getService_name());
		return "initservice/index";
	}
	
}
