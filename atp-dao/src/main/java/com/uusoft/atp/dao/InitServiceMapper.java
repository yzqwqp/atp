package com.uusoft.atp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.InitServiceInfo;

public interface InitServiceMapper {
	
	int insert(InitServiceInfo testServiceInfo);
	
	List<InitServiceInfo> selectAll();

	List<InitServiceInfo> selectAllService();
	
	List<InitServiceInfo> selectByName(String service_name);
	
	List<InitServiceInfo> selectById(@Param("method_id") int method_id);
	
	int updateById(InitServiceInfo initServiceInfo);
	
	List<Integer> selectMethodIdByNameAndService(@Param("serviceName") String serviceName,@Param("methodName")String methodName);
	
	int deledteinitservice();

	List<String> getmethodname(@Param("service_name")String service_name);
	
	List<InitServiceInfo> selectallname(@Param("serviceName") String serviceName,@Param("methodName")String methodName);
}
