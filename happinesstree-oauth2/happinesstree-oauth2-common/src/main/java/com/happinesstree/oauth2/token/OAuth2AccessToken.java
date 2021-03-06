package com.happinesstree.oauth2.token;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @Title: OAuth2AccessToken.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface OAuth2AccessToken {

	public static String BEARER_TYPE = "Bearer";

	public static String OAUTH2_TYPE = "OAuth2";

	public static String ACCESS_TOKEN = "access_token";

	public static String TOKEN_TYPE = "token_type";

	public static String EXPIRES_IN = "expires_in";

	public static String REFRESH_TOKEN = "refresh_token";

	public static String SCOPE = "scope";

	Map<String, Object> getAdditionalInformation();

	Set<String> getScope();

	OAuth2RefreshToken getRefreshToken();

	String getTokenType();

	boolean isExpired();

	Date getExpiration();

	int getExpiresIn();

	String getValue();

}
