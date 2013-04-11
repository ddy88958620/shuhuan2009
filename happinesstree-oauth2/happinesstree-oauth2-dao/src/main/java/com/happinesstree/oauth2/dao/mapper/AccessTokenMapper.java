package com.happinesstree.oauth2.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.happinesstree.oauth2.dao.domain.AccessToken;

/**
 * 
 * @Title: AccessTokenMapper.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-13 下午2:28:03
 * @author shuhuan2009@gmail.com
 */
public interface AccessTokenMapper {
    
    /**
     * 保存访问令牌
     * 
     * @param record
     * @return
     */
    int insert(AccessToken record);

    /**
     * 根据主键ID,查询访问令牌
     * 
     * @param id
     * @return
     */
    AccessToken selectByPrimaryKey(@Param("id") Integer id);
    
    /**
     * 根据访问令牌字符串,查询访问令牌
     * 
     * @param tokenValue
     * @return
     */
    AccessToken selectByTokenValue(@Param("tokenValue") String tokenValue);
    
    /**
     * 根据过期访问令牌字符串,查询访问令牌
     * 
     * @param tokenValue
     * @return
     */
    AccessToken selectByExpiredTokenValue(String tokenValue);
    
    /**
     * 逻辑删除访问令牌
     * 
     * @param tokenValue
     * @return
     */
	int deleteByTokenValue(@Param("tokenValue") String tokenValue);

	/**
	 * 删除与刷新令牌关联的访问令牌
	 * 
	 * @param tokenValue
	 * @return
	 */
	int deleteAccessTokenUsingRefreshToken(@Param("tokenValue") String tokenValue);

	/**
	 * 根据passport用户uid，Appkey获取访问令牌对象
	 * 
	 * @param uid
	 * @param appKey
	 * @return
	 */
	AccessToken selectByUidAndAppKey(@Param("uid") long uid, @Param("appKey") String appKey);

}