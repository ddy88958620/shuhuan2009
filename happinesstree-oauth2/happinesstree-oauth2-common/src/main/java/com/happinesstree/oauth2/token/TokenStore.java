package com.happinesstree.oauth2.token;

import java.util.Collection;

import com.happinesstree.oauth2.request.AuthorizationRequest;

/**
 * 
 * @Title: TokenStore.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface TokenStore {

	/**
	 * Store an access token.
	 * 
	 * @param token The token to store.
	 * @param authentication The authentication associated with the token.
	 */
	void storeAccessToken(OAuth2AccessToken token, AuthorizationRequest authorizationRequest);

	/**
	 * Read an access token from the store.
	 * 
	 * @param tokenValue The token value.
	 * @return The access token to read.
	 */
	OAuth2AccessToken readAccessToken(String tokenValue);

	/**
	 * Remove an access token from the database.
	 * 
	 * @param token The token to remove from the database.
	 */
	void removeAccessToken(OAuth2AccessToken token);

	/**
	 * Store the specified refresh token in the database.
	 * 
	 * @param refreshToken The refresh token to store.
	 * @param authentication The authentication associated with the refresh token.
	 */
	void storeRefreshToken(OAuth2RefreshToken refreshToken, AuthorizationRequest authorizationRequest);

	/**
	 * Read a refresh token from the store.
	 * 
	 * @param tokenValue The value of the token to read.
	 * @return The token.
	 */
	OAuth2RefreshToken readRefreshToken(String tokenValue);

	/**
	 * Remove a refresh token from the database.
	 * 
	 * @param token The token to remove from the database.
	 */
	void removeRefreshToken(OAuth2RefreshToken token);

	/**
	 * Remove an access token using a refresh token. This functionality is necessary so refresh tokens can't be used to
	 * create an unlimited number of access tokens.
	 * 
	 * @param refreshToken The refresh token.
	 */
	void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken);

	/**
	 * Retrieve an access token stored against the provided authentication key, if it exists.
	 * 
	 * @param authentication the authentication key for the access token
	 * 
	 * @return the access token or null if there was none
	 */
	OAuth2AccessToken getAccessToken(AuthorizationRequest authorizationRequest);

	/**
	 * @param userName the user name to search
	 * @return a collection of access tokens
	 */
	Collection<OAuth2AccessToken> findTokensByUserName(String userName);

	/**
	 * @param clientId the client id
	 * @return a collection of access tokens
	 */
	Collection<OAuth2AccessToken> findTokensByClientId(String clientId);

}
