package com.uusoft.atp.service;

import java.util.Map;

import com.uusoft.atp.model.ParameterVo;

/** 
* 类说明 ：
* 
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2016年12月16日 下午6:37:19 
*/
public interface InitMethodService {
	
	Map<String,Object> getParameterMap(String serviceName,String methodName, String jsonData);
	ParameterVo getparameterVo(String serviceName,String methodName, String jsonData);
}
