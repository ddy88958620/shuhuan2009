package com.happinesstree.oauth2.custom;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happinesstree.oauth2.dao.domain.AccessToken;
import com.happinesstree.oauth2.dao.domain.RefreshToken;
import com.happinesstree.oauth2.request.AuthorizationRequest;
import com.happinesstree.oauth2.service.OauthAccessTokenService;
import com.happinesstree.oauth2.service.OauthRefreshTokenService;
import com.happinesstree.oauth2.token.OAuth2AccessToken;
import com.happinesstree.oauth2.token.OAuth2RefreshToken;
import com.happinesstree.oauth2.token.TokenStore;
import com.happinesstree.oauth2.utils.SerializationUtils;

/**
 * 
 * @Title: QiyiTokenStore.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 访问令牌/刷新令牌管理
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-8 下午2:51:56
 * @author shuhuan@qiyi.com
 */
@Service("qiyiTokenStore")
public class MyTokenStore implements TokenStore {
	
	private static final Logger logger = LoggerFactory.getLogger(MyTokenStore.class);
	
	@Autowired
	private OauthAccessTokenService oauthAccessTokenService;
	
	@Autowired
	private OauthRefreshTokenService oauthRefreshTokenService;
	
	/**
	 * 根据认证对象，查询访问令牌
	 */
	@Override
	public OAuth2AccessToken getAccessToken(AuthorizationRequest authorizationRequest) {
		
		OAuth2AccessToken oauth2AccessToken = null;
		
		if( null == authorizationRequest ) {
			return null;
		}

		// 获得uid/clientId
		String uid = "";
//		PassportUser passportUser = (PassportUser) authorizationRequest.getUser();
//		if( null != passportUser && StringUtils.isNotBlank(passportUser.getUid()) ) {
//			uid = passportUser.getUid();
//		}
		String clientId = authorizationRequest.getClientId();
		
		AccessToken accessToken = oauthAccessTokenService.findAccessTokenByUidAndAppKey(uid, clientId);
		
		if( null != accessToken ) {
			oauth2AccessToken = deserializeAccessToken(accessToken.getToken());
		}

		return oauth2AccessToken;
	}
	
	/**
	 * 保存访问令牌
	 * 
	 */
	@Override
	public void storeAccessToken(OAuth2AccessToken token, AuthorizationRequest authorizationRequest) {
		String refreshToken = null;
		if (token.getRefreshToken() != null) {
			refreshToken = token.getRefreshToken().getValue();
		}

		AccessToken accessToken = new AccessToken();
		// 访问令牌MD5加密串
		accessToken.setAccessToken(token.getValue());
		// 访问令牌对象序列化
		accessToken.setToken(serializeAccessToken(token));
		// 访问令牌过期时间戳
		accessToken.setExpiration(token.getExpiration().getTime());
		// 用户名 passport用户authcookie
		String uid = "";
		String authcookie = "";
//		PassportUser passportUser = (PassportUser) authorizationRequest.getUser();
//		if( null != passportUser ) {
//			uid = passportUser.getUid();
//			authcookie = passportUser.getAuthCookie();
//		}
//		accessToken.setUid(StringUtil.parseLong(uid));
		accessToken.setAuthcookie(authcookie);
		// 客户端AppKey
		accessToken.setAppKey(authorizationRequest.getClientId());
		// 刷新令牌MD5加密串
		accessToken.setRefreshToken(refreshToken);
		// 当前时间now
		long now = System.currentTimeMillis() / 1000;
		accessToken.setCreateTime(now);
		accessToken.setUpdateTime(now);
		accessToken.setState(1);
		
		boolean result = oauthAccessTokenService.saveAccessToken(accessToken);
		if( result ) {
			logger.info("保存访问令牌" + token.getValue() + "成功");
		} else {
			logger.error("保存访问令牌" + token.getValue() + "失败");
		}
	}
	
	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		
		OAuth2AccessToken oauth2AccessToken = null;
		
		AccessToken accessToken = oauthAccessTokenService.findAccessTokenByTokenValue(tokenValue);
		
		if( null != accessToken ) {
			oauth2AccessToken = deserializeAccessToken(accessToken.getToken());
		}

		return oauth2AccessToken;
	}
	
	@Override
	public void removeAccessToken(OAuth2AccessToken token) {
		removeAccessToken(token.getValue());
	}

	public void removeAccessToken(String tokenValue) {
		boolean result = oauthAccessTokenService.removeAccessToken(tokenValue);
		if( result ) {
			logger.info("逻辑删除访问令牌" + tokenValue + "成功");
		} else {
			logger.error("逻辑删除访问令牌" + tokenValue + "失败");
		}
	}
	
	/**
	 * 保存刷新令牌
	 * 
	 */
	@Override
	public void storeRefreshToken(OAuth2RefreshToken oauth2RefreshToken, AuthorizationRequest authorizationRequest) {
		
		RefreshToken refreshToken = new RefreshToken();
		// 刷新令牌MD5加密串
		refreshToken.setRefreshToken(oauth2RefreshToken.getValue());
		// 刷新令牌对象序列化
		refreshToken.setToken(serializeRefreshToken(oauth2RefreshToken));
		// 刷新令牌过期时间戳
		refreshToken.setExpiration(oauth2RefreshToken.getExpiration().getTime());
		// 当前时间now
		long now = System.currentTimeMillis() / 1000;
		refreshToken.setCreateTime(now);
		refreshToken.setUpdateTime(now);
		refreshToken.setState(1);
		
		boolean result = oauthRefreshTokenService.saveRefreshToken(refreshToken);
		if( result ) {
			logger.info("保存刷新令牌" + oauth2RefreshToken.getValue() + "成功");
		} else {
			logger.error("保存刷新令牌" + oauth2RefreshToken.getValue() + "失败");
		}
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String token) {
		
		OAuth2RefreshToken oauth2RefreshToken = null;

		RefreshToken refreshToken = oauthRefreshTokenService.findRefreshTokenByTokenValue(token);

		if( null != refreshToken ) {
			oauth2RefreshToken = deserializeRefreshToken(refreshToken.getToken());
		}
		
		return oauth2RefreshToken;
	}

	@Override
	public void removeRefreshToken(OAuth2RefreshToken token) {
		removeRefreshToken(token.getValue());
	}
	
	public void removeRefreshToken(String token) {
		boolean result = oauthRefreshTokenService.removeRefreshToken(token);
		if( result ) {
			logger.info("逻辑删除刷新令牌" + token + "成功");
		} else {
			logger.error("逻辑删除刷新令牌" + token + "失败");
		}
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		removeAccessTokenUsingRefreshToken(refreshToken.getValue());
	}

	public void removeAccessTokenUsingRefreshToken(String refreshToken) {
		boolean result = oauthAccessTokenService.removeAccessTokenUsingRefreshToken(refreshToken);
		if( result ) {
			logger.info("逻辑删除与刷新令牌" + refreshToken + "绑定的访问令牌成功");
		} else {
			logger.error("逻辑删除与刷新令牌" + refreshToken + "绑定的访问令牌失败");
		}
	}
	
	protected byte[] serializeAccessToken(OAuth2AccessToken token) {
		return SerializationUtils.serialize(token);
	}

	protected byte[] serializeRefreshToken(OAuth2RefreshToken token) {
		return SerializationUtils.serialize(token);
	}
	
	protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}
}
