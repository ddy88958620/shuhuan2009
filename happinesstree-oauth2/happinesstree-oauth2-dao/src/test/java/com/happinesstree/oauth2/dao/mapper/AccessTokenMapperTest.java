package com.happinesstree.oauth2.dao.mapper;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.happinesstree.oauth2.dao.domain.AccessToken;
import com.happinesstree.oauth2.dao.mapper.AccessTokenMapper;

/**
 * 
 * @Title: AccessTokenMapperTest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0访问令牌DAO测试
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午4:22:34
 * @author shuhuan2009@gmail.com
 */
public class AccessTokenMapperTest extends CommonDaoTest {

	@Autowired
	AccessTokenMapper accessTokenMapper;
	
	@Test
	public void testInsert() {
		AccessToken accessToken = new AccessToken();
		accessToken.setAccessToken("test-access-token");
		accessToken.setToken(null);
		accessToken.setExpiration(System.currentTimeMillis());
		accessToken.setRefreshToken("test-refresh-token");
		accessToken.setAppKey("test-app-key");
		accessToken.setUid(2013L);
		accessToken.setCreateTime(System.currentTimeMillis());
		accessToken.setUpdateTime(System.currentTimeMillis());
		accessToken.setState(1);
		
		int result = accessTokenMapper.insert(accessToken);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		AccessToken accessToken = accessTokenMapper.selectByPrimaryKey(1);
		System.out.println(accessToken);
		Assert.assertNotNull(accessToken);
	}
	
	@Test
	public void testSelectByTokenValue() {
		AccessToken accessToken = accessTokenMapper.selectByTokenValue("test-access-token");
		System.out.println(accessToken);
		Assert.assertNotNull(accessToken);
	}
	
	@Test
	public void testDeleteByTokenValue() {
		int result = accessTokenMapper.deleteByTokenValue("81ac3f3323478933a71f0822b41f6a99");
		System.out.println(result);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testDeleteAccessTokenUsingRefreshToken() {
		int result = accessTokenMapper.deleteAccessTokenUsingRefreshToken("d2f7409504a12be5dbdb3e54248059d8");
		System.out.println(result);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testSelectByUidAndAppKey() {
		AccessToken accessToken = accessTokenMapper.selectByUidAndAppKey(2013L, "test-app-key");
		System.out.println(accessToken);
		Assert.assertNotNull(accessToken);
	}

}
