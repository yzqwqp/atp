package com.uusoft.atp.utils;

import com.alibaba.dubbo.common.utils.StringUtils; 

/**
 * @author qiupeng
 *
 */
public  final class StringUtil {
	
	/**
     * 判断字符串不为空，且不是空白字符，且不为Null
     */
    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str);
    }
    
    /**
     * 字符串切分
     * 
     */
    public static String[] splitList(String str) {
    	return str.split("=");
    }
}
