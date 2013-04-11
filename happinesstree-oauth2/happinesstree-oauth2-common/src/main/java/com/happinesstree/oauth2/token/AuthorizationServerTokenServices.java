package com.happinesstree.oauth2.token;

import com.happinesstree.oauth2.request.AuthorizationRequest;

/**
 * 
 * @Title: AuthorizationServerTokenServices.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:23:23
 * @author shuhuan@qiyi.com
 */
public interface AuthorizationServerTokenServices {

	OAuth2AccessToken createAccessToken(AuthorizationRequest authorizationRequest);

	OAuth2AccessToken refreshAccessToken(String refreshToken, AuthorizationRequest authorizationRequest);

	OAuth2AccessToken getAccessToken(AuthorizationRequest authorizationRequest);

}