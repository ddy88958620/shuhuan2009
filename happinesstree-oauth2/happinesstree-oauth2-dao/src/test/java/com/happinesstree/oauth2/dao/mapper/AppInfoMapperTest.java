package com.happinesstree.oauth2.dao.mapper;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.happinesstree.oauth2.dao.domain.AppInfo;
import com.happinesstree.oauth2.dao.domain.User;

/**
 * 
 * @Title: AppInfoMapperTest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * App应用DAO测试
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午4:35:10
 * @author shuhuan2009@gmail.com
 */
public class AppInfoMapperTest extends CommonDaoTest{

	@Autowired
	AppInfoMapper appInfoMapper;
	
	@Test
	public void testInsert() {
		AppInfo appInfo = new AppInfo();
		appInfo.setAppKey("test-app-key");
		appInfo.setAppSecret("test-app-secret");
		appInfo.setAppName("test-app-name");
		appInfo.setAuthorizedGrantTypes("authorization_code,refresh_token,implicit");
		appInfo.setOwnerEmail("test@happinesstree.com");
		appInfo.setCreateTime(System.currentTimeMillis() / 1000);
		appInfo.setUpdateTime(System.currentTimeMillis() / 1000);
		appInfo.setState(1);
		
		int result = appInfoMapper.insert(appInfo);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		AppInfo appInfo = appInfoMapper.selectByPrimaryKey(1);
		System.out.println(appInfo);
		Assert.assertNotNull(appInfo);
	}
	
	@Test
	public void testSelectByAppKey() {
		AppInfo appInfo = appInfoMapper.selectByAppKey("test-app-key");
		System.out.println(appInfo);
		Assert.assertNotNull(appInfo);
	}
	
}
