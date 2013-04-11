package com.happinesstree.oauth2.request;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.happinesstree.oauth2.utils.OAuth2Utils;

/**
 * 
 * @Title: DefaultAuthorizationRequest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:23:51
 * @author shuhuan@qiyi.com
 */
@SuppressWarnings("serial")
public class DefaultAuthorizationRequest implements AuthorizationRequest, Serializable {

	private Set<String> scope = new LinkedHashSet<String>();

	private boolean approved = false;

	private Map<String, String> authorizationParameters = new HashMap<String, String>();

	private Map<String, String> approvalParameters = new HashMap<String, String>();

	private String resolvedRedirectUri;
	
	private Object user;

	public DefaultAuthorizationRequest(Map<String, String> authorizationParameters) {
		this(authorizationParameters, Collections.<String, String> emptyMap(), authorizationParameters.get(CLIENT_ID),
				OAuth2Utils.parseParameterList(authorizationParameters.get("scope")), false);
	}

	public DefaultAuthorizationRequest(Map<String, String> authorizationParameters,
			Map<String, String> approvalParameters, String clientId, Collection<String> scope) {
		this(authorizationParameters, approvalParameters, clientId, scope, false);
	}

	public DefaultAuthorizationRequest(String clientId) {
		this(null, null, clientId, null, false);
	}

	public DefaultAuthorizationRequest(AuthorizationRequest copy) {
		this(copy.getAuthorizationParameters(), copy.getApprovalParameters(), copy.getClientId(), copy.getScope(), copy.isApproved());
		setRedirectUri(copy.getRedirectUri());
		setUser(copy.getUser());
		if (!scope.isEmpty()) {
			this.authorizationParameters.put(SCOPE, OAuth2Utils.formatParameterList(scope));
		}
	}

	private DefaultAuthorizationRequest(Map<String, String> authorizationParameters,
			Map<String, String> approvalParameters, String clientId, Collection<String> scope, boolean approved) {
		if (authorizationParameters != null) {
			this.authorizationParameters.putAll(authorizationParameters);
		}
		if (approvalParameters != null) {
			this.approvalParameters.putAll(approvalParameters);
		}
		if (scope != null) {
			this.scope = new LinkedHashSet<String>(scope);
		}
		this.authorizationParameters.put(CLIENT_ID, clientId);
		this.authorizationParameters.put(SCOPE, OAuth2Utils.formatParameterList(scope));
		this.approved = approved;
	}

	public Map<String, String> getAuthorizationParameters() {
		return Collections.unmodifiableMap(authorizationParameters);
	}

	public Map<String, String> getApprovalParameters() {
		return Collections.unmodifiableMap(approvalParameters);
	}

	public String getClientId() {
		return authorizationParameters.get(CLIENT_ID);
	}

	public Set<String> getScope() {
		return Collections.unmodifiableSet(this.scope);
	}

	public boolean isApproved() {
		return approved;
	}

	public boolean isDenied() {
		return !approved;
	}

	public String getState() {
		return authorizationParameters.get(STATE);
	}

	public String getRedirectUri() {
		return resolvedRedirectUri == null ? authorizationParameters.get(REDIRECT_URI) : resolvedRedirectUri;
	}

	public Set<String> getResponseTypes() {
		return OAuth2Utils.parseParameterList(authorizationParameters.get(RESPONSE_TYPE));
	}

	public void setRedirectUri(String redirectUri) {
		this.resolvedRedirectUri = redirectUri;
	}

	public void setScope(Set<String> scope) {
		if (scope != null && scope.size() == 1) {
			String value = scope.iterator().next();
			/*
			 * This is really an error, but it can catch out unsuspecting users and it's easy to fix. It happens when an
			 * AuthorizationRequest gets bound accidentally from request parameters using @ModelAttribute.
			 */
			if (value.contains(" ") || scope.contains(",")) {
				scope = OAuth2Utils.parseParameterList(value);
			}
		}
		this.scope = scope == null ? new LinkedHashSet<String>() : new LinkedHashSet<String>(scope);
		authorizationParameters.put(SCOPE, OAuth2Utils.formatParameterList(scope));
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public void setAuthorizationParameters(Map<String, String> authorizationParameters) {
		this.authorizationParameters = authorizationParameters == null ? new HashMap<String, String>()
				: new HashMap<String, String>(authorizationParameters);
		if (authorizationParameters.containsKey(SCOPE) && StringUtils.hasText(authorizationParameters.get(SCOPE))) {
			String scope = authorizationParameters.get(SCOPE);
			setScope(OAuth2Utils.parseParameterList(scope));
		}
	}

	public void setApprovalParameters(Map<String, String> approvalParameters) {
		this.approvalParameters = approvalParameters == null ? new HashMap<String, String>()
				: new HashMap<String, String>(approvalParameters);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((approvalParameters == null) ? 0 : approvalParameters
						.hashCode());
		result = prime * result + (approved ? 1231 : 1237);
		result = prime
				* result
				+ ((authorizationParameters == null) ? 0
						: authorizationParameters.hashCode());
		result = prime
				* result
				+ ((resolvedRedirectUri == null) ? 0 : resolvedRedirectUri
						.hashCode());
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultAuthorizationRequest other = (DefaultAuthorizationRequest) obj;
		if (approvalParameters == null) {
			if (other.approvalParameters != null)
				return false;
		} else if (!approvalParameters.equals(other.approvalParameters))
			return false;
		if (approved != other.approved)
			return false;
		if (authorizationParameters == null) {
			if (other.authorizationParameters != null)
				return false;
		} else if (!authorizationParameters
				.equals(other.authorizationParameters))
			return false;
		if (resolvedRedirectUri == null) {
			if (other.resolvedRedirectUri != null)
				return false;
		} else if (!resolvedRedirectUri.equals(other.resolvedRedirectUri))
			return false;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		return true;
	}

	@Override
	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

}