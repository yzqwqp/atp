package com.uusoft.atp.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.dao.TestCaseMapper;
import com.uusoft.atp.dao.TestDataMapper;
import com.uusoft.atp.model.TestDataInfo;

/** 
* 类说明 ：
* 
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2017年2月10日 下午3:30:15 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@Transactional
public class TestDataServiceImplTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestDataServiceImplTest.class);

	@Resource
	private TestDataServiceImpl testDataImp;
	@Resource
	private TestDataMapper dataMapper;
	
	@Test
	public void test() {
		List<TestDataInfo> dataList = dataMapper.selectCaseValue(10);
		//LinkedHashMap<String,String>
		//LinkedHashMap<arg0,classStr>
		LinkedHashMap<String, String> classMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		List<String> tmpList = new ArrayList<String>();
		//Set<String> set = paraType.keySet();
		/*
		 * for (String s:set) {
				arr[i++] = paraType.get(s);
			}
		 */
		String paraName = null;
		String paraType = null;
		String paraValue = null;
		for (TestDataInfo t:dataList) {
			LOGGER.info("para_name: "+t.getPara_name()+" para_type："+t.getPara_type()
					+" Field_name: "+t.getField_name()+" Field_type: "+t.getField_type()
					+" Value_data: "+t.getValue_data());
			paraName = t.getPara_name();
			paraType = t.getPara_type();
			//判断是否包含para_name,防重
			if (!classMap.containsKey(paraName)) {
				classMap.put(paraName, paraType);
			}
			//计算次数
			tmpList.add(paraName);
		}
		
		LOGGER.info("tmpList : " +tmpList.size());
		
		//classMap好了之后拉出一个paraname的数组
		Object[] paraArr = classMap.keySet().toArray();
		for (Object o : paraArr) {
			LOGGER.info(o.toString());
			//判断para_name出现多次
			if(isInfo(o.toString(),tmpList)){
				//传入o.toString()--paraname,拼装成map返回
				createMap(o.toString(),dataList);
			} else {//出现了1次
				//基础类型进行值转换
//				changeValue(o.toString(), dataList);
			}
				
		}
			
		
		//classMap好了之后进行循环
		for (Map.Entry<String, String> entry : classMap.entrySet()) {
			LOGGER.info("##########");
			LOGGER.info(entry.getKey());
			for (TestDataInfo t:dataList) {
				//判断循环中Para_name与classMap的paraname一样
				
				
				
				if (entry.getKey() == t.getPara_name()) {
					
				}
			}
		}
		
		
		/*Iterator<Entry<String, String>> iterator= classMap.entrySet().iterator();  
		while(iterator.hasNext())  
		{  
			LOGGER.info("##################");
			LOGGER.info(classMap.get(iterator));
			LOGGER.info("##################");
		}  */
		
		
		
//		paraValue = t.getValue_data();
		valueMap.put(paraName, paraValue);

		//把List<TestDataInfo>变成两个数组
		//A数组：String[para_type]
		//B数组：Object[Value_data],Value_data需要根据Para_type进行类型转换（比如BigDecimal的需要转成对应BigDecimal的类型）
		//B数组：基本数据类型需要转换，map不需要转换
		
		
		
		
	}
	
	private boolean isInfo(String str, List<String> strList) {
		int i = 0;
		for (String s : strList) {
			if (s.equals(str))
				i++;
		}
		LOGGER.info(str + "出现了 : " + i +"次");
		return i > 1 ? true :false;
	}
	
	private LinkedHashMap<String, String> createMap(String str, List<TestDataInfo> dataList) {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		for (TestDataInfo t:dataList) {
			if(t.getPara_name().equals(str)){
				map.put(t.getField_name(), t.getValue_data());
			}
		}
		return map;
	}
	
}
