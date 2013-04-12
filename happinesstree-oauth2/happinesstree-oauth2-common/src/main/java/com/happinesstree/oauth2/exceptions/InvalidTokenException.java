package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: InvalidTokenException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * token过期
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@SuppressWarnings("serial")
public class InvalidTokenException extends OAuth2Exception {

	public InvalidTokenException(String msg) {
		super(msg);
	}

	@Override
	public int getHttpErrorCode() {
		return 401;
	}

	@Override
	public String getOAuth2ErrorCode() {
		return Constants.OAUTH2_EXPIRED_TOKEN;
	}
}
