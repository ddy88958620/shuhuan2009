package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: BadClientCredentialsException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@SuppressWarnings("serial")
public class BadClientCredentialsException extends OAuth2Exception {

	public BadClientCredentialsException(String msg) {
		super(msg); // Don't reveal source of error
	}

	@Override
	public int getHttpErrorCode() {
		return 401;
	}

	@Override
	public String getOAuth2ErrorCode() {
		return Constants.OAUTH2_INVALID_CLIENT;
	}
}
