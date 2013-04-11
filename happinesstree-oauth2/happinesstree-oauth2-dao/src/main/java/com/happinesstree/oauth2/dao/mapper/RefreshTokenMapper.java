package com.happinesstree.oauth2.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.happinesstree.oauth2.dao.domain.RefreshToken;

/**
 * 
 * @Title: RefreshTokenMapper.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-13 下午2:28:03
 * @author shuhuan2009@gmail.com
 */
public interface RefreshTokenMapper {

    /**
     * 保存刷新令牌
     * 
     * @param record
     * @return
     */
    int insert(RefreshToken record);


    /**
     * 根据主键ID,查询访问令牌
     * 
     * @param id
     * @return
     */
    RefreshToken selectByPrimaryKey(@Param("id") Integer id);
    
    /**
     * 根据访问令牌字符串,查询访问令牌
     * 
     * @param tokenValue
     * @return
     */
    RefreshToken selectByTokenValue(@Param("tokenValue") String tokenValue);

    /**
     * 逻辑删除刷新令牌
     * 
     * @param tokenValue
     * @return
     */
	int deleteByTokenValue(@Param("tokenValue") String tokenValue);

}