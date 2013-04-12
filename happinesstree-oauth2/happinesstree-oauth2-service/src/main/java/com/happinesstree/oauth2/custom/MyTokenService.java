package com.happinesstree.oauth2.custom;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.happinesstree.oauth2.clients.ClientDetails;
import com.happinesstree.oauth2.clients.ClientDetailsService;
import com.happinesstree.oauth2.exceptions.InvalidGrantException;
import com.happinesstree.oauth2.exceptions.InvalidTokenException;
import com.happinesstree.oauth2.request.AuthorizationRequest;
import com.happinesstree.oauth2.token.AuthorizationServerTokenServices;
import com.happinesstree.oauth2.token.DefaultOAuth2AccessToken;
import com.happinesstree.oauth2.token.DefaultOAuth2RefreshToken;
import com.happinesstree.oauth2.token.OAuth2AccessToken;
import com.happinesstree.oauth2.token.OAuth2RefreshToken;
import com.happinesstree.oauth2.token.TokenStore;
import com.happinesstree.oauth2.utils.MD5EncoderUtils;

/**
 * 
 * @Title: QiyiTokenService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * AccessToken/RefreshToken令牌管理实现
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@Service("myTokenService")
public class MyTokenService implements AuthorizationServerTokenServices {

	// 访问令牌有效时间，默认30天
	private int accessTokenValiditySeconds = 60 * 60 * 24 * 30;
	
	// 刷新令牌有效时间，默认3个月
	private int refreshTokenValiditySeconds = 60 * 60 * 24 * 30 * 3;

	// 是否支持刷新令牌，默认支持
	private boolean supportRefreshToken = true;

	@Resource(name="qiyiTokenStore")
	private TokenStore tokenStore;
	
	// 客户端AppClient信息服务
	@Resource(name="qiyiClientDetailsService")
	private ClientDetailsService clientDetailsService;

	/**
	 * 创建访问令牌access_token
	 * 
	 */
	public OAuth2AccessToken createAccessToken(AuthorizationRequest authorizationRequest) {

		// 根据认证实例，查找access_token是否存在
		OAuth2AccessToken existingAccessToken = tokenStore.getAccessToken(authorizationRequest);
		OAuth2RefreshToken refreshToken = null;
		
		if (existingAccessToken != null) {
			if (existingAccessToken.isExpired()) { // 访问令牌过期
				if (existingAccessToken.getRefreshToken() != null) {
					refreshToken = existingAccessToken.getRefreshToken();
					// 设置刷新令牌过期（数据库中逻辑删除、清楚缓存）
					tokenStore.removeRefreshToken(refreshToken);
					
					// 重置刷新令牌
					refreshToken = null;
				}
				// 设置访问令牌过期（数据库中逻辑删除、清楚缓存）
				tokenStore.removeAccessToken(existingAccessToken);
			}
			else {
				return existingAccessToken;
			}
		}

		// 创建新的刷新令牌
		if (refreshToken == null) { 
			refreshToken = createRefreshToken(authorizationRequest);
		}
		
		// 创建新的访问令牌
		OAuth2AccessToken accessToken = createAccessToken(authorizationRequest, refreshToken);
		
		// 保存访问令牌和刷新令牌
		tokenStore.storeAccessToken(accessToken, authorizationRequest);
		if (refreshToken != null) {
			tokenStore.storeRefreshToken(refreshToken, authorizationRequest);
		}
		
		return accessToken;

	}

	/**
	 * 根据刷新令牌refresh_token获取访问令牌access_token
	 * 
	 */
	public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, AuthorizationRequest authorizationRequest) {

		if (!supportRefreshToken) {
			throw new InvalidGrantException("Invalid refresh token: " + refreshTokenValue);
		}

		OAuth2RefreshToken refreshToken = tokenStore.readRefreshToken(refreshTokenValue);
		if (refreshToken == null) {
			throw new InvalidGrantException("Invalid refresh token: " + refreshTokenValue);
		}

		String clientId = authorizationRequest.getClientId();
		if (clientId == null) {
			throw new InvalidGrantException("Wrong client for this refresh token: " + refreshTokenValue);
		}

		// clear out any access tokens already associated with the refresh token.
		tokenStore.removeAccessTokenUsingRefreshToken(refreshToken);

		if (isExpired(refreshToken)) {
			tokenStore.removeRefreshToken(refreshToken);
			throw new InvalidTokenException("Invalid refresh token (expired): " + refreshToken);
		}

		tokenStore.removeRefreshToken(refreshToken);
		refreshToken = createRefreshToken(authorizationRequest);

		OAuth2AccessToken accessToken = createAccessToken(authorizationRequest, refreshToken);
		tokenStore.storeAccessToken(accessToken, authorizationRequest);
		tokenStore.storeRefreshToken(refreshToken, authorizationRequest);
		
		return accessToken;
	}

	public OAuth2AccessToken getAccessToken(AuthorizationRequest authorizationRequest) {
		return tokenStore.getAccessToken(authorizationRequest);
	}


	protected boolean isExpired(OAuth2RefreshToken refreshToken) {
		if (refreshToken instanceof OAuth2RefreshToken) {
			OAuth2RefreshToken expiringToken = (OAuth2RefreshToken) refreshToken;
			return expiringToken.getExpiration() == null
					|| System.currentTimeMillis() > expiringToken.getExpiration().getTime();
		}
		return false;
	}

	public OAuth2AccessToken readAccessToken(String accessToken) {
		return tokenStore.readAccessToken(accessToken);
	}
	

	public String getClientId(String tokenValue) {
		return null;
	}

	public Collection<OAuth2AccessToken> findTokensByUserName(String userName) {
		return tokenStore.findTokensByUserName(userName);
	}

	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		return tokenStore.findTokensByClientId(clientId);
	}

	public boolean revokeToken(String tokenValue) {
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		if (accessToken == null) {
			return false;
		}
		if (accessToken.getRefreshToken() != null) {
			tokenStore.removeRefreshToken(accessToken.getRefreshToken());
		}
		tokenStore.removeAccessToken(accessToken);
		return true;
	}

	/**
	 * 创建刷新令牌
	 * 
	 * @param authentication
	 * @return
	 */
	private OAuth2RefreshToken createRefreshToken(AuthorizationRequest authorizationRequest) {
		if (!isSupportRefreshToken(authorizationRequest)) {
			return null;
		}
		int validitySeconds = getRefreshTokenValiditySeconds(authorizationRequest);
		
		String refreshTokenValue = MD5EncoderUtils.md5(UUID.randomUUID().toString());
		
		OAuth2RefreshToken refreshToken = new DefaultOAuth2RefreshToken(refreshTokenValue,
				new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
		return refreshToken;
	}

	/**
	 * 创建访问令牌
	 * 
	 * @param authentication
	 * @param refreshToken
	 * @return
	 */
	private OAuth2AccessToken createAccessToken(AuthorizationRequest authorizationRequest, OAuth2RefreshToken refreshToken) {
		
		String accessTokenValue = MD5EncoderUtils.md5(UUID.randomUUID().toString());
		
		DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessTokenValue);
		int validitySeconds = getAccessTokenValiditySeconds(authorizationRequest);
		if (validitySeconds > 0) {
			token.setExpiration(new Date(System.currentTimeMillis() + (validitySeconds * 1000L)));
		}
		token.setRefreshToken(refreshToken);

		return token;
	}

	/**
	 * The access token validity period in seconds
	 * @param authorizationRequest the current authorization request
	 * @return the access token validity period in seconds
	 */
	protected int getAccessTokenValiditySeconds(AuthorizationRequest authorizationRequest) {
		if (clientDetailsService != null) {
			ClientDetails client = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
			Integer validity = client.getAccessTokenValiditySeconds();
			if (validity != null) {
				return validity;
			}
		}
		return accessTokenValiditySeconds;
	}

	/**
	 * The refresh token validity period in seconds
	 * @param authorizationRequest the current authorization request
	 * @return the refresh token validity period in seconds
	 */
	protected int getRefreshTokenValiditySeconds(AuthorizationRequest authorizationRequest) {
		if (clientDetailsService != null) {
			ClientDetails client = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
			Integer validity = client.getRefreshTokenValiditySeconds();
			if (validity != null) {
				return validity;
			}
		}
		return refreshTokenValiditySeconds;
	}

	/**
	 * Is a refresh token supported for this client (or the global setting if
	 * {@link #setClientDetailsService(ClientDetailsService) clientDetailsService} is not set.
	 * @param authorizationRequest the current authorization request
	 * @return boolean to indicate if refresh token is supported
	 */
	protected boolean isSupportRefreshToken(AuthorizationRequest authorizationRequest) {
		if (clientDetailsService != null) {
			ClientDetails client = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
			return client.getAuthorizedGrantTypes().contains("refresh_token");
		}
		return this.supportRefreshToken;
	}

	/**
	 * The validity (in seconds) of the refresh token.
	 * 
	 * @param refreshTokenValiditySeconds The validity (in seconds) of the refresh token.
	 */
	public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	/**
	 * The default validity (in seconds) of the access token. Zero or negative for non-expiring tokens. If a client
	 * details service is set the validity period will be read from he client, defaulting to this value if not defined
	 * by the client.
	 * 
	 * @param accessTokenValiditySeconds The validity (in seconds) of the access token.
	 */
	public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	/**
	 * Whether to support the refresh token.
	 * 
	 * @param supportRefreshToken Whether to support the refresh token.
	 */
	public void setSupportRefreshToken(boolean supportRefreshToken) {
		this.supportRefreshToken = supportRefreshToken;
	}

	/**
	 * The client details service to use for looking up clients (if necessary). Optional if the access token expiry is
	 * set globally via {@link #setAccessTokenValiditySeconds(int)}.
	 * 
	 * @param clientDetailsService the client details service
	 */
	public void setClientDetailsService(ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
	}
	
}
