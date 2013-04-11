package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: UnsupportedGrantTypeException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 不支持的 GrantType
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午5:06:36
 * @author shuhuan@qiyi.com
 */
@SuppressWarnings("serial")
public class UnsupportedGrantTypeException extends OAuth2Exception {

  public UnsupportedGrantTypeException(String msg) {
    super(msg);
  }

  @Override
  public String getOAuth2ErrorCode() {
    return Constants.OAUTH2_UNSPPORTED_GRANT_TYPE;
  }
}