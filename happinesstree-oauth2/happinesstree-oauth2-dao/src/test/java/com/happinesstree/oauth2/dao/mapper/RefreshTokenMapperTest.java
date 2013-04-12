package com.happinesstree.oauth2.dao.mapper;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.happinesstree.oauth2.dao.domain.RefreshToken;

/**
 * 
 * @Title: RefreshMapperTest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0刷新令牌DAO测试
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午4:40:29
 * @author shuhuan2009@gmail.com
 */
public class RefreshTokenMapperTest extends CommonDaoTest{

	@Autowired
	RefreshTokenMapper refreshTokenMapper;
	
	@Test
	public void testInsert() {
		
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken("test");
		refreshToken.setToken(null);
		refreshToken.setExpiration(System.currentTimeMillis());
		refreshToken.setCreateTime(System.currentTimeMillis());
		refreshToken.setUpdateTime(System.currentTimeMillis());
		refreshToken.setState(1);
		
		
		int result = refreshTokenMapper.insert(refreshToken);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		RefreshToken refreshToken = refreshTokenMapper.selectByPrimaryKey(17);
		System.out.println(refreshToken);
		Assert.assertNotNull(refreshToken);
	}
	
	@Test
	public void testSelectByTokenValue() {
		RefreshToken refreshToken = refreshTokenMapper.selectByTokenValue("test");
		System.out.println(refreshToken);
		Assert.assertNotNull(refreshToken);
	}
	
	@Test
	public void testDeleteByTokenValue() {
		int result = refreshTokenMapper.deleteByTokenValue("test");
		System.out.println(result);
		Assert.assertEquals(1, result);
	}

}
