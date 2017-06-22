/**   
 * <p>Title: ParaTypeUtil.java</p>
 * @Package com.uusoft.atp.utils 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author Adele
 * @since 2016年12月11日 下午5:08:15 
 * @version V1.0   
 */
package com.uusoft.atp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser; 
/** 
 * <p>Description: TODO(用一句话描述该文件做什么)</p> 
 * <p>Company:上海投投金融有限责任公司</p>
 * @author Adele
 * @version V1.0 
 */
/**
 * @author qiupeng
 *
 */
public  final class JsonUtil {
	private static final  Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);


	
	/** 
	 * <p>Description:根据Json和对应的参数类型转变成Object数组</p>
	 * @param json
	 * @param method
	 * @author Adele
	 * @date 2016年12月11日 下午10:43:39   
	 */
	public static Object[] json2Object(String json, List<String> paras){
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(json);
		JsonObject object = element.getAsJsonObject();
		List<Object> list = new ArrayList<>();//保存入参的值
		int i =0;
		for(Entry<String, JsonElement> entry : object.entrySet()){
			if(paras.get(i).startsWith("com")){//如果是
				Map<String, Object> map = new HashMap<String, Object>();
				JsonElement element2 = entry.getValue();
				JsonObject object2 = element2.getAsJsonObject();
				for(Entry<String, JsonElement> entry2 : object2.entrySet()){
					map.put(entry2.getKey(), entry2.getValue());
				}
				if(!map.isEmpty()){
					list.add(map);
				}
			}
			else{
				list.add(entry.getValue());
			}
			i++;
		}
		Object[] arr = list.toArray();
		return arr;
	}
	
	public static Object[] fastjson2Object(String json, List<String> paras){
		List<Object> list = new ArrayList<>();//保存入参的值
		JSONObject jsonobj = JSONObject.parseObject(json);
		Object[] arr = new Object[paras.size()];
		int i =0;
		for(Entry<String, Object> entry : jsonobj.entrySet()){
			if(paras.get(i).startsWith("com")){//如果是
				Map<String, Object> map = new HashMap<String, Object>();
				JSONObject object = (JSONObject) entry.getValue();
				for(Entry<String, Object> entry2 : object.entrySet()){
					map.put(entry2.getKey(), entry2.getValue());
				}
				if(!map.isEmpty()){
					list.add(map);
				}
			}
			else{
				list.add(entry.getValue());
				LOGGER.info(entry.getValue().toString());
			}
			i++;
		}
		arr= list.toArray();
		return arr;
	}
	
	
	/**
	 * 家佳提供意见，建议使用Treemap(按K的字典顺序排)或者LinkedHashMap(按放入顺序排)，可以保持数据的顺序一致
	 * @param json
	 * @param map
	 * @return
	 */
	public static Object[] fastjson2Object2(String json, LinkedHashMap<String,String> map){
		LOGGER.info("################");
		LOGGER.info(json);
		LOGGER.info(map.toString());
		JSONObject jsonobj = JSONObject.parseObject(json);
		Set<Map.Entry<String,String>> set=map.entrySet();
		Object[] obj=new Object[map.size()];
		int index=0;
		for(Map.Entry<String,String> mapp:set){
			if (mapp.getValue().startsWith("com")) {
				obj[index++]=jsonobj.getObject(mapp.getKey(), Map.class);
			} else {
				try {
					obj[index++]=jsonobj.getObject(mapp.getKey(),Class.forName(mapp.getValue()));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return obj;
	}
	

}
