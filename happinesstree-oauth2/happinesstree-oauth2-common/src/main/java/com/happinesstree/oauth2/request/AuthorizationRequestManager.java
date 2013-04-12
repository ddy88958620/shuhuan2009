package com.happinesstree.oauth2.request;

import java.util.Map;

import com.happinesstree.oauth2.clients.ClientDetails;

/**
 * 
 * @Title: AuthorizationRequestManager.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface AuthorizationRequestManager {

	AuthorizationRequest createAuthorizationRequest(Map<String, String> authorizationParameters);
	
	void validateParameters(Map<String, String> parameters, ClientDetails clientDetails);

}