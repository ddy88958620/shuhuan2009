package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: UnauthorizedClientException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 客户端没有权限
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@SuppressWarnings("serial")
public class UnauthorizedClientException extends OAuth2Exception {

	public UnauthorizedClientException(String msg) {
		super(msg);
	}

	@Override
	public int getHttpErrorCode() {
		// The spec says this can be unauthorized
		return 401;
	}

	@Override
	public String getOAuth2ErrorCode() {
		return Constants.OAUTH2_UNAUTHORIZED_CLIENT;
	}
}
