package com.uusoft.atp.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.uusoft.atp.model.InitParaInfo;

public interface InitParaMapper {
	
	int insert(InitParaInfo testServiceInfo);
	
	List<InitParaInfo> selectAll();
	
	List<InitParaInfo> selectById(@Param("para_id") int para_id);
	
	int updateById(InitParaInfo initParaInfo);
	
	List<String> selectParaTypesByMethId(@Param("methodId")int methodId);
	
	List<InitParaInfo> selectParaid(int method_id,String para_type);
	
	List<InitParaInfo> selectParaByMethId(@Param("methodId")int methodId);
	
	int deledteinitpara();
	/**
	 * 通过方法ID查询，返回结果TreeMap<paraName,paraType>
	 * @param methodId
	 * @return
	 * author 邱鹏
	 */
	List<LinkedHashMap<String, String>> selectParaLinkedHashMap(@Param("methodId")int methodId);
}
