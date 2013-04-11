package com.happinesstree.oauth2.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @Title: MD5Encoder.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * MD5加密
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-13 下午3:23:45
 * @author shuhuan@happinesstree.com
 */
public class MD5EncoderUtils {

	/**
	 * 普通MD5加密
	 * 
	 * @param value
	 * @return
	 */
	public static String md5(String value) {
		if (value == null) {
			return null;
		}
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
		}

		try {
			byte[] bytes = digest.digest(value.getBytes("UTF-8"));
			return String.format("%032x", new BigInteger(1, bytes));
		}
		catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
		}
    }
}
