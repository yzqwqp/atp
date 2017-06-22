package com.uusoft.atp.demo;
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
//import org.apache.commons.beanutils.BeanUtils;  

public class Map2ObjUtil {
	
	  
	  
	/** 
	 * Map转Object 工具类 
	 * @author JIHAN 
	 * 
	 */  
	  
	    public static Object populateBean(Map map, Object obj) throws Exception{  
//	        BeanUtils.populate(obj, map);  
	        return obj;  
	    }  
	      
	    public static List<Object> popListToList(Object object, List list) throws Exception{  
	          
	        List<Object> listRetun = new ArrayList<Object>();  
	        for (int i = 0; i < list.size(); i++) {  
	            Object objectTemp = object.getClass().newInstance();  
//	            BeanUtil.populateBean((Map) list.get(i), objectTemp);  
	            listRetun.add(objectTemp);  
	        }  
	        return listRetun;  
	    }  
}
