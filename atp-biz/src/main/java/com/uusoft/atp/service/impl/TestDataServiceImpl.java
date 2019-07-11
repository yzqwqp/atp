package com.uusoft.atp.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.uusoft.atp.dao.TestCaseMapper;
import com.uusoft.atp.dao.TestDataMapper;
import com.uusoft.atp.model.ParameterVo;
import com.uusoft.atp.model.TestCaseVo;
import com.uusoft.atp.model.TestDataInfo;
import com.uusoft.atp.service.TestDataService;
import com.uusoft.atp.utils.JsonUtil;
import com.uusoft.atp.utils.ResultTool;

@Service("TestDataService")
@Transactional
public class TestDataServiceImpl implements TestDataService {

	private final static Logger LOGGER = LoggerFactory.getLogger(TestDataServiceImpl.class);

	@Resource
	TestDataMapper mapper;
	
	@Resource
	TestCaseMapper caseMapper;

	ParameterVo vo = new ParameterVo();
	
	List<TestDataInfo> dataInfoList = new ArrayList<TestDataInfo>();
	
	@Override
	public int insert(TestDataInfo testDataInfo) {
		return mapper.insert(testDataInfo);
	}

	@Override
	public List<TestDataInfo> selectAll() {
		return mapper.selectAll();
	}

//	@Override
//	public ResultTool<List<TestDataInfo>> selectById(int case_id) {
//		dataInfoList = mapper.selectById(case_id);
//		if (dataInfoList.size() > 0) {
//			return ResultTool.setResult("0000", "查询成功", dataInfoList);
//		} else {
//			TestCaseVo caseVo = caseMapper.selectByCaseId(case_id);
//			int i = mapper.insertInitTestData(case_id, caseVo.getService_name(), caseVo.getMethod_name());
//			if ( i > 0) {
//				dataInfoList = mapper.selectById(case_id);
//				return ResultTool.setResult("0000", "查询成功", dataInfoList);
//			} else {
//				return ResultTool.setResult("9999", "init初始表中无相关数据", dataInfoList);
//			}
//		}
//		
//	}

	@Override
	public int updateById(TestDataInfo testDataInfo) {
		return mapper.updateById(testDataInfo);
	}

	@Override
	public List<TestDataInfo> selectCaseValue(int case_id) {
		return mapper.selectCaseValue(case_id);
	}
	
	public ParameterVo parseTestData(int case_id) {
		List<TestDataInfo> dataList = mapper.selectCaseValue(case_id);
		
		//使用三层map实现
		//第一层LinkedHashMap<int ,LinkedHashMap<k,v>>,
		//例如：LinkedHashMap<case_id,LinkedHashMap<k,v>>
		LinkedHashMap<Integer, LinkedHashMap<String, Object>> oneMap = new LinkedHashMap<Integer, LinkedHashMap<String, Object>>();
		//第二层LinkedHashMap<String ,LinkedHashMap<k,v>>,
		//例如：LinkedHashMap<arg0,StringValue>
		//例如：LinkedHashMap<arg0,LinkHashMap<k,v>>
		LinkedHashMap<String, Object> twoMap = new LinkedHashMap<String, Object>();
		//第三层LinkedHashMap<String ,String>
//		LinkedHashMap<String, String> threeMap = new LinkedHashMap<String, String>();
				
		//先做初始化操作，生成一个class
		LinkedHashMap<String, String> classMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		List<String> tmpList = new ArrayList<String>();
		String paraName = null;
		String paraType = null;
		String paraValue = null;
		for (TestDataInfo t:dataList) {
/*			LOGGER.info("para_name: "+t.getPara_name()+" para_type："+t.getPara_type()
					+" Field_name: "+t.getField_name()+" Field_type: "+t.getField_type()
					+" Value_data: "+t.getValue_data());*/
			paraName = t.getPara_name();
			paraType = t.getPara_type();
			//判断是否包含para_name,防重
			if (!classMap.containsKey(paraName)) {
				classMap.put(paraName, paraType);
			}
			//计算次数
			tmpList.add(paraName);
		}
		
		//再次循环dataList，装载twoMap，判断paraName在tmpList列表里面出现的次数
		for (TestDataInfo t:dataList) {
			String tmpParaName = t.getPara_name();
			if(isInfo(tmpParaName,tmpList)){//大于一次
				threeMap(twoMap, tmpParaName, t.getField_name(), t.getValue_data());
			} else {
				twoMap.put(tmpParaName, t.getValue_data());
			}
		}
		oneMap.put(case_id, twoMap);
		LOGGER.info("打印oneMap:【" + oneMap.toString() + "】");
		
		//classMap好了之后拉出一个paraname的数组
		String[] paraArr = classMap.values().toArray(new String[classMap.size()]);
		//生成paraname的class数组，对应的value数组
		Object[] valueObj = invokeValueData(classMap,twoMap);
		
		vo.setParamTypes(paraArr);
		vo.setParamValues(valueObj);
		return vo;
	}
	
	private Object[] invokeValueData(LinkedHashMap<String, String> classMap, LinkedHashMap<String, Object> twoMap) {
		//把twoMap转成json
		String json = JSON.toJSONString(twoMap);
		LOGGER.info("打印twoMap的jsonStr:【"+ json +"】");
		//classMap
		//oneMap
		//转成json后（fastjson2Object2）
		Object[] obj = JsonUtil.fastjson2Object2(json, classMap);
		return obj;
	}
	
	
	private boolean isInfo(String str, List<String> strList) {
		int i = 0;
		for (String s : strList) {
			if (s.equals(str))
				i++;
		}
//		LOGGER.info(str + "出现了 : " + i +"次");
		return i > 1 ? true :false;
	}
	
	private LinkedHashMap<String, Object>
				threeMap(LinkedHashMap<String, Object> twoMap, String paraName, String k, String v) {
		//判断是twoMap中是否存在paraName的map
		//如不存在，则新增一个threeMap
		//第三层LinkedHashMap<String ,String>
		LinkedHashMap<String, String> threeMap = new LinkedHashMap<String, String>();
		
		if (!twoMap.keySet().contains(paraName)) {
			threeMap.put(k, v);
			twoMap.put(paraName, threeMap);
		} else {
			//如存在，则在原有的threeMap中add值
			((LinkedHashMap<String, String>)twoMap.get(paraName)).put(k, v);
		}
		return twoMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int updateMap(String json) {
		Map<Integer, String> map = (Map<Integer, String>) JSONObject.parse(json);
		return mapper.updateMap(map);
	}

}
