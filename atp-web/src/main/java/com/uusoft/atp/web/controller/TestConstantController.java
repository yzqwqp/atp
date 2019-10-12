package com.uusoft.atp.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uusoft.atp.model.TestConstantInfo;
import com.uusoft.atp.service.TestConstantService;
import com.uusoft.atp.utils.ResultTool;

@Controller
@RequestMapping("/testconstant")
public class TestConstantController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TestConstantController.class);
	
	ResultTool<String> result = new ResultTool<String>("","","");
	
	@Resource
	TestConstantService testConstantService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		List<TestConstantInfo> constantList = testConstantService.selectAll();
		request.setAttribute("constantList", constantList);//筛选列的[服务名称]数据
		return "testconstant/index";
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
    public ResultTool<String> add(TestConstantInfo testConstantInfo) {
		int i = testConstantService.insert(testConstantInfo);
		if (i>0) {
			result.setObj("【"+testConstantInfo.getConstant_name()+"】新增成功");;
		} else {
			result.setObj("【"+testConstantInfo.getConstant_name()+"】新增失败");;
		}
        return result;
    }
}
