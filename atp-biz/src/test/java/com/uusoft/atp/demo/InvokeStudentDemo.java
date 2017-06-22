package com.uusoft.atp.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeStudentDemo {
	public static void main(String[] args) throws  IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {  
          
//        sd.setId(1);  
//        sd.setName("测试invoke");  
//        sd.setSex("未知");  
//        sd.setAge("10000");  
//        sd.setBrithday("19920205");  
//        sd.setAddress("湖南");  
//        Object[] agrs1 = new Object[]{};  
//        StringBuilder sb = new StringBuilder();  
//        sb.append("<Object.XmlString> start");  
//        for (Method m : sd.getClass().getMethods()) {  
//            if (m.getName().startsWith("get")) {  
//                System.out.println(m.getName());  
//                sb.append("  <" + m.getName().substring(3) + ">");  
//                sb.append(m.invoke(sd,agrs1));  
//                System.out.println("---"+m.invoke(sd, agrs1));  
//            }  
//        }  
//        System.out.println(sb.toString());  
        
        
        
        // 调用 print(String string)
//        Method method1 = sd.getClass().getMethod("print", String.class);
//        method1.invoke(sd, "a");
        
        // 调用 print(String ... strings)
//        Method method2 = sd.getClass().getMethod("print", Array.newInstance(String.class, 0).getClass());
//        method2.invoke(sd, new String[]{"a", "b"});
        
//        SpringUtil.execute("sd", "print", "[{\"str1\":\"test1\",\"str2\":\"test2\"}]");
//        String param = "{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"}";
//        JSONArray array = JSONArray.fromObject("[ {\"UserID\":\"XXXXXX\",\"CID\":\"1\",\"Start\":\"1371644311\",\"End\":\"1410220800\"}]");
//        JSONObject jsonParam = (JSONObject)JSONValue.parse(param);
//        JSONObject obj=JSONArray.parseObject(param);
             
        /*Set set = obj.keySet();
        for (Object key : set) {
            System.out.println(key);
        }*/
        
//        		parse(param);
//        Method method = sd.getClass().getMethod("print",JSONObject.class);
//        method.invoke(sd, jsonParam);
//        execute(sd,"print",obj);
//        Object[] args1 = Object();
//    } 
	
	/*public static void execute(StudentDemo stu, String methodname, JSONObject args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Class[] argsClass = new Class[args.length];   

        for (int i = 0, j = args.length; i < j; i++) {   
            argsClass[i] = args[i].getClass();
        }   
		Method method = stu.getClass().getMethod(methodname, String.class);
		method.invoke(stu, args);
		
	}*/
	
	/*
	public static void json2HashMap(String key, Object value,  
            List<Map<?, ?>> rstList) {  
        HashMap<String, Object> map = new HashMap<String, Object>();  
        map.put(key, value);  
        rstList.add(map);  
    } */
	
	
		StudentDemo sd=new StudentDemo(); 
//	String param = "{\"mobilePhone\":\"13510398031\",\"userName\":\"李四\"}";
	
	String sqlStr = "select * from test_data a where a.test_case_id = '12'";
	Object[] valueStr = SqlUtil.executeSql(SqlUtil.openConn("mysql","atp"), sqlStr, "value");
	
	
	Object[] strArray={"1","2",3};
	
	Object[] a =new Object[3];
	a[0] = strArray[0];
	a[1] = strArray[1];
	a[2] = strArray[2];
	
	
//	execute(sd,"print", a);
	execute(sd,"print2",valueStr);
	}
	public static String execute(StudentDemo stu, String methodname, Object... args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class[] argsClass = new Class[args.length];   

        for (int i = 0, j = args.length; i < j; i++) {   
            argsClass[i] = args[i].getClass();
        } 
		
		Method method =stu.getClass().getMethod(methodname,argsClass);
		return (String) method.invoke(stu, args);
	}
	
}
