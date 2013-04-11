package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: BadClientCredentialsException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午5:03:37
 * @author shuhuan@qiyi.com
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
