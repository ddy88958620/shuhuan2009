package com.happinesstree.oauth2.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.happinesstree.oauth2.dao.domain.User;

/**
 * 
 * @Title: UserMapper.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-13 下午2:28:03
 * @author shuhuan2009@gmail.com
 */
public interface UserMapper {

    /**
     * 保存用户信息
     * 
     * @param user
     * @return
     */
    int insert(User user);


    /**
     * 根据主键ID,查询用户信息
     * 
     * @param id
     * @return
     */
    User selectByPrimaryKey(@Param("id") Integer id);
    
    /**
     * 根据邮箱,查询用户信息
     * 
     * @param email
     * @return
     */
    User selectByEmail(@Param("code") String email);

    /**
	 * 根据主键ID,逻辑删除授权码
	 * 【将state修改为0】
	 * 
	 * @param code
	 * @return
	 */
    int deleteById(@Param("id") int id);
    
}