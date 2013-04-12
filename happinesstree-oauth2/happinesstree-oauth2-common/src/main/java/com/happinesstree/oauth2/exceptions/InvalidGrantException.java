package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: InvalidGrantException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 提供的Access Grant是无效的、过期的或已撤销的
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@SuppressWarnings("serial")
public class InvalidGrantException extends OAuth2Exception {

	public InvalidGrantException(String msg) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return Constants.OAUTH2_INVALID_GRANT;
	}
}