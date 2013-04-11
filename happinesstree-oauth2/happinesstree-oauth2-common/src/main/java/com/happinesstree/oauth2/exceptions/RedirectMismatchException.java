package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: RedirectMismatchException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 重定向地址不匹配
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午5:05:07
 * @author shuhuan@qiyi.com
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
