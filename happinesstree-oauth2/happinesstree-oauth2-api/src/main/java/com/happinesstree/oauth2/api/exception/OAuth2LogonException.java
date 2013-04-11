package com.happinesstree.oauth2.api.exception;

/**
 * 
 * @Title: OAuth2LogonException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2用户未登录
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-16 下午2:44:24
 * @author shuhuan2009@gmail.com
 */
public class OAuth2LogonException extends Exception {
	
	private static final long serialVersionUID = -1834578831912469060L;

	public OAuth2LogonException(String msg) {
		super(msg);
	}
	
	public OAuth2LogonException() {
		super("User Login");
	}

}
