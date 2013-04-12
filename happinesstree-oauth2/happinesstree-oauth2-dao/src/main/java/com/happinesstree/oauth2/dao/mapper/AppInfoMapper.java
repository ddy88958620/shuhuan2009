package com.happinesstree.oauth2.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.happinesstree.oauth2.dao.domain.AppInfo;

/**
 * 
 * @Title: AppInfoMapper.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-13 下午2:28:03
 * @author shuhuan2009@gmail.com
 */
public interface AppInfoMapper {
	
	/**
     * 保存应用APP
     * 
     * @param record
     * @return
     */
    int insert(AppInfo appInfo);
    
    /**
     * 根据主键ID,查询应用APP
     * 
     * @param id
     * @return
     */
    AppInfo selectByPrimaryKey(@Param("id") Integer id);
    

}