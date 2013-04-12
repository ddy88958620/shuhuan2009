package com.happinesstree.oauth2.dao.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @Title: RefreshMapperTest.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * DAO测试基类
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午4:40:29
 * @author shuhuan2009@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-env-context.xml" })
public class CommonDaoTest {
	
	@Test
	public void commonTest() {
		System.out.println("OAuth2 DAO测试用例");
	}

}
