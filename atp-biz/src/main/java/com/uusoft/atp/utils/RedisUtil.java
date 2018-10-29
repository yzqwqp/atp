package com.uusoft.atp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import freemarker.template.SimpleDate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;




/**
 * redis对象类
 * @author qiupeng
 *
 */
public class RedisUtil {
	
//Redis服务器IP
	private static String ADDR = "121.40.235.92";//智能推荐--测试环境
//	private static String ADDR = "121.41.66.71";//智能推荐--生产环境
	//Redis的端口号
	private static int PORT = 6379;
	//访问密码
	private static String AUTH = "rdsqazxsw123";//智能推荐--测试环境
//	private static String AUTH = "rdsqazxsw123real";//智能推荐--生产环境
	//可用连接实例的最大数目，默认值为8；
	//如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 600;
	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 300;
	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 1000;
	private static int TIMEOUT = 1000;
	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;
	private static JedisPool jedisPool = null;
	
	static String key1 = "SX*HSY*0001_130000_sessionDriverGPS";//18252726071
//	static String key1 = "SX*HSY*0001_132947_sessionDriverGPS";//18252726081
//	static String key1 = "SX*HSY*0001_132970_sessionDriverGPS";//18252726083
	static String key2 = "MOBILE_ZNTJ_LIST_REDIS";
	static String key3 = "MOBILE_ZNTJ_TIME_REDIS";
	static String key4 = "SESSION_DRIVER_VARIETY_PUSH";
	static String key5 = "sessionDriverOnline";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		updaeRedisTime(key1);
//		selectRedisUser();
		selectRedisList();
		
		RedisUtil.expire(key3);
		RedisUtil.expire(key4);
		RedisUtil.expire(key5);

	}
	
	public static void selectRedisUser(){
		//登录用户
		String userGpsUpdateRedisStr = RedisUtil.getValue(key1);
		System.out.println("--1--用户gps信息 : " + userGpsUpdateRedisStr);//打印redis缓存中用户gps信息
		JSONObject jsonObject1 = JSONObject.parseObject(userGpsUpdateRedisStr);
		String userLocationTime = jsonObject1.getString("locationTime");//用户GPS更新入Redis的时间
		try {
			System.out.println(" 用户GPS更新入Redis的时间 ：  " + DateUtils.longToString(Long.valueOf(userLocationTime), "yyyy-MM-dd HH:mm:ss"));
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void selectRedisList(){
		//redis中缓存的当日智能推荐信息
		String zntjListStr = RedisUtil.getValue(key2);
		System.out.println("--2--当日智能推荐信息 : " + zntjListStr);
		
		//redis中缓存的当日智能推荐信息的更新时间
		String zntjListUpdateTime = RedisUtil.getValue(key3);
		System.out.println("--3--当日智能推荐更新时间 : " + zntjListUpdateTime);
		JSONObject jsonObject2 = JSONObject.parseObject(zntjListUpdateTime);
		String zntjTimeRedisTime = jsonObject2.getString("zntjTimeRedis");//智能推荐信息的更新入Redis的时间
		
		try {
			System.out.println(" 智能推荐信息的stt时间 ：  " + DateUtils.longToString(Long.valueOf("1537406820000"), "yyyy-MM-dd HH:mm:ss"));
			System.out.println(" 智能推荐信息的end时间 ：  " + DateUtils.longToString(Long.valueOf("1537407420000"), "yyyy-MM-dd HH:mm:ss"));
			System.out.println(" 智能推荐信息的更新时间 ：  " + DateUtils.longToString(Long.valueOf(zntjTimeRedisTime), "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
		}
		
		//redis中缓存的当日智能推荐信息
		String onlineStr = RedisUtil.getValue(key5);
		System.out.println("--4--onlineStr : " + onlineStr);
	}
	
	public static void updaeRedisTime(String key1){
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("location_lat", "31.300151");
		userMap.put("location_lng", "121.496934");
		try {
			userMap.put("locationTime", DateUtils.stringToLong("2018-09-19 16:50:29", "yyyy-MM-dd HH:mm:ss"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RedisUtil.put(key1, JSONObject.toJSONString(userMap));
		
	}
	
	public static String getValue(String key) {
//		String key = mobileNo + ConstantUtils.REDIS_KEYSPILT + ConstantUtils.REDIS_INSTID  + ConstantUtils.REDIS_KEYSPILT + type + ConstantUtils.REDIS_KEYSPILT + "vCode";
		return RedisUtil.getJedis().get(key);
	}
	
	//RedisUtils.getJedis().del(key);
	
	
	
	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
//			config.setMaxActive(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
//			config.setMaxWait(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取Jedis实例
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 释放jedis资源
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
	
	public static void expire(String key) {
		if (key != null&&!"".equals(key)) {
			Jedis jedis = jedisPool.getResource();
			jedis.expire(key, 0);
			jedis.close();
		}
	}
	
	public static void put(String key, String value){
		if (key != null&&!"".equals(key)) {
			Jedis jedis = jedisPool.getResource();
			jedis.set(key, value);
			jedis.expire(key, 60*60);
			jedis.close();
		}
	}

}
