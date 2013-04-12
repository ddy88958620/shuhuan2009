package com.happinesstree.oauth2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiyi.videocloud.openplatform.dao.mapper.OauthRefreshTokenMapper;
import com.qiyi.videocloud.openplatform.domain.OauthRefreshToken;
import com.qiyi.videocloud.openplatform.service.OauthRefreshTokenService;

/**
 * 
 * @Title: RefreshTokenServiceImpl.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0刷新令牌业务处理实现
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-2-4 下午3:39:05
 * @author shuhuan@qiyi.com
 */
@Service("oauthRefreshTokenService")
public class OauthRefreshTokenServiceImpl implements OauthRefreshTokenService {

	@Autowired
	RefreshTokenMapper oauthRefreshTokenMapper;

	@Override
	public boolean saveRefreshToken(RefreshToken refreshToken) {
		int result = oauthRefreshTokenMapper.insert(refreshToken);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public RefreshToken findRefreshTokenByTokenValue(String tokenValue) {
		return oauthRefreshTokenMapper.selectByTokenValue(tokenValue);
	}

	@Override
	public boolean removeRefreshToken(String tokenValue) {
		int result = oauthRefreshTokenMapper.deleteByTokenValue(tokenValue);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
	}
}
