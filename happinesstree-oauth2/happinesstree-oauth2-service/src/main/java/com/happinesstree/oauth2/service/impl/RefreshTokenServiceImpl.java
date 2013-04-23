package com.happinesstree.oauth2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happinesstree.oauth2.dao.domain.RefreshToken;
import com.happinesstree.oauth2.dao.mapper.RefreshTokenMapper;
import com.happinesstree.oauth2.service.RefreshTokenService;

/**
 * 
 * @Title: RefreshTokenServiceImpl.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0刷新令牌业务处理实现
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@Service("oauthRefreshTokenService")
public class RefreshTokenServiceImpl implements RefreshTokenService {

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
