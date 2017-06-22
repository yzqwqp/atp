package com.uusoft.atp.service;

import java.util.List;

import com.uusoft.atp.model.InitServiceInfo;
import com.uusoft.atp.model.ParameterNameVo;
import com.uusoft.atp.model.TestDataVo;
import com.uusoft.atp.utils.ResultTool;

public interface InitServiceService {
	
	/**
	 * @author guishuai
	 * <p>Description:初始化服务，方法，参数类型</p>
	 * @return
	 */
	int insert();
	
	List<InitServiceInfo> selectAll();
	
	List<InitServiceInfo> selectById(int service_id);
	
	int updateById(InitServiceInfo initServiceInfo);
	
	List<InitServiceInfo> selectByName(String service_name);
	
	List<InitServiceInfo> selectAllService();
	
	/**
	 * <p>Description:根据服务名称和方法名称，查询对应的入参和类型</p>
	 * @param serviceName
	 * @param methodName
	 * @return
	 */
	List<ParameterNameVo> getparaName(String serviceName,String methodName);
	
	List<TestDataVo> getdataname(String serviceName,String methodName); 
	
	ResultTool<List<String>> getmethodname(String service_name);
	
	List<InitServiceInfo> getbyname(String serviceName,String methodName );
}
