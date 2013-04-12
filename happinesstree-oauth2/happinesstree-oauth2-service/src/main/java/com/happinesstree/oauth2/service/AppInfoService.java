/**
 * 
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * <br>
 * @Company: iqiyi.com
 * @Created on 下午3:10:04
 * @author zhiyuan
 */
package com.happinesstree.oauth2.service;

import java.util.List;

import com.happinesstree.oauth2.dao.domain.AppInfo;

/**
 * 
 * @Title: RefreshTokenService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0刷新令牌处理接口
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface AppInfoService {
	
	AppInfo getAppInfoByAppKey(String appKey);
	
	/**
	 * 获得应用开发者帐号拥有的app列表
	 * @param uid 开发者uid
	 * @param pageNum 页数
	 * @param pageSize 每页数据量
	 * @return 开发者拥有的app列表
	 */
	public List<AppInfo> getAppListForDeveloper(Long uid,int pageNum,int pageSize);
	
	/**获得奇艺用户绑定的app列表
	 * @param uid
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<AppInfo> getAppListForUser(Long uid,int pageNum,int pageSize);
	
	public List<AppInfo> getAppForAudit(int pageNum,int pageSize);
	
	/**
	 * 应用开发者查看其某个应用的详情
	 * @param uid
	 * @param appKey
	 * @return
	 */
	public AppInfo getAppDetailForDeveloper(Long uid,String appKey);
	
	/**
	 * 应用开发者提交新app申请
	 * @param appInfo
	 * @return
	 */
	public Integer newAppRequest(AppInfo appInfo);
	
	/**
	 * 应用开发者更新应用信息（不需要审批）
	 * @param appInfo
	 * @return
	 */
	public Integer updateAppByDeveloper(AppInfo appInfo);
	
	/**
	 * 应用开发者更新app属性（需要审批）
	 * @param appInfo
	 * @return
	 */
	public Integer updateAppRequest(AppInfo appInfo);
	
	/**
	 * 应用开发者重置appSecret
	 * @param uid
	 * @param appKey
	 * @return
	 */
	public Integer resetAppSecret(Long uid,String appKey);
	
	/**
	 * 应用开发者删除应用
	 * @param uid
	 * @param appKey
	 * @return
	 */
	public Integer deleteAppByDeveloper(Long uid,String appKey);

	/**
	 * 应用开发者查看应用的权限信息
	 * @param uid
	 * @param appKey
	 */
	//public List<OpResourceInfo> getAppPermissions(Long uid,String appKey);
	
	/**
	 * 应用开发者申请资源权限
	 * @param uid
	 * @param resourceId
	 * @param applyDesc
	 * @return
	 */
	public Integer applyAppPermission(Long uid, String appKey,int resourceId,String applyDesc);
	

	/**
	 * 设置用户对app授权
	 * @param uid
	 * @param appKey
	 * @param resource_id
	 * @param status
	 * @return
	 */
	//public Integer setUserAppPermission(Long uid,String appKey,int resource_id,UserAppAuthStatus status);
	
	
	/**
	 * 新增应用quota使用
	 * @param appKey
	 * @param quota must >0
	 * @return
	 */
	public Integer addAppQuotaUsage(String appKey,long quota);
	
	/**
	 * 生成appKey
	 * @param appInfo
	 * @return appKey
	 */
	public String generateAppKey(AppInfo appInfo);
	
	/**
	 * 生成appSecret
	 * @param appInfo
	 * @return appSecret
	 */
	public String generateAppSecret(AppInfo appInfo);
	
	/**
	 * 取得appinfo
	 * @param accessToken
	 * @return
	 */
	public AppInfo getAppInfoByToken(String accessToken);

	
	/**
	 * 取消用户对应用的授权
	 * @param appKey
	 * @param uid
	 * @return
	 */
	Integer cancelPermission(String appKey,Long uid);
	
	

	/**
	 * 查询指定时间段应用上传统计
	 * @param appKey
	 * @param startDate
	 * @param endDate
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	//public List<UploadDailyReport> getUploadReport(String appKey,String startDate,String endDate,Integer pageSize,Integer pageNumber);

	/**
	 * 查询指定时间段应用上传统计个数
	 * @param appKey
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getUploadReportCount(String appKey,String startDate,String endDate);
}
