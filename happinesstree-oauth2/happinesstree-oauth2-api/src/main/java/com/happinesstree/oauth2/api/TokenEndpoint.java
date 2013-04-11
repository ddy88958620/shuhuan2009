package com.happinesstree.oauth2.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.happinesstree.oauth2.clients.ClientDetailsService;
import com.happinesstree.oauth2.code.RandomValueAuthorizationCodeServices;
import com.happinesstree.oauth2.common.Constants;
import com.happinesstree.oauth2.exceptions.InvalidGrantException;
import com.happinesstree.oauth2.exceptions.InvalidRequestException;
import com.happinesstree.oauth2.exceptions.UnsupportedGrantTypeException;
import com.happinesstree.oauth2.request.AuthorizationRequest;
import com.happinesstree.oauth2.request.AuthorizationRequestManager;
import com.happinesstree.oauth2.request.DefaultAuthorizationRequest;
import com.happinesstree.oauth2.request.DefaultAuthorizationRequestManager;
import com.happinesstree.oauth2.token.OAuth2AccessToken;

/**
 * 
 * @Title: TokenEndpoint.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 获取访问令牌/刷新令牌
 * /oauth2/token
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午1:58:18
 * @author shuhuan@qiyi.com
 */
@Controller
@RequestMapping(value = "/oauth2/token")
public class TokenEndpoint extends BaseController {

	// 客户端App管理
	@Resource(name="qiyiClientDetailsService")
	private ClientDetailsService clientDetailsService;
	
	// 授权码code管理
	@Resource(name="qiyiAuthorizationCodeService")
	private RandomValueAuthorizationCodeServices authorizationCodeService;
	
	// 令牌管理
	@Resource(name="qiyiTokenService")
	private QiyiTokenService qiyiTokenService;
	
	// 授权请求管理
	private AuthorizationRequestManager authorizationRequestManager = new DefaultAuthorizationRequestManager();
	
	@RequestMapping
	public ResponseEntity<Map<String, Object>> getAccessToken(
			@RequestParam(value = "grant_type", required = false) String grantType,
			@RequestParam(value = "client_id", required = false) String clientId,
			@RequestParam Map<String, String> parameters) {

		if (!StringUtils.hasText(grantType)) {
			throw new InvalidRequestException(Constants.OAUTH2_INVALID_REQUEST);
		}
		
		HashMap<String, String> request = new HashMap<String, String>(parameters);
		
		// 核查客户端clientId/clientSecret/redirect_uri
		authorizationRequestManager.validateParameters(parameters,
				clientDetailsService.loadClientByClientId(clientId));

		DefaultAuthorizationRequest authorizationRequest = new DefaultAuthorizationRequest(
				authorizationRequestManager.createAuthorizationRequest(request));
		
		OAuth2AccessToken token = null;
		if( isAuthCodeRequest(parameters) ) {
			// 授权码方式
			
			// 1、检查code是否有效
			AuthorizationRequest codeAuthorizationRequest = authorizationCodeService.remove(parameters.get("code"));
			
			if( null == codeAuthorizationRequest ) {
				throw new InvalidGrantException("Expired authorization code: " + parameters.get("code"));
			}
			
			// 检查请求code时的redirect_uri一致
			// ...
			
			authorizationRequest.setUser(codeAuthorizationRequest.getUser());
			
			// 2、生成access_token
			token = qiyiTokenService.createAccessToken(authorizationRequest);
			
		} else if( isRefreshTokenRequest(parameters) ) {
			// 刷新访问令牌
			String refreshTokenValue = parameters.get("refresh_token");
			
			// 2、生成access_token
			token = qiyiTokenService.refreshAccessToken(refreshTokenValue, authorizationRequest);
		}
		
		if (token == null) {
			throw new UnsupportedGrantTypeException("Unsupported grant type: " + grantType);
		}

		return getResponse(token);
	}
	
	private ResponseEntity<Map<String, Object>> getResponse(OAuth2AccessToken accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cache-Control", "no-store");
		headers.set("Pragma", "no-cache");
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("access_token", accessToken.getValue());
		result.put("refresh_token", accessToken.getRefreshToken().getValue());
		result.put("expiresIn", accessToken.getExpiresIn());
		
		return new ResponseEntity<Map<String, Object>>(result, headers, HttpStatus.OK);
	}
	
	private boolean isRefreshTokenRequest(Map<String, String> parameters) {
		return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
	}

	private boolean isAuthCodeRequest(Map<String, String> parameters) {
		return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
	}

}
