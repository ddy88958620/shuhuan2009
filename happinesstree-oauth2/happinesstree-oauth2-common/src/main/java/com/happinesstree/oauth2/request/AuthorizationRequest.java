package com.happinesstree.oauth2.request;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @Title: AuthorizationRequest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:23:06
 * @author shuhuan@qiyi.com
 */
public interface AuthorizationRequest {

	public static final String CLIENT_ID      = "client_id";
	
	public static final String STATE          = "state";

	public static final String SCOPE          = "scope";

	public static final String REDIRECT_URI   = "redirect_uri";

	public static final String RESPONSE_TYPE  = "response_type";

	public static final String USER_OAUTH_APPROVAL = "user_oauth_approval";
	
	public static final String USER_LOGIN_APPROVAL = "user_login_approval";
	
	public Object getUser();

	public Map<String, String> getAuthorizationParameters();
	
	public Map<String, String> getApprovalParameters();

	public String getClientId();
	
	public Set<String> getScope();

	public boolean isApproved();

	public boolean isDenied();

	public String getState();

	public String getRedirectUri();

	public Set<String> getResponseTypes();

}