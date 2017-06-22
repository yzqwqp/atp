package com.uusoft.atp.web.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uusoft.atp.demo.StudentDemo;
import com.uusoft.atp.model.TestDataInfo;
import com.uusoft.atp.service.TestDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class testdata {
	
	@Resource
	private TestDataService testdataservice;
	
	@Test
	public void test() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		StudentDemo sd=new StudentDemo(); 
		
		List<TestDataInfo> alldata = testdataservice.selectAll();
		int length = alldata.size();
		Object[] obj =new Object[length];
		int i = 0;
		for (TestDataInfo td : alldata) {
			System.out.println("Case_id :"+td.getCase_id()+ "  ||  Data_key :"+td.getPara_name() +"  ||  Data_value :"+td.getValue_data() + "  ||  Data_value_type :" + td.getPara_type());
			obj[i] = changeType(td.getValue_data(),td.getPara_type());
			System.out.println("obj get class is : " + obj[i].getClass());
			i++;
		}
		execute(sd,"print",obj);
		
		
	}
	
	public static Object changeType(Object obj, String strType){
		if(strType.compareTo("String") == 0){
			obj = String.valueOf(obj);
		} else if (strType.compareTo("Int") == 0){
			obj = Integer.parseInt(obj.toString());
			System.out.println(obj.getClass());
		}
		System.out.println("【obj to String is 】: " +obj.toString());
		
		return obj;
	}
	
	public static String execute1(StudentDemo stu, String methodname,List<TestDataInfo> data) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		int length = data.size();
		Object[] obj =new Object[length];
		Class[] objClass = new Class[length]; 
		int i = 0;
		for (TestDataInfo td : data) {
//			System.out.println("Case_id :"+td.getCase_id()+ "  ||  Data_key :"+td.getData_key() +"  ||  Data_value :"+td.getData_value() + "  ||  Data_value_type :" + td.getData_value_type());
			obj[i] = changeType(td.getValue_data(),td.getPara_type());
			objClass[i] = obj[i].getClass();
			i++;
		}
		
		Method method =stu.getClass().getMethod(methodname,objClass);
		return (String) method.invoke(stu, obj);
	}
	
	
	
	public static String execute(StudentDemo stu, String methodname, Object... args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class[] argsClass = new Class[args.length];   

        for (int i = 0, j = args.length; i < j; i++) {   
            argsClass[i] = args[i].getClass();
        } 
		
		Method method =stu.getClass().getMethod(methodname,argsClass);
		return (String) method.invoke(stu, args);
	}
	
//	public static void main(String[] args){
//		testdata td = new testdata();
//		td.test();
//	}
}
