package com.uusoft.atp.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uusoft.atp.dao.InitFieldMapper;
import com.uusoft.atp.dao.InitParaMapper;
import com.uusoft.atp.dao.InitServiceMapper;
import com.uusoft.atp.model.InitFieldInfo;
import com.uusoft.atp.model.InitParaInfo;
import com.uusoft.atp.model.InitServiceInfo;
import com.uusoft.atp.model.ParameterNameVo;
import com.uusoft.atp.model.TestDataVo;
import com.uusoft.atp.model.TestMethodInfo;
import com.uusoft.atp.service.InitServiceService;
import com.uusoft.atp.utils.ResultTool;
import com.uusoft.atp.utils.SpringUtil;

@Service("InitServiceService")
@Transactional
public class InitServiceServiceImpl implements InitServiceService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(InitServiceServiceImpl.class);
	
	@Resource
	private TestCaseServiceImpl testCaseServiceImpl;
	
	@Resource
	private InitServiceMapper mapper;
	
	@Resource
	private InitParaMapper pmapper;
	
	@Resource
	private InitFieldMapper  fmapper;
	
	@Transactional
	@Override
	public int insert() {
		LOGGER.info("清除初始化表各项数据");
		mapper.deledteinitservice();
		pmapper.deledteinitpara();
		fmapper.deledteinitfield();
		LOGGER.info("开始初始化服务数据 ");
		Map<String,String> map = SpringUtil.executeReferenceConfig();
		InitServiceInfo info = new InitServiceInfo();
		InitParaInfo pinfo = new InitParaInfo();
		for(Map.Entry<String, String> entry:map.entrySet()){			
			info.setService_name(entry.getKey());
			String entservice = entry.getValue();
			try {
				Class class1 =Class.forName(entservice);
				Method[] fields = class1.getMethods();
				for (Method field : fields) {// --for() begin
		    	    //Class[] carray = field.getParameterTypes(); 
		    	    Parameter[] parameters = field.getParameters();
		    	    info.setMethod_name(field.getName());
		    	    LOGGER.info("插入初始化服务列表......... ");
		    	    mapper.insert(info);
		    	    LOGGER.info("初始化服务完成...........");
		    	    for(Parameter cla : parameters) 
		   		    { 
		    	    	pinfo.setMethod_id(info.getMethod_id());
		    	    	pinfo.setPara_name(cla.getName());
		    	    	pinfo.setPara_type(cla.getType().getName());
		    	    	pmapper.insert(pinfo);
		    	    	String paraname = cla.getType().getName();
		    	    	if(paraname.contains("model")){
		    	    		InitFieldInfo finfo = new InitFieldInfo();
		    	    		Class clas =Class.forName(paraname);
		    	    		int i = 1;
		    	    		//判断导入info父类是否为object。为否循环获取父类属性
		    	    		do{
		    	    			Field[] fileds = clas.getDeclaredFields();
		    	    			//int i = 1;
								for(Field f : fileds){	
									//过滤掉属性serialVersionUID
									if(f.getName().equals("serialVersionUID")){
										continue;
									}else{
										finfo.setField_name(f.getName());
									}
									//修改取消class字段
									if(f.getGenericType().toString().contains("class")){
										finfo.setField_type(f.getGenericType().toString().split(" ")[1]);
									}else{
										finfo.setField_type(f.getGenericType().toString());
									}
									List<InitParaInfo> initpara = pmapper.selectParaid(pinfo.getMethod_id(),pinfo.getPara_type());
									finfo.setPara_id(initpara.get(0).getPara_id());
									finfo.setField_no(i);
									fmapper.insert(finfo);
									i++;
								}
								clas = clas.getSuperclass();
		    	    		}while(Object.class.isInstance(clas));
		    	    	}
		   		    } 
	    		}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	
		return  mapper.selectAll().size();
	}

	@Override
	public List<InitServiceInfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<InitServiceInfo> selectById(int service_id) {
		return mapper.selectById(service_id);
	}

	@Override
	public int updateById(InitServiceInfo initServiceInfo) {
		return mapper.updateById(initServiceInfo);
	}

	@Override
	public List<InitServiceInfo> selectByName(String service_name) {
		// TODO Auto-generated method stub
		return mapper.selectByName(service_name);
	}

	@Override
	public List<InitServiceInfo> selectAllService() {
		return mapper.selectAllService();
	}

	@Override
	public List<ParameterNameVo> getparaName(String serviceName, String methodName) {
		List<ParameterNameVo> vo1 = new ArrayList<ParameterNameVo>();
		List<Integer> a = mapper.selectMethodIdByNameAndService(serviceName, methodName);
		for(int at : a){
			ParameterNameVo vo = new ParameterNameVo();
			List<Map<String,String>> paramethod = new ArrayList<Map<String,String>>();
			List<Map<String,String>> fieldname = new ArrayList<Map<String,String>>();
			List<InitParaInfo> paraNameType = pmapper.selectParaByMethId(at);
			for(InitParaInfo info : paraNameType ){
				Map<String,String> map = new HashMap<String,String>();
				Map<String,String> fieldmap = new HashMap<String,String>();
				map.put(info.getPara_name(), info.getPara_type());
				List<InitFieldInfo> finfo = fmapper.selectById(info.getPara_id());
				if(finfo.isEmpty()||finfo == null){
					fieldmap.put("-1", "-1");
					fieldname.add(fieldmap);
					vo.setFieldname(fieldname);
				}else{
					for(InitFieldInfo ifinfo : finfo){
						fieldmap.put(ifinfo.getField_name(), ifinfo.getField_type());
					}
					vo.setFieldname(fieldname);
					fieldname.add(fieldmap);
				}
				paramethod.add(map);
				vo.setParamethod(paramethod);
				}
			vo1.add(vo);
		}
		
		return vo1;
	}

	@Override
	public List<TestDataVo> getdataname(String serviceName, String methodName) {
		List<TestDataVo>  vo1 = new ArrayList<TestDataVo>();
		
		List<ParameterNameVo> para = getparaName(serviceName, methodName);
		if(para != null){
			for(ParameterNameVo pv : para){
				
				List<Map<String, String>> paramethod = pv.getParamethod();
				List<Map<String, String>> fieldname = pv.getFieldname();
				if(paramethod!= null&&paramethod.size()==fieldname.size()){
					int len = paramethod.size();
					for(int i=0;i<len;i++){
						for(Entry<String, String> entry : paramethod.get(i).entrySet()) {
							for(Entry<String, String> entry1 : fieldname.get(i).entrySet()) {
								TestDataVo vo = new TestDataVo();
								vo.setParaname( entry.getKey());
								vo.setParatype(entry.getValue());
								vo.setFieldname(entry1.getKey());
								vo.setFieldtype(entry1.getValue());
								vo1.add(vo);
							}
						}	
					}
				}else if(paramethod==null){
					return null;
				}
			}
		}else{
			return null;
		}
		return vo1;
	}

	@Override
	public ResultTool<List<String>> getmethodname(String service_name) {
		List<InitServiceInfo> methodname = mapper.selectByName(service_name);
		List<String> methods = new ArrayList<String>();
		for (InitServiceInfo t:methodname) {
			methods.add(t.getMethod_name());
		}
		return ResultTool.setResult("0000", "查询成功", methods);
	}

	@Override
	public List<InitServiceInfo> getbyname(String serviceName, String methodName) {
		List<InitServiceInfo> getname = mapper.selectallname(serviceName, methodName);
		return getname;
	}


}
