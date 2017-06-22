package com.uusoft.atp.demo;

import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

public class StudentDemo implements GenericService  {
	
	public void pStudent(String str)
    {
        System.out.println("str is :" +"str");
    }

    public void printStudent(String userName, String mobilePhone, Integer age)
    {
    	System.out.println("StudentDemo print is begin>>>>");
        System.out.println("mobilePhone is :" + mobilePhone);
        System.out.println("userName is :" + userName);
        System.out.println("age is :" + age);
        
    }
	
	
	
	private int id;  
    private String name;  
    private String sex;  
    private String age;  
    private String brithday;  
    private String address;
    
    public StudentDemo() {  
    }  
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getBrithday() {
		return brithday;
	}
	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Object $invoke(String arg0, String[] arg1, Object[] arg2) throws GenericException {
		// TODO Auto-generated method stub
		return null;
	}  
	
	
	
	
	
	
}
