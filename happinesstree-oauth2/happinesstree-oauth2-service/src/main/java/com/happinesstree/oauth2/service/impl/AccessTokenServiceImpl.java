package com.happinesstree.oauth2.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happinesstree.oauth2.dao.domain.AccessToken;
import com.happinesstree.oauth2.dao.domain.AppInfo;
import com.happinesstree.oauth2.dao.mapper.AccessTokenMapper;
import com.happinesstree.oauth2.dao.mapper.RefreshTokenMapper;
import com.happinesstree.oauth2.service.OauthAccessTokenService;
import com.happinesstree.oauth2.service.OpAppService;

/**
 * 
 * @Title: AccessTokenServiceImpl.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0访问令牌业务处理实现
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-2-4 下午3:39:05
 * @author shuhuan@qiyi.com
 */
@Service("oauthAccessTokenService")
public class OauthAccessTokenServiceImpl implements OauthAccessTokenService {

	@Autowired
	private AccessTokenMapper oauthAccessTokenMapper;
	
	@Autowired
	private RefreshTokenMapper oauthRefreshTokenMapper;
	
	@Autowired
	private OpAppService opAppService;
	
	@Autowired
	//private PassportService passportService;

	@Override
	public boolean saveAccessToken(AccessToken accessToken) {
		int result = oauthAccessTokenMapper.insert(accessToken);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AccessToken findAccessTokenByTokenValue(String tokenValue) {
		return oauthAccessTokenMapper.selectByTokenValue(tokenValue);
	}
	
	@Override
	public AccessToken findAccessTokenByExpiredTokenValue(String tokenValue) {
		return oauthAccessTokenMapper.selectByExpiredTokenValue(tokenValue);
	}

	@Override
	public String checkAccessTokenValid(String tokenValue) {

		String code = OpConstants.SUCCESS_CODE;
		
		try {
			AccessToken accessToken = findAccessTokenByTokenValue(tokenValue);
			if (accessToken == null) {
				code = OpConstants.CODE_INVALID_ACCESS_TOKEN;
			} else if (System.currentTimeMillis() > accessToken.getExpiration()) {
				removeAccessToken(tokenValue);
				code = OpConstants.CODE_EXPIRED_ACCESS_TOKEN;
			}
		} catch (Exception e) {
			e.printStackTrace();
			code = OpConstants.ERROR_CODE;
		}

		return code;
	}

	@Override
	public AppInfo getOpAppInfoByAccessToken(String tokenValue) {
		
		AppInfo opAppInfo = null;
		
		AccessToken accessToken = findAccessTokenByTokenValue(tokenValue);
		
		if( null != accessToken ) {
			// 获得appKey
			String appKey = accessToken.getAppKey();
			
			// 根据appKey，获取AppInfo
			opAppInfo = opAppService.getAppInfoByAppKey(appKey);
		}
		
		return opAppInfo;
	}
	
	@Override
	public AppInfo getOpAppInfoByAccessToken(String tokenValue, boolean isExpiredToken) {
		
		AppInfo opAppInfo = null;
		
		if( !isExpiredToken ) {
			opAppInfo = getOpAppInfoByAccessToken(tokenValue);
		} else {
			AccessToken accessToken = findAccessTokenByExpiredTokenValue(tokenValue);
			
			if( null != accessToken ) {
				// 获得appKey
				String appKey = accessToken.getAppKey();
				
				// 根据appKey，获取AppInfo
				opAppInfo = opAppService.getAppInfoByAppKey(appKey);
			}
		}
		
		return opAppInfo;
	}

	@Override
	public String getUidByAccessToken(String tokenValue) {
		
		String uid = null;
		
		AccessToken accessToken = findAccessTokenByTokenValue(tokenValue);
		
		if( null != accessToken ) {
			uid = accessToken.getUid() + "";
		}
		
		return uid;
	}

	@Override
	public PassportUser getPassportUserByAccessToken(String tokenValue) {
		
		PassportUser passportUser = null;
		
		AccessToken accessToken = findAccessTokenByTokenValue(tokenValue);
		
		if( null != accessToken ) {
			
			String authcookie = accessToken.getAuthcookie();
			
			if( StringUtils.isNotBlank(authcookie) ) {
				passportUser = passportService.checkAuthCookie(authcookie);
			}
			
			// unset accessToken
			accessToken = null;
		}
		
		return passportUser;
	}

	@Override
	public boolean removeAccessToken(String tokenValue) {
		int result = oauthAccessTokenMapper.deleteByTokenValue(tokenValue);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeAccessTokenUsingRefreshToken(String tokenValue) {
		int result = oauthAccessTokenMapper.deleteAccessTokenUsingRefreshToken(tokenValue);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeTokens(String uid, String appKey) {
		
		// 1、根据uid/appkey查询access_token对象
		AccessToken oauthAccessToken = oauthAccessTokenMapper.selectByUidAndAppKey(StringUtil.parseLong(uid), appKey);
		if( null == oauthAccessToken ) {
			return true;
		}
		
		// 2、获得refresh_token_key，逻辑删除刷新令牌
		String refresh_token = oauthAccessToken.getRefreshToken();
		if( StringUtils.isNotBlank(refresh_token) ) {
			oauthRefreshTokenMapper.deleteByTokenValue(refresh_token);
		}
		
		// 3、逻辑删除访问令牌
		int result = oauthAccessTokenMapper.deleteAccessTokenUsingRefreshToken(refresh_token);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public AccessToken findAccessTokenByUidAndAppKey(String uid, String appKey) {
		return oauthAccessTokenMapper.selectByUidAndAppKey(StringUtil.parseLong(uid), appKey);
	}
	
}
