package com.happinesstree.oauth2.exceptions;

import com.happinesstree.oauth2.common.Constants;

/**
 * 
 * @Title: UserDeniedAuthorizationException.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 用户或授权服务器拒绝授予数据访问权限
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@SuppressWarnings("serial")
public class UserDeniedAuthorizationException extends OAuth2Exception {

  public UserDeniedAuthorizationException(String msg) {
    super(msg);
  }

  @Override
  public String getOAuth2ErrorCode() {
    return Constants.OAUTH2_ACCESS_DENIED;
  }

}
