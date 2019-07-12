/**   
 * <p>Title: InitMethodServiceImpl.java</p>
 * @Package com.uusoft.atp.service.impl 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author Adele
 * @since 2016年12月16日 下午8:48:59 
 * @version V1.0   
 */
package com.uusoft.atp.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.model.ParameterVo;
import com.uusoft.atp.service.InitMethodService;
import com.uusoft.atp.utils.JsonUtil;

/** 
 * <p>Description: 提供接口实现，json转对象</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
@Service("InitMethodService")
@Transactional
public class InitMethodServiceImpl implements InitMethodService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(InitMethodServiceImpl.class);
	/** 
	 * <p>Description:Json转map</p>
	 * @return  Map<String, Object[]>,返回的Map中，两个Key定义为"paraTypes"，对应参数类型，"paraValues"对应参数值
	 * @author Adele
	 * @date 2016年12月17日 下午1:07:37   
	 */
	
	@Resource
	private TestCaseServiceImpl testCaseServiceImpl;
	
	@Override
	public Map<String, Object> getParameterMap(String serviceName,String method, String jsonData){
		Map<String, Object> map = new HashMap<String, Object>();
//		List<String> paraTypes = testCaseServiceImpl.selectParasByMethod(serviceName,method);
//		if(paraTypes.isEmpty())
//			LOGGER.info("返回的参数类型为空！");
//		else{
//			Object[] paraValues = JsonUtil.json2Object(jsonData, paraTypes);
//			map.put("paraTypes", paraTypes);
//			map.put("paraValues", paraValues);
//		}
		return map;
	}

	public ParameterVo getparameterVo(String serviceName,String method, String jsonData){
		ParameterVo vo = new ParameterVo();
		
//		List<LinkedHashMap<String, String>> paramTypes = testCaseServiceImpl.selectParasByMethod(serviceName,method);
		LinkedHashMap<String, String> paraType = new LinkedHashMap<String, String>();//用来存储paramTypes里面与json对应的参数类型的
		JSONObject jsonobj = JSONObject.parseObject(jsonData);
		int jsonLenth = jsonobj.size();
//		if(paramTypes.isEmpty())
			LOGGER.info("返回的参数类型为空！");
//		else{
			//paramTypes is :
			//[{para_name=arg0, para_type=java.math.BigDecimal}, {para_name=arg1, para_type=java.lang.String}, {para_name=arg2, para_type=java.lang.String}, {para_name=arg3, para_type=java.lang.String}, {para_name=arg4, para_type=java.lang.String}]
			/*for(LinkedHashMap<String, String> ptype:paramTypes)//遍历Map
			{
				//TODO 需要校验一下这边的逻辑
				if(ptype.size()==jsonLenth){//重载方法参数类型要与json匹配
					paraType = ptype;//json要转成此类型
					break;
				}
			}*/
			Set<String> set = paraType.keySet();
			String[] arr = new String[jsonLenth];//List转array
			int i = 0;
			LOGGER.info("从数据库撸出paramTypes，开始打印");
			for (String s:set) {
				arr[i++] = paraType.get(s);
			}
/*			for(int i=0;i<paraType.size();i++){
				arr[i] = paraType.get(i);
				LOGGER.info(arr[i]);
			}
*/			
			vo.setParamTypes(arr);
			LOGGER.info("传入jsonData和paraTyeList，转换成obj数组，打印开始");
			Object[] paramValues = JsonUtil.fastjson2Object2(jsonData, paraType);
			vo.setParamValues(paramValues);
//		}
		return vo;
	}
	
	//TODO
	//邱鹏 20170124修改，使用treeMap对存入值进行优化
	/*	public ParameterVo getparameterVo2(String serviceName,String method, String jsonData){
		ParameterVo vo = new ParameterVo();
		List<List<String>> paramTypes = testCaseServiceImpl.selectParasByMethod(serviceName,method);
		TreeMap<String, String> map = new TreeMap<String, String>();
		List<String> paraType = new ArrayList<>();//用来存储paramTypes里面与json对应的参数类型的
		JSONObject jsonobj = JSONObject.parseObject(jsonData);
		int jsonLenth = jsonobj.size();
		if(paramTypes.isEmpty())
			LOGGER.info("返回的参数类型为空！");
		else{
			for(List<String> ptype:paramTypes)//遍历Map
			{
				if(ptype.size()==jsonLenth){//重载方法参数类型要与json匹配
					paraType = ptype;//json要转成此类型
					break;
				}
			}
			String[] arr = new String[jsonLenth];//List转array
			LOGGER.info("从数据库撸出paramTypes，开始打印");
			for(int i=0;i<paraType.size();i++){
				arr[i] = paraType.get(i);
				LOGGER.info(arr[i]);
			}
			vo.setParamTypes(arr);
			LOGGER.info("传入jsonData和paraTyeList，转换成obj数组，打印开始");
			Object[] paramValues = JsonUtil.fastjson2Object(jsonData, paraType);
			vo.setParamValues(paramValues);
		}
		return vo;
	}*/
	
	
}
