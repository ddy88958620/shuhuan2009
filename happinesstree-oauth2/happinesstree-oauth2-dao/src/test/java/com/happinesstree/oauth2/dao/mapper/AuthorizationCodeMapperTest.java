package com.happinesstree.oauth2.dao.mapper;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.happinesstree.oauth2.dao.domain.AuthorizationCode;
import com.happinesstree.oauth2.dao.mapper.AuthorizationCodeMapper;

/**
 * 
 * @Title: AuthorizationCodeMapperTest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0授权码DAO测试
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-11 下午4:35:10
 * @author shuhuan@qiyi.com
 */
public class AuthorizationCodeMapperTest extends CommonDaoTest{

	@Autowired
	AuthorizationCodeMapper authorizationCodeMapper;
	
	@Test
	public void testInsert() {
		
		AuthorizationCode authorizationCode = new AuthorizationCode();
		authorizationCode.setCode("test");
		authorizationCode.setAuthorizeRequest(null);
		authorizationCode.setExpiration(System.currentTimeMillis());
		authorizationCode.setCreateTime(System.currentTimeMillis());
		authorizationCode.setUpdateTime(System.currentTimeMillis());
		authorizationCode.setState(1);
		
		int result = authorizationCodeMapper.insert(authorizationCode);
		System.out.println(result);
		
		Assert.notNull(result, "app not found");
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		
		AuthorizationCode authorizationCode = authorizationCodeMapper.selectByPrimaryKey(22);
		System.out.println(authorizationCode);
		
		Assert.notNull(authorizationCode, "app not found");
	}
	
	@Test
	public void testSelectByCode() {
		
		AuthorizationCode authorizationCode = authorizationCodeMapper.selectByCode("test");
		System.out.println(authorizationCode);
		
		Assert.notNull(authorizationCode, "app not found");
	}
	
	@Test
	public void testRemoveById() {
		int result = authorizationCodeMapper.deleteById(26);
		System.out.println(result);
		Assert.notNull(result, "app not found");
	}

}
