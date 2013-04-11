package com.happinesstree.oauth2.dao.mapper;

import java.util.List;

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
    
    Integer deleteByAppKey(@Param("appKey") String appKey,@Param("uid") long ownerUid);

    Integer insertSelective(AppInfo record);
    
    Integer insertForAuditing(AppInfo record);
    
    Integer checkAppName(@Param("appName") String appName);
 
    AppInfo getRequestById(@Param("id")int requestId);
    
    AppInfo selectAppDetailByDeveloper(@Param("appKey") String appKey,@Param("uid") Long uid,@Param("appStatus") Integer appStatus);
    
    AppInfo selectByAppId(@Param("id")Integer appId);
    
    List<AppInfo> selectAppForAuidt(@Param("start") Integer start,@Param("offset") Integer offset);
    
    AppInfo selectByAppKey(@Param("appKey") String appKey,@Param("appStatus")Integer appStatus) ;
    
    List<AppInfo> getAppListForDeveloper(@Param("uid") long uid,@Param("start") Integer start,@Param("offset") Integer offset);
   
    List<AppInfo> getAppListForUser(@Param("uid")long uid,@Param("start") Integer start,@Param("offset") Integer offset);
    
    Long getAppNumForDeveloper(@Param("uid") long uid);
    
    Integer updateByAppDeveloperSelective(AppInfo record);
    
    Integer resetAppSecret(@Param("appKey") String appKey,@Param("uid") long ownerUid,@Param("appSecret")String appSecret);
    
    Integer updateByAppKeySelective(AppInfo record);

    AppInfo getAppInfoByToken(@Param("accessToken")String accessToken);
   
    Integer addAppQuotaUsag(@Param("appKey")String appKey,@Param("quota")long quota);
    
    List<AppInfo> selectAppByStatus(@Param("appStatus")Integer appStatus,@Param("start") Integer start,@Param("offset") Integer offset);

}