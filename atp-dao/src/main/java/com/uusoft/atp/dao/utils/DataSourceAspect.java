package com.uusoft.atp.dao.utils;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class DataSourceAspect implements MethodBeforeAdvice,AfterReturningAdvice
{

    @Override
    public void afterReturning(Object returnValue, Method method,
                               Object[] args, Object target) throws Throwable {

    	DynamicDataSource.clearCustomerType();
    }

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {

        //首先取类上的数据源
        if(method.getDeclaringClass().isAnnotationPresent(DataSource.class) && !method.isAnnotationPresent(DataSource.class)) {

            DataSource datasource = method.getDeclaringClass().getAnnotation(DataSource.class);
            DynamicDataSource.setCustomerType(datasource.name());

            //方法上的数据源 优先级高于类上的
        } else if (method.isAnnotationPresent(DataSource.class)) {

            DataSource datasource = method.getAnnotation(DataSource.class);
            DynamicDataSource.setCustomerType(datasource.name());
        }
        else
        {
        	DynamicDataSource.setCustomerType(DataSource.ATP_DATASOURCE);
        }
    }
}
