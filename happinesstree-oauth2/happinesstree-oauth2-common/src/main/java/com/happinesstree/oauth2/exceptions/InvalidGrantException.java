package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: InvalidGrantException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 提供的Access Grant是无效的、过期的或已撤销的
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午5:03:11
 * @author shuhuan@qiyi.com
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