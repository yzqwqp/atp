package com.uusoft.atp.service.impl;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.xerces.util.SynchronizedSymbolTable;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.uusoft.atp.model.InitServiceInfo;
import com.uusoft.atp.model.ParameterNameVo;
import com.uusoft.atp.model.ParameterVo;
import com.uusoft.atp.model.TestDataVo;
import com.uusoft.atp.service.InitServiceService;
import com.uusoft.atp.service.TestServiceService;
import com.uusoft.atp.service.impl.InitServiceServiceImpl;
import com.uusoft.atp.utils.JsonUtil;
import com.uusoft.atp.utils.SpringUtil;
//import com.uusoft.trs.service.SpecialTradeService;
//import com.uusoft.trs.service.TrsAppService;
//import com.uusoft.meepo.service.IFofProductinfoService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class InitServiceServiceImplTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(InitServiceServiceImplTest.class);
	
	@Autowired
	private InitServiceServiceImpl impl;
	@Autowired
	private InitMethodServiceImpl mimpl;
	
	
	@Autowired
	private TestCaseServiceImpl testCaseServiceImpl;
	
	
	InitServiceInfo info = new InitServiceInfo();
	
	/**
	 * 测试导入数据
	 */
	@Test
	public void test01insert() {
		LOGGER.info("###########################");
		int i = impl.insert();
		Assert.assertTrue(i>=0);
		LOGGER.info("###########################");
	}
	
//	@Test
//	public void test002(){
//		Method[] declaredMethods = IFofProductinfoService.class.getMethods();
//		for (final Method method : declaredMethods){
//			System.out.println("%%%%%%%%%%%%");
//			System.out.println(method.getName());	
//			System.out.println("%%%%%%%%%%%%");
//			Parameter[] parameters = method.getParameters();
//			for (Parameter parameter : parameters){
//				System.out.println("********************************");
//				System.out.println("parameterName="+parameter.getName());
//				System.out.println("type="+parameter.getType().getName());
//				System.out.println("********************************");
//			}
//		}
//	}
	
	@Test
	public void test003(){
		 try {	
	           Class clazz = Class.forName("com.uusoft.atp.service.impl.InitServiceServiceImpl");
	           Method[] methods = clazz.getMethods();
	           for (Method method : methods) {
	               String methodName = method.getName();
	               System.out.println("方法名称:" + methodName);
	               Class<?>[] parameterTypes = method.getParameterTypes();
	               for (Class<?> clas : parameterTypes) {
	                   String parameterName = clas.getName();
	                   System.out.println("参数名称:" + parameterName);
	               }
	               System.out.println("*****************************");
	           }
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }
	   }
	
	/*@Test
	public void test004(){		
		String serviceName = "merchantService";
		String methodName = "getMerchantByNo";
		String jsonData = "{\"merchantno\":\"HT0000001\"}";
		ParameterVo vo = new ParameterVo();
		List<List<String>> paramTypes = testCaseServiceImpl.selectParasByMethod(serviceName,methodName);
		List<String> paraType = new ArrayList<>();
		JSONObject jsonobj = JSONObject.parseObject(jsonData);
		System.out.println(jsonobj);
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
			for(int i=0;i<paraType.size();i++){
				arr[i] = paraType.get(i);
			}
			vo.setParamTypes(arr);
			
			List<Object> list = new ArrayList<>();
			for(Entry<String, Object> entry : jsonobj.entrySet()){
				list.add(entry.getValue());
				Object[] arr1 = list.toArray();
				vo.setParamValues(arr1);
			}
			
			//Object[] paramValues = JsonUtil.json2Object(jsonData, paraType);
//			vo.setParamValues(paramValues);
		}
		SpringUtil su = new SpringUtil();
//		ParameterVo vv = mimpl.getparameterVo(serviceName, methodName, jsonData);
		Object result = su.genericInvoke(serviceName, methodName, vo.getParamTypes(), vo.getParamValues());
		System.out.println(vo.getParamValues());
		System.out.println("********测试数据："+result.toString());
	}*/
	
	@Test
	public void testgetparameterVo(){
		//System.out.println(JSON.toJSONString(impl.getparaName("custService", "merchantCheck")));
		List<ParameterNameVo> vo2 = impl.getparaName("custService", "openAcct");
		for(ParameterNameVo vo : vo2){
			System.out.println("******************");
			System.out.println(JSON.toJSONString(vo));
			System.out.println("******************");
		}
		
	}
	
	@Test
	public void testgetparameterVo1(){
		//System.out.println(JSON.toJSONString(impl.getparaName("custService", "merchantCheck")));
		List<TestDataVo> vo2 = impl.getdataname("custService", "openAcct");
		
		for(TestDataVo vo : vo2){
			System.out.println("******************");
			System.out.println(JSON.toJSONString(vo));
			System.out.println("******************");
		}
		
	}
	
	@Test
	public void testgetxml(){
		try {  
		String spath=System.getProperty("user.dir")+"\\src\\main\\resources\\spring\\";
		System.out.println(spath);
		File f = new File(spath+"dubbo-consumer.xml");
		SAXReader reader = new SAXReader();   
	    Document doc = reader.read(f);   
		Element root = doc.getRootElement();   
		Element foo;  
		List<Element> booklist = root.elements();
		for(Element sonbooklist:booklist){
			System.out.println(sonbooklist.getName());
			System.out.println(sonbooklist.attributeCount());
			List<Attribute> bookAttrs = sonbooklist.attributes();
			for (Attribute attr : bookAttrs) {
				System.out.println("属性名：" + attr.getName() + "--属性值：" + attr.getValue());
		    }
			
		}
		}catch (Exception e) {   
			 	e.printStackTrace();   
		} 
	}
	
	@Test
	public void testmock(){
		List  mock = Mockito.mock(List.class);
		mock.add("one");
//		Mockito.when(mock.get(Mockito.anyInt())).thenReturn("sss").thenReturn("fuc");	
//		System.out.println("***************"+mock.get(0));
//		System.out.println("***************"+mock.get(1));
//		System.out.println("***************"+mock.get(3));
//		System.out.println(Mockito.verify(mock.get(0)));
		Mockito.verify(mock).add("one");
	}	
	
	@Test
	public void testmoco(){
		
	}
}
	
