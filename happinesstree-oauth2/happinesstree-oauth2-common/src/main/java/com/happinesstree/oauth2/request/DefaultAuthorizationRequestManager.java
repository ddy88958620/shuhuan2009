package com.happinesstree.oauth2.request;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.happinesstree.oauth2.clients.ClientDetails;
import com.happinesstree.oauth2.exceptions.BadClientCredentialsException;
import com.happinesstree.oauth2.exceptions.InvalidClientException;
import com.happinesstree.oauth2.exceptions.OAuth2Exception;
import com.happinesstree.oauth2.exceptions.RedirectMismatchException;

/**
 * 
 * @Title: DefaultAuthorizationRequestManager.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:24:08
 * @author shuhuan@qiyi.com
 */
public class DefaultAuthorizationRequestManager implements
		AuthorizationRequestManager {

	public AuthorizationRequest createAuthorizationRequest(Map<String, String> parameters) {

		String clientId = parameters.get("client_id");
		if (clientId == null) {
			throw new InvalidClientException("A client id must be provided");
		}

		DefaultAuthorizationRequest request = new DefaultAuthorizationRequest(
				parameters, Collections.<String, String> emptyMap(), clientId,
				null);

		return request;
	}

	/**
	 * 
	 */
	public void validateParameters(Map<String, String> parameters,
			ClientDetails clientDetails) throws OAuth2Exception {
		// 检查是否存在clientSecret
		if(parameters.containsKey("client_secret")) {
			String clientSecret = parameters.get("client_secret");
			if(!clientDetails.getClientSecret().equals(clientSecret)) {
				throw new BadClientCredentialsException("Client Secret Is Not Right");
			}
		}
		
		String redirectUri = "";
		if( parameters.containsKey("redirect_uri") ) {
			redirectUri = parameters.get("redirect_uri");
		}
		
		// 检查redirect_uri
		Set<String> redirectUriSet = clientDetails.getRegisteredRedirectUri();
		if( null != redirectUriSet && !redirectUriSet.isEmpty() ) {
			if( !redirectUriSet.contains(redirectUri) ) {
				throw new RedirectMismatchException("redirect_uri_mismatch");
			}
		}
	}

}
