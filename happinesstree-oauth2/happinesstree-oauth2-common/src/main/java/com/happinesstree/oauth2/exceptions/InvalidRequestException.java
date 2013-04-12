package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: InvalidRequestException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 请求不合法
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@SuppressWarnings("serial")
public class InvalidRequestException extends OAuth2Exception {

	public InvalidRequestException(String msg) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return Constants.OAUTH2_INVALID_REQUEST;
	}
}
