package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: RedirectMismatchException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 重定向地址不匹配
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@SuppressWarnings("serial")
public class RedirectMismatchException extends OAuth2Exception {

	public RedirectMismatchException(String msg) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return Constants.OAUTH2_REDIRECT_URI_MISMATCH;
	}
}
