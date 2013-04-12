package com.happinesstree.oauth2.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happinesstree.oauth2.code.RandomValueAuthorizationCodeServices;
import com.happinesstree.oauth2.dao.domain.AuthorizationCode;
import com.happinesstree.oauth2.exceptions.InvalidGrantException;
import com.happinesstree.oauth2.request.AuthorizationRequest;
import com.happinesstree.oauth2.service.AuthorizationCodeService;
import com.happinesstree.oauth2.utils.SerializationUtils;

/**
 * 
 * @Title: QiyiAuthorizationCodeService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 授权码code缓存、数据库管理实现
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@Service("myAuthorizationCodeService")
public class MyAuthorizationCodeService extends RandomValueAuthorizationCodeServices {
	
	private static final Logger logger = LoggerFactory.getLogger(MyAuthorizationCodeService.class);
	
	// 授权码有效时间，默认10min
	private int authorizationCodeValiditySeconds = 60 * 10 * 1000;
	
	@Autowired
	private AuthorizationCodeService oauthCodeService;

	/**
	 * 保存授权码
	 * 
	 */
	@Override
	protected void store(String code, AuthorizationRequest authorizationRequest) {
		
		long now = System.currentTimeMillis();
		
		// 获得过期时间戳
		long expiration = now  + authorizationCodeValiditySeconds;
		
		// 获得AuthorizationCode实例
		AuthorizationCode authorizationCode = new AuthorizationCode();
		authorizationCode.setCode(code);
		authorizationCode.setAuthorizeRequest(SerializationUtils.serialize(authorizationRequest));
		authorizationCode.setExpiration(expiration);
		authorizationCode.setCreateTime(now/1000L);
		authorizationCode.setUpdateTime(now/1000L);
		authorizationCode.setState(1);
		
		boolean result = oauthCodeService.saveOauthCode(authorizationCode);
		if( result ) {
			logger.info("存储授权码->" + code + "成功");
		} else {
			logger.error("存储授权码->" + code + "失败");
		}
		
	}
	
	/**
	 * 无需删除授权码
	 * 1、设置授权码过期
	 * 2、删除缓存
	 * 
	 */
	@Override
	public AuthorizationRequest remove(String code) {
		
		AuthorizationRequest authorizationRequest = null;
		
		// 去缓存或者数据库查找code
		AuthorizationCode authorizationCode = oauthCodeService.findOauthCodeByCode(code);
		
		if( null != authorizationCode ) {
			
			long expiration = authorizationCode.getExpiration();
			if( authorizationCode.getState() == 0 || System.currentTimeMillis() > expiration ) {
				throw new InvalidGrantException("Expired Authorization Code:" + code);
			}
			
			authorizationRequest = SerializationUtils.deserialize(authorizationCode.getAuthorizeRequest());
		} 
		
		if( null != authorizationRequest ) {
			// 逻辑删除授权码
			oauthCodeService.removeOauthCodeById(authorizationCode.getId());
		}

		return authorizationRequest;
	}

}
