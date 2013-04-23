package com.happinesstree.oauth2.service;

import com.happinesstree.oauth2.dao.domain.AppInfo;

/**
 * 
 * @Title: RefreshTokenService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * App应用处理接口
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface AppInfoService {
	
	/**
	 * 保存App应用
	 * 
	 * @param accessToken
	 * @return
	 */
	boolean saveAppInfo(AppInfo appInfo);
	
	/**
	 * 查询App应用
	 * 
	 * @param appKey
	 * @return
	 */
	AppInfo findAppInfoByAppKey(String appKey);
	
}
