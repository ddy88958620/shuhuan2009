package com.happinesstree.oauth2.request;

import java.util.Map;

import com.happinesstree.oauth2.clients.ClientDetails;

/**
 * 
 * @Title: AuthorizationRequestManager.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:23:16
 * @author shuhuan@qiyi.com
 */
public interface AuthorizationRequestManager {

	AuthorizationRequest createAuthorizationRequest(Map<String, String> authorizationParameters);
	
	void validateParameters(Map<String, String> parameters, ClientDetails clientDetails);

}