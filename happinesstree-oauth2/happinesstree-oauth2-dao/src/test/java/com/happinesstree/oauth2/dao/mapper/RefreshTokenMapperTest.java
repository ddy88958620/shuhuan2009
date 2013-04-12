package com.happinesstree.oauth2.dao.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.happinesstree.oauth2.dao.domain.RefreshToken;
import com.happinesstree.oauth2.dao.mapper.RefreshTokenMapper;

/**
 * 
 * @Title: RefreshMapperTest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 2.0刷新令牌DAO测试
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-11 下午4:40:29
 * @author shuhuan@qiyi.com
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
		System.out.println(result);
		Assert.notNull(result, "app not found");
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		
		RefreshToken refreshToken = refreshTokenMapper.selectByPrimaryKey(17);
		System.out.println(refreshToken);
		Assert.notNull(refreshToken, "app not found");
	}
	
	@Test
	public void testSelectByTokenValue() {
		
		RefreshToken refreshToken = refreshTokenMapper.selectByTokenValue("test");
		System.out.println(refreshToken);
		Assert.notNull(refreshToken, "app not found");
	}
	
	@Test
	public void testDeleteByTokenValue() {
		
		int result = refreshTokenMapper.deleteByTokenValue("1f028fe05d6f375f80077afe9993b34a");
		System.out.println(result);
		Assert.notNull(result, "app not found");
	}

}
