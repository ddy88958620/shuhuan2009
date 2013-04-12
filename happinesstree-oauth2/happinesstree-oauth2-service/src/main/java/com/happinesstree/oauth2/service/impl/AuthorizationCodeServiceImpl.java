package com.happinesstree.oauth2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiyi.videocloud.openplatform.dao.mapper.OauthCodeMapper;
import com.qiyi.videocloud.openplatform.domain.OauthCode;
import com.qiyi.videocloud.openplatform.service.OauthCodeService;

/**
 * 
 * @Title: OAuthCodeServiceImpl.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0授权码业务处理实现
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@Service("oauthCodeService")
public class AuthorizationCodeServiceImpl implements AuthorizationCodeService {

	@Autowired
	OauthCodeMapper oauthCodeMapper;

	@Override
	public boolean saveOauthCode(AuthorizationCode oauthCode) {
		int result = oauthCodeMapper.insert(oauthCode);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public AuthorizationCode findOauthCodeByCode(String code) {
		return oauthCodeMapper.selectByCode(code);
	}

	@Override
	public boolean removeOauthCodeById(int id) {
		int result = oauthCodeMapper.deleteById(id);
		if( result > 0 ) {
			return true;
		} else {
			return false;
		}
	}
}
