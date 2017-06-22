package com.uusoft.atp.utils;
/** 
* 类说明 ：
* 	Spring实现类，用于调用容器内的实例化对象
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2016年12月13日 上午10:19:12 
*/
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;


public class SpringUtil  implements ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringUtil.class);
	
	public static ApplicationContext applicationContext; 
   
	 @Override
	 public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 SpringUtil.applicationContext = applicationContext;
	 }
	 
	 public static Object getBean(String name){
	     return applicationContext.getBean(name);
	 }
	 
    /**
     * invoke反射实现
     * @param serviceName
     * @param methodName
     * @param data
     * @return
     */
    public static String executeInvoke(String serviceName, String methodName, Class[] objClass, Object[] obj){
//    	public static String executeInvoke(String serviceName, String methodName, List<TestDataInfo> data){
    	Object webService = SpringUtil.getBean(serviceName);
//    	int length = data.size();
//		Object[] obj =new Object[length];
//		Class[] objClass = new Class[length]; 
//		int i = 0;
//		for (TestDataInfo td : data) {
//			System.out.println("Case_id :"+td.getCase_id()+ "  ||  Data_key :"+td.getData_key() +"  ||  Data_value :"+td.getData_value() + "  ||  Data_value_type :" + td.getData_value_type());
//			obj[i] = changeType(td.getData_value(),td.getData_value_type());
//			objClass[i] = obj[i].getClass();
//			i++;
//		}
		
		Method method;
		Object result = null;
		try {
			method = webService.getClass().getMethod(methodName, objClass);
			result = method.invoke(webService, obj);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return result.toString();
    }
    
    /**
     * 值类型转换
     * @param obj
     * @param strType
     * @return
     */
    public static Object changeType(Object obj, String strType){
		if(strType.compareTo("String") == 0){
			obj = String.valueOf(obj);
		} else if (strType.compareTo("Int") == 0){
			obj = Integer.parseInt(obj.toString());
			System.out.println(obj.getClass());
		}
		System.out.println("【obj to String is 】: " +obj.toString());
		System.out.println("【obj class is 】: " +obj.getClass());
		
		return obj;
	}
	
    /**
     * 获取Spring容器中的所有的dubbo注册的bean
     * @return
     */
    public static Map<String ,String> executeReferenceConfig() {
    	
//        Map<String, ConsumerConfig> consumerConfigMap = applicationContext == null ? null : BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, ConsumerConfig.class, false, false);  
//        Map<String, ModuleConfig> moduleConfigMap = applicationContext == null ? null : BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, ModuleConfig.class, false, false);
        Map<String, ReferenceConfig> referenceConfigMap = applicationContext == null ? null : BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, ReferenceConfig.class, false, false);
        Map<String ,String> map = new HashMap<String , String>() ;
        for (ReferenceConfig config : referenceConfigMap.values()) {  
        	map.put(config.getId(), config.getInterface());
        }
        return map;
        
    }
	
    /**
     * 	invoke泛化实现（调用的是dubbo的GenericService实现）
     * @author qiupeng
     * @param serviceName
     * @param methodName
     * @param paraValueClassObj
     * @param paraValuesObj
     * @return Object
     */
    public static ResultTool<Object> genericInvoke(String serviceName,String methodName, String[] paraValueClassObj, Object[] paraValuesObj) {
    	GenericService webService = (GenericService)  SpringUtil.getBean(serviceName);
    	try {
    		Object result = webService.$invoke(methodName, paraValueClassObj, paraValuesObj);
    		LOGGER.info("######"+serviceName+"的"+methodName+"执行genericInvoke方法成功######");
    		return ResultTool.setResult("0000", "执行成功", result);
    	} catch (Exception e){
    		LOGGER.error("######"+serviceName+"的"+methodName+"执行genericInvoke方法失败######");
    		LOGGER.error(e.toString());
    		return ResultTool.setResult("9999", "执行失败", null);
    	}
    }
}
