package com.happinesstree.oauth2.service;

import com.happinesstree.oauth2.dao.domain.RefreshToken;

/**
 * 
 * @Title: RefreshTokenService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0刷新令牌处理接口
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface RefreshTokenService {

	/**
	 * 保存刷新令牌
	 * 
	 * @param refreshToken
	 * @return
	 */
	boolean saveRefreshToken(RefreshToken refreshToken);

	/**
	 * 根据刷新令牌字符串，查询刷新令牌
	 * 
	 * @param tokenValue
	 * @return
	 */
	RefreshToken findRefreshTokenByTokenValue(String tokenValue);

	/**
	 * 逻辑删除刷新令牌
	 * 
	 * @param tokenValue
	 * @return
	 */
	boolean removeRefreshToken(String tokenValue);

}
