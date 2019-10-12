package com.uusoft.atp.dao.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataSource {
	String name() default DataSource.ATP_DATASOURCE;
	String ATP_DATASOURCE = "atpDataSource";
	String APPTMS_DATASOURCE = "apptmsDataSource";
}
