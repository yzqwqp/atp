package com.uusoft.atp.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/** 
* 类说明 ：
* 
* @author 邱鹏
* @email qiupeng@toutoujinrong.com
* @since 2017年2月10日 下午6:36:40 
*/
public class LinkedHashMapTest {
	    public static void main(String[] args) {
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("1","abc");
	        map.put("2","abc");
	        map.put("3","kjs");
	        map.put("4","abc");
	        map.put("5","kjs");
	        Map<String, String> map4 = new HashMap<String, String>();
	        map4 = doThing(map);
	        for (String key : map4.keySet()){
	            System.out.println(key+":"+map4.get(key));
	        }
	 
	    }
	    public static Map<String, String>  doThing(Map<String, String> map){
	        Map<String, String> map2 = new HashMap<String, String>();
	        Map<String, String> map3 = new HashMap<String, String>();
	        //TreeMap:对map按key值排序
	        TreeMap<String, String> treemap = new TreeMap<String, String>(map);
	        Iterator<String> it = treemap.keySet().iterator();
	        while (it.hasNext()) {
	            String key = it.next();
	            String value = treemap.get(key);
	            if(map2.containsKey(value)){
	                continue;
	            }else{
	                map2.put(value, value);
	                map3.put(key, value);
	            }
	             
	        }
	        return map3;
	    }
	    
	    
	    public static void aaa()
	    {
	     Map<String, Integer> map = new HashMap<String, Integer>();
	     String[] array = new String[] {"AA","BB","cc","dd","AA","BB","cc","dd","AA","BB","AA"};

	     for (String str : array)
	    {
	     Integer num = map.get(str);
	     num = null == num ? 1 : num + 1;
	     map.put(str, num);
	    }

	     if (array.length != map.size())
	    {
	    System.out.println("存在相同的元素!");
	    }

	     Set set = map.entrySet();
	     Iterator<Entry> it = set.iterator();
	     while (it.hasNext())
	    {
	     Entry<String, Integer> entry = (Entry<String, Integer>)it.next();
	     System.out.println("String :"+ entry.getKey() +"num :"+ entry.getValue());
	    }
	    }
}
