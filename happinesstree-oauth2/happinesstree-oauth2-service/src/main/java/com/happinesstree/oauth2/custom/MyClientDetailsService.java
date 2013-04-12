package com.happinesstree.oauth2.custom;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happinesstree.oauth2.clients.BaseClientDetails;
import com.happinesstree.oauth2.clients.ClientDetails;
import com.happinesstree.oauth2.clients.ClientDetailsService;
import com.happinesstree.oauth2.dao.domain.AppInfo;
import com.happinesstree.oauth2.exceptions.InvalidClientException;
import com.happinesstree.oauth2.service.AppInfoService;

/**
 * 
 * @Title: QiyiClientDetailsService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 客户端（App）信息服务实现
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
@Service("myClientDetailsService")
public class MyClientDetailsService implements ClientDetailsService {

	@Autowired
	private AppInfoService opAppService;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
		
		AppInfo opAppInfo = opAppService.getAppInfoByAppKey(clientId);
		
		// 客户端为空，则提示OAuth2 ERROR
		if( null == opAppInfo ) {
			throw new InvalidClientException("invalid_client");
		}
		
		BaseClientDetails clientDetails = new BaseClientDetails();
		
		// 客户端ID
		clientDetails.setClientId(clientId);
		
		// 客户端密钥secret
		clientDetails.setClientSecret(StringUtils.trim(opAppInfo.getAppSecret()));
		
		// 获得客户端redirect_uri
		String registeredRedirectUris = StringUtils.trim(opAppInfo.getRedirectUri());
				
		Set<String> uriSet = this.getClientConfigInfo(registeredRedirectUris);
		
		clientDetails.setRegisteredRedirectUri(uriSet);
		
		// 获得客户端Grant type
		String grantTypes = StringUtils.trim(opAppInfo.getAuthorizedGrantTypes());
		if( StringUtils.isBlank(grantTypes) ) {
			grantTypes = "authorization_code,refresh_token,implicit";
		}
								
		Set<String> grantTypeSet = this.getClientConfigInfo(grantTypes);
		
		clientDetails.setAuthorizedGrantTypes(grantTypeSet);
		
		// 访问令牌过期时间
		clientDetails.setAccessTokenValiditySeconds(opAppInfo.getAccessTokenValidity());
		
		// 刷新令牌过期时间
		clientDetails.setRefreshTokenValiditySeconds(opAppInfo.getRefreshTokenValidity());
		
		return clientDetails;
	}
	
	/**
	 * 集中处理客户端数据表各项中含有(,)逗号
	 * 
	 * @param config
	 * @return
	 */
	private Set<String> getClientConfigInfo( String config ) {
		
		if( StringUtils.isBlank(config) ) {
			return null;
		}
		
		String[] configArr = config.split(",");
		
		Set<String> configSet = new HashSet<String>();
		
		for( String item : configArr ) {
			configSet.add(item);
		}
		
		return configSet;
	}

}
