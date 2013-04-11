package com.happinesstree.oauth2.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;

/**
 * 
 * @Title: MD5Encoder.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * MD5加密
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-13 下午3:23:45
 * @author shuhuan@qiyi.com
 */
public class MD5EncoderUtils {

	private static final Logger logger = LoggerFactory .getLogger(MD5EncoderUtils.class);
	
	/**
	 * 普通MD5加密
	 * 
	 * @param rawPass
	 * @return
	 */
	public static String md5(String rawPass) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			
			byte[] digest = messageDigest.digest(Utf8.encode(rawPass));

	        return new String(Hex.encode(digest));
	        
		} catch (NoSuchAlgorithmException e) {
			logger.error("md5 exception", e);
		}

        return "";
    }
}
