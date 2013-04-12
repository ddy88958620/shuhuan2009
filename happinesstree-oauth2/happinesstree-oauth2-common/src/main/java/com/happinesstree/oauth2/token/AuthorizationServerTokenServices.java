package com.happinesstree.oauth2.token;

import com.happinesstree.oauth2.request.AuthorizationRequest;

/**
 * 
 * @Title: AuthorizationServerTokenServices.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface AuthorizationServerTokenServices {

	OAuth2AccessToken createAccessToken(AuthorizationRequest authorizationRequest);

	OAuth2AccessToken refreshAccessToken(String refreshToken, AuthorizationRequest authorizationRequest);

	OAuth2AccessToken getAccessToken(AuthorizationRequest authorizationRequest);

}