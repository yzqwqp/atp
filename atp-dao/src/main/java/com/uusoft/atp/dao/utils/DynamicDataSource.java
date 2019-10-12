package com.uusoft.atp.dao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 配置多数据源 qiupeng 2019-07-24
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private final static Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

	// 配置文件 applicationContext-mybatis.xml 中设置的两个"key"名称
//	public static final String DATASOURCE_ATP = "atpDataSource";
//	public static final String DATASOURCE_APPTMS = "apptmsDataSource";
	// 本地线程，获取当前正在执行的currentThread
	public static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		// 把当前请求的参数，存入当前线程，这个参数是我们定义的
//		LOGGER.info("### 当前切换的数据源是=" + customerType);
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	// 切换默认数据源
	public static void clearCustomerType() {
		contextHolder.remove();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getCustomerType();
	}
}