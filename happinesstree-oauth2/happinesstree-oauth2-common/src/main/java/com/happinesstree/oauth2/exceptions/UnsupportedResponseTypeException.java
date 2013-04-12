package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: UnsupportedResponseTypeException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 不支持的 ResponseType异常
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public class UnsupportedResponseTypeException extends OAuth2Exception {

	private static final long serialVersionUID = -2691442359910787489L;

	public UnsupportedResponseTypeException(String msg) {
		super(msg);
	}
	
	@Override
	public int getHttpErrorCode() {
		return 401;
	}

	@Override
	public String getOAuth2ErrorCode() {
		return Constants.OAUTH2_UNSUPPORTED_RESPONSE_TYPE;
	}
}