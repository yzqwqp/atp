package com.uusoft.atp.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.service.impl.TestCaseServiceImpl;

/** 
* 类说明 ：
* 
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2017年1月23日 下午5:03:55 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class JsonUtilTest {
	private static final  Logger LOGGER = LoggerFactory.getLogger(JsonUtilTest.class);
	
	@Test
	public void test(){
		LOGGER.info("test");	
	}
	
	@Resource
	private TestCaseServiceImpl testCaseServiceImpl;

	@Test
	//TODO
	//翠萍没有做基本类型数据的类型转换
	public void fastjson2ObjectTest2() {
		//(String json, List<String> paras)
		String jsonData = "{\"arg0\":\"1000\",\"arg1\":\"247481\",\"arg2\":\"1b113785baba47adb428171c378aea31\",\"arg3\":\"AA2ED973-EACA-4703-B836\",\"arg4\":\"LT0000001\"}";
		
		String[] str = new String[5];
		str[0] = BigDecimal.class.toString();//金额
		str[1] = String.class.toString();//资金账号
		str[2] = String.class.toString();//userID
		str[3] = String.class.toString();//serialNum
		str[4] = String.class.toString();//firmID;
		
		List<String> paras = Arrays.asList(str);
		List<Object> list = new ArrayList<>();//保存入参的值
		JSONObject jsonobj = JSONObject.parseObject(jsonData);
		Object[] arr = new Object[paras.size()];
		int i =0;
		for(Entry<String, Object> entry : jsonobj.entrySet()){
			if(paras.get(i).startsWith("com")){//如果是
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject object = (JSONObject) entry.getValue();
				for(Entry<String, Object> entry2 : object.entrySet()){
					LOGGER.info(entry2.getValue().toString());
					LOGGER.info(entry2.getValue().getClass().toString());
					map.put(entry2.getKey(), entry2.getValue());
				}
				if(!map.isEmpty()){
					list.add(map);
				}
			}
			else{
				LOGGER.info(entry.getValue().toString());
				LOGGER.info(entry.getValue().getClass().toString());
				list.add(entry.getValue());
			}
			i++;
		}
		arr= list.toArray();
	}
	
	@Test
	public void fastjson2ObjectTest3() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("arg0", "java.math.BigDecimal");
		map.put("arg1", "java.lang.String");
		map.put("arg2", "java.lang.String");
		map.put("arg3", "java.lang.String");
		map.put("arg4", "java.lang.String");
		
		String jsonData = "{\"arg0\":\"1000\",\"arg1\":\"247481\",\"arg2\":\"1b113785baba47adb428171c378aea31\",\"arg3\":\"AA2ED973-EACA-4703-B836\",\"arg4\":\"LT0000001\"}";
		Object[] obj = JsonUtil.fastjson2Object2(jsonData, map);
		for (int i=0; i < obj.length; i++){
			LOGGER.info(obj[i].toString() +"    "+obj[i].getClass());
		}
			
	}
	
	
	
}
