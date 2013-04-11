package com.happinesstree.oauth2.code;

import com.happinesstree.oauth2.request.AuthorizationRequest;

/**
 * 
 * @Title: AuthorizationCodeServices.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:22:59
 * @author shuhuan@qiyi.com
 */
public interface AuthorizationCodeServices {

	String createAuthorizationCode(AuthorizationRequest authorizationRequest);

}
