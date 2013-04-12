package com.happinesstree.oauth2.code;

import com.happinesstree.oauth2.request.AuthorizationRequest;

/**
 * 
 * @Title: AuthorizationCodeServices.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-16 下午6:23:31
 * @author shuhuan2009@gmail.com
 */
public interface AuthorizationCodeServices {

	String createAuthorizationCode(AuthorizationRequest authorizationRequest);

}
