package com.happinesstree.oauth2.service;

import com.happinesstree.oauth2.dao.domain.AccessToken;
import com.happinesstree.oauth2.dao.domain.AppInfo;

/**
 * 
 * @Title: AccessTokenService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0访问令牌处理接口
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface AccessTokenService {

	/**
	 * 保存访问令牌
	 * 
	 * @param accessToken
	 * @return
	 */
	boolean saveAccessToken(AccessToken accessToken);

	/**
	 * 根据访问令牌字符串，查询访问令牌
	 * 
	 * @param tokenValue
	 * @return
	 */
	AccessToken findAccessTokenByTokenValue(String tokenValue);
	
	/**
	 * 根据访问令牌字符串，查询访问令牌
	 * 
	 * @param tokenValue
	 * @return
	 */
	AccessToken findAccessTokenByExpiredTokenValue(String tokenValue);

	/**
	 * 检查访问令牌是否有效
	 * 
	 * @param tokenValue
	 * @return
	 */
	public String checkAccessTokenValid(String tokenValue);
	
	/**
	 * 根据访问令牌，获取App信息
	 * 
	 * @param tokenValue
	 * @return
	 */
	public AppInfo getOpAppInfoByAccessToken(String tokenValue);
	
	/**
	 * 根据访问令牌，获取App信息，不需要验证token是否有效
	 * 
	 * @param tokenValue
	 * @return
	 */
	public AppInfo getOpAppInfoByAccessToken(String tokenValue, boolean isExpiredToken);
	
	/**
	 * 根据访问令牌，获取passport用户uid
	 * 
	 * @param tokenValue
	 * @return
	 */
	public String getUidByAccessToken(String tokenValue);
	
	/**
	 * 根据访问令牌，获取passport用户对象
	 * 
	 * @param tokenValue
	 * @return
	 */
	//public PassportUser getPassportUserByAccessToken(String tokenValue);

	/**
	 * 逻辑删除访问令牌
	 * 
	 * @param tokenValue
	 * @return
	 */
	boolean removeAccessToken(String tokenValue);

	/**
	 * 删除与刷新令牌关联的访问令牌
	 * 
	 * @param md5Value
	 * @return
	 */
	boolean removeAccessTokenUsingRefreshToken(String tokenValue);
	
	/**
	 * 用户取消授权
	 * 逻辑删除访问令牌/刷新令牌
	 * 
	 * @param uid    Passport用户Uid
	 * @param appKey 用户取消授权的AppKey
	 * @return
	 */
	boolean removeTokens(String uid, String appKey);

	/**
	 * 根据用户uid/appKey，查询访问令牌
	 * 
	 * @param uid
	 * @param appKey
	 * @return
	 */
	AccessToken findAccessTokenByUidAndAppKey(long uid, String appKey);

}
