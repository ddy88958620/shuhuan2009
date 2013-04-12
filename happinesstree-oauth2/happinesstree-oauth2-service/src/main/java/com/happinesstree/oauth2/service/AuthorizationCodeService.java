package com.happinesstree.oauth2.service;

import com.happinesstree.oauth2.dao.domain.AuthorizationCode;

/**
 * 
 * @Title: OAuthCodeService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0授权码处理接口
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-2-4 下午3:17:23
 * @author shuhuan@qiyi.com
 */
public interface OauthCodeService {

	/**
	 * 保存授权码
	 * 
	 * @param oauthCode
	 * @return
	 */
	boolean saveOauthCode(AuthorizationCode oauthCode);

	/**
	 * 查询授权码
	 * 
	 * @param code
	 * @return
	 */
	AuthorizationCode findOauthCodeByCode(String code);

	/**
	 * 根据授权码对象ID,逻辑删除授权码
	 * 【将state修改为0】
	 * 
	 * @param code
	 * @return
	 */
	boolean removeOauthCodeById(int id);

}
