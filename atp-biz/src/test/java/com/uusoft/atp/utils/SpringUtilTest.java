package com.uusoft.atp.utils;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.atp.model.ParameterVo;
import com.uusoft.atp.service.impl.InitMethodServiceImpl;

/** 
* 类说明 ：
* 
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2016年12月20日 下午9:18:14 
*/
/**
 * @author qiupeng
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class SpringUtilTest  implements Serializable {
	private static final long serialVersionUID = 1L;
	SpringUtil springUtil = new SpringUtil();
	
	@Resource
	private InitMethodServiceImpl impl;
	
	@Test
	public void testGenericInvoke() {
		
		String serviceName = "userService";
		String methodName = "getUser";
		String jsonData = "{\"userId\":\"a7db82cef0d140918a60387dcd7d91c5\"}";
		
//		HashMap<String, Object> reMap = (HashMap<String, Object>) impl.getParameterMap(serviceName,methodName, jsonData);
//		List<String> paraValueClassObj=(List<String>) reMap.get("paraTypes");
//		String[] paraValueClassObjs = new String[paraValueClassObj.size()];
//		paraValueClassObj.toArray(paraValueClassObjs);
//		for(int i=0,len=paraValueClassObjs.length;i<len;i++)
//			System.out.println("***********paraTypes"+i+paraValueClassObjs[i]);
//		Object[] paraValuesObj = (Object[]) reMap.get("paraValues");
//		for(int j=0;j<paraValuesObj.length;j++)
//			System.out.println("############paraValues"+j+paraValuesObj[j]);
//		
//		springUtil.genericInvoke(serviceName, methodName, paraValueClassObjs, paraValuesObj); 
		
		ParameterVo parameterVo = impl.getparameterVo(serviceName,methodName, jsonData);
		String[] strTypes= parameterVo.getParamTypes();
		Object[] objValues= parameterVo.getParamValues();
		
		for (int i=0;i<strTypes.length;i++) {
			System.out.println(strTypes[i]);
		}
		
		for (int j=0;j<strTypes.length;j++) {
			System.out.println(objValues[j].toString());
		}
		
		
		Object result = springUtil.genericInvoke(serviceName, methodName, strTypes, objValues);
//		Object result = springUtil.genericInvoke("userService", "getUser", new String[]{"java.lang.String"}, new Object[]{"a7db82cef0d140918a60387dcd7d91c5"});
		System.out.println("############result is :" + result.toString());
	}
	
	
	/**
	 * 测试冯灏的投投钱包消费新接口
	 */
	@Test
	public void testNonstandardInterface() {
		//Result<Map<String, Object>> unionConsumeByFirmId(BigDecimal amount, String moneyAcc, String userId, String serialNum, String firmId) 
		//BigDecimal amount, String moneyAcc, String userId, String serialNum, String firmId;
		String[] str = new String[5];
		str[0] = "java.math.BigDecimal";//金额
		str[1] = "java.lang.String";//资金账号
		str[2] = "java.lang.String";//userID
		str[3] = "java.lang.String";//serialNum
		str[4] = "java.lang.String";//firmID;
		System.out.println(str[0]);
		System.out.println(str[1]);
		System.out.println(str[2]);
		System.out.println(str[3]);
		System.out.println(str[4]);
		
		
		
		Object[] obj = new Object[5];
		BigDecimal bd=new BigDecimal("1000");
		obj[0] = 5.99;//金额
		obj[1] = String.valueOf("247481");//资金账号
		obj[2] = String.valueOf("1b113785baba47adb428171c378aea31");//userID
		obj[3] = String.valueOf("AA2ED973-EACA-4703-B841");//serialNum
		obj[4] = String.valueOf("LT0000001");//firmID;
		System.out.println(obj[0]);
		System.out.println(obj[1]);
		System.out.println(obj[2]);
		System.out.println(obj[3]);
		System.out.println(obj[4]);
		
		
		Object result = SpringUtil.genericInvoke("nonstandardTradeService", "unionConsumeByFirmId",  str, obj);
		System.out.println("###########");
		System.out.println(result.toString());
		System.out.println("###########");
	}

}
