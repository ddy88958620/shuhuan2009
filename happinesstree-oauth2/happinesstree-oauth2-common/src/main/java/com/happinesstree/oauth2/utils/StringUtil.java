package com.happinesstree.oauth2.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @Title: StringUtil.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 字符串处理工具
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public class StringUtil {
	
	/**
	 * 转码
	 * @param str
	 * @return
	 */
	public static String enCodeStr(String str) {
        try {
          return new String(str.getBytes("iso-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * 转化为整数
	 * @param str
	 * @return
	 */
	public static int parseInt( String str ) {
		str = StringUtils.trim(str);
		if( StringUtils.isBlank(str) ) {
			return 0;
		} else if(StringUtils.isNumeric(str)) {
			return Integer.parseInt(str);
		} else {
			return 0;
		}
	}
	
	/**
	 * 转化为浮点数
	 * @param str
	 * @return
	 */
	public static double parseDouble( String str ) {
		str = StringUtils.trim(str);
		if( StringUtils.isBlank(str) ) {
			return 0.0;
		} else if(StringUtils.isNumeric(str)) {
			return Double.parseDouble(str);
		} else {
			return 0.0;
		}
	}
	
	/**
	 * 转化为长整数
	 * @param str
	 * @return
	 */
	public static long parseLong( String str ) {
		str = StringUtils.trim(str);
		if( StringUtils.isBlank(str) ) {
			return 0;
		} else if(StringUtils.isNumeric(str)) {
			return Long.parseLong(str);
		} else {
			return 0;
		}
	}
	
	public static String regxTimestamp( String timestamp ) {
    	
    	if( timestamp.indexOf('.') > 0 ) {
    		timestamp = timestamp.substring(0, timestamp.indexOf('.'));
    	}
    	
    	if( timestamp.length() > 13 ) {
    		// 取前13位
        	timestamp = timestamp.substring(0, 13);
    	}
    	
    	return timestamp;
    }
}
