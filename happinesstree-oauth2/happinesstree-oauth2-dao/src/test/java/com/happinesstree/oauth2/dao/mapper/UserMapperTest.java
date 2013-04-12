package com.happinesstree.oauth2.dao.mapper;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.happinesstree.oauth2.dao.domain.User;
import com.happinesstree.oauth2.utils.MD5EncoderUtils;

/**
 * 
 * @Title: UserMapperTest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0用户DAO测试
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午4:35:10
 * @author shuhuan2009@gmail.com
 */
public class UserMapperTest extends CommonDaoTest{

	@Autowired
	UserMapper userMapper;
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setEmail("shuhuan2013@gmail");
		user.setPassword(MD5EncoderUtils.md5("123456"));
		user.setCreateTime(System.currentTimeMillis() / 1000);
		user.setUpdateTime(System.currentTimeMillis() / 1000);
		user.setState(1);
		
		int result = userMapper.insert(user);
		Assert.assertEquals(1, result);
	}
	
	@Test
	public void testSelectByPrimaryKey() {
		User user = userMapper.selectByPrimaryKey(1);
		System.out.println(user);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void testSelectByEmail() {
		User user = userMapper.selectByEmail("shuhuan2009@gmail.com");
		System.out.println(user);
		Assert.assertNotNull(user);
	}
	
}
