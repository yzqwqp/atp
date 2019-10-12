package com.uusoft.atp.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uusoft.atp.model.UserInfo;
import com.uusoft.atp.service.LoginService;

@Controller
@RequestMapping("/testservice1")
public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	public String result="";
	
	@Resource
	LoginService loginService;

	@RequestMapping(value= "/login",produces="application/text; charset=UTF-8")
	@ResponseBody
	public String login(QueryBean qb) throws Exception{
		String userName = qb.getUserName();
		String userPwd = qb.getPwd();
		UserInfo userInfo = loginService.selectByLoginName(userName);
		if(userInfo!=null) {
			String userPwdBySql = userInfo.getLoginPasswd();
			if(userPwd.equals(userPwdBySql)) {
				result="登录成功";
				session.setAttribute("user", userInfo);
			}else {
				result="密码错误";
			}
		}else {
			result="没有此用户";
		}
		return result;	
	}	
}
