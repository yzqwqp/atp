package com.uusoft.atp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsoneditor")
public class JsoneditorController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(JsoneditorController.class);
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return "jsoneditor/index";
	}
}
