package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: InvalidTokenException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * token过期
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午5:03:53
 * @author shuhuan@qiyi.com
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
