package com.happinesstree.oauth2.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.happinesstree.oauth2.dao.domain.AuthorizationCode;

/**
 * 
 * @Title: AuthorizationCodeMapper.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-13 下午2:28:03
 * @author shuhuan2009@gmail.com
 */
public interface AuthorizationCodeMapper {

    /**
     * 保存授权码
     * 
     * @param record
     * @return
     */
    int insert(AuthorizationCode record);


    /**
     * 根据主键ID,查询授权码
     * 
     * @param id
     * @return
     */
    AuthorizationCode selectByPrimaryKey(@Param("id") Integer id);
    
    /**
     * 根据授权码字符串,查询授权码
     * 
     * @param id
     * @return
     */
    AuthorizationCode selectByCode(@Param("code") String code);

    /**
	 * 根据授权码对象ID,逻辑删除授权码
	 * 【将state修改为0】
	 * 
	 * @param code
	 * @return
	 */
    int deleteById(@Param("id") int id);
    
}