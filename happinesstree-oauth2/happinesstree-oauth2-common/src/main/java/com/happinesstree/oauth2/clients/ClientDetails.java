package com.happinesstree.oauth2.clients;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @Title: ClientDetails.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:23:39
 * @author shuhuan@qiyi.com
 */
public interface ClientDetails extends Serializable {

	String getClientId();

	boolean isSecretRequired();

	String getClientSecret();

	boolean isScoped();

	Set<String> getScope();

	Set<String> getAuthorizedGrantTypes();

	Set<String> getRegisteredRedirectUri();

	Integer getAccessTokenValiditySeconds();
 
	Integer getRefreshTokenValiditySeconds();

	Map<String, Object> getAdditionalInformation();

}
