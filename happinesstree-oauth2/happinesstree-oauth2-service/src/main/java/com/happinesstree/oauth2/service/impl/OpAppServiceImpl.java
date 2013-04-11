package com.happinesstree.oauth2.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.qiyi.videocloud.openplatform.OpConstants;
import com.qiyi.videocloud.openplatform.annotation.DBAccess;
import com.qiyi.videocloud.openplatform.dao.mapper.OpAppInfoMapper;
import com.qiyi.videocloud.openplatform.dao.mapper.OpAppResourceRelationMapper;
import com.qiyi.videocloud.openplatform.dao.mapper.OpAppUserPermissionMapper;
import com.qiyi.videocloud.openplatform.dao.mapper.OpResourceInfoMapper;
import com.qiyi.videocloud.openplatform.dao.mapper.UploadDailyReportMapper;
import com.qiyi.videocloud.openplatform.domain.OpAppInfo;
import com.qiyi.videocloud.openplatform.domain.OpAppResourceRelation;
import com.qiyi.videocloud.openplatform.domain.OpAppUserPermission;
import com.qiyi.videocloud.openplatform.domain.OpResourceInfo;
import com.qiyi.videocloud.openplatform.domain.UploadDailyReport;
import com.qiyi.videocloud.openplatform.service.OauthAccessTokenService;
import com.qiyi.videocloud.openplatform.service.OpAppService;
import com.qiyi.videocloud.openplatform.service.OpPermissionService;
import com.qiyi.videocloud.openplatform.type.AppRequestStatus;
import com.qiyi.videocloud.openplatform.type.AppResApproveStatus;
import com.qiyi.videocloud.openplatform.type.AppStatus;
import com.qiyi.videocloud.openplatform.type.AppType;
import com.qiyi.videocloud.openplatform.type.OwnerType;
import com.qiyi.videocloud.openplatform.type.UserAppAuthStatus;
import com.qiyi.videocloud.openplatform.util.MD5EncoderUtils;
import com.qiyi.videocloud.openplatform.util.OpenPlatformException;
import com.qiyi.videocloud.openplatform.util.PaginationUtils;

@Service("opAppService")
public class OpAppServiceImpl implements OpAppService {
	
	@Autowired
	OpAppInfoMapper appMapper;
	
	@Autowired
	OpAppUserPermissionMapper appUserPermissionMapper;
	
	@Autowired
	OpAppResourceRelationMapper appResourceRelationMapper;
	
	@Autowired
	OpResourceInfoMapper opResourceInfoMaper;
	
	@Autowired
	UploadDailyReportMapper uploadDailyReportMapper;
	
	@Autowired
	OauthAccessTokenService oauthAccessTokenSerive;

	
	@Autowired
	OpPermissionService opPermissionService;
	
	@Override
	@DBAccess(readOnly=true)
	public List<AppInfo> getAppListForDeveloper(Long uid, int pageNum,
			int pageSize) {
		
		
		Assert.notNull(uid,"用户id为空");
		
		return appMapper.getAppListForDeveloper(uid, null, null);

	}

	@Override
	@DBAccess(readOnly=true)
	public List<AppInfo> getAppListForUser(Long uid, int pageNum, int pageSize) {
		
		Assert.notNull(uid,"用户id为空");
	
		return this.appMapper.getAppListForUser(uid, null, null);
	}

	@Override
	@DBAccess(readOnly=true)
	public AppInfo getAppDetailForDeveloper(Long uid, String appKey) {
		
		Assert.hasText(appKey,"appKey为空");
		Assert.notNull(uid,"开发者信息为空");
		
		return appMapper.selectAppDetailByDeveloper(appKey,uid,null);
	}

	@Override
	@DBAccess
	public Integer newAppRequest(AppInfo appInfo) {
		
		Assert.notNull(appInfo,"应用信息为空");
		Assert.notNull(appInfo.getOwnerUid(),"应用开发者信息为空");
		Assert.hasText(appInfo.getAppName(),"应用名称为空");
		Assert.hasText(appInfo.getAppDesc(),"应用描述为空");
		Assert.notNull(appInfo.getAppType(),"应用类型为空");
		Assert.notNull(appInfo.getAppTotalQuota(),"上传quota为空");
		Assert.state(appInfo.getAppTotalQuota()>=0, "上传quota必须为正数");
		
		AppType appType = AppType.getByCode(appInfo.getAppType());
		Assert.notNull(appType,"应用类型错误");
		
		Assert.notNull(appInfo.getOwnerType(),"开发者类型为空");
		OwnerType ownerType = OwnerType.getByCode(appInfo.getOwnerType());
		Assert.notNull(ownerType,"开发者类型错误");
		
		Integer count = this.appMapper.checkAppName(appInfo.getAppName());
		if(count>0){
			throw new OpenPlatformException(OpConstants.CODE_ILLEGAL_ARGUMENT,"应用名称已存在");
		}
		
		long now=System.currentTimeMillis()/1000;
		
		appInfo.setAppStatus(AppStatus.NEW_APP_REQUEST.getCode());
		appInfo.setAppKey(this.generateAppKey(appInfo));
		appInfo.setAppSecret(this.generateAppSecret(appInfo));
		appInfo.setCreateTime(now);
		appInfo.setUpdateTime(now);
		appInfo.setIsAuditing(OpConstants.APP_IS_AUDITING);
		
		//insert for auditing
		Integer ret=this.appMapper.insertForAuditing(appInfo);
		if(ret!=1){
			throw new OpenPlatformException(OpConstants.CODE_DATABASE_ERROR,"数据访问异常");
		}
		
		//insert main record
		return appMapper.insertSelective(appInfo);
	}

	@Override
	@DBAccess
	public Integer updateAppByDeveloper(AppInfo appInfo) {
		
		Assert.notNull(appInfo,"应用信息为空");
		Assert.hasText(appInfo.getAppKey(),"appKey为空");
		Assert.notNull(appInfo.getOwnerUid(), "开发者id为空");
		AppInfo targetApp = 
				appMapper.selectAppDetailByDeveloper(appInfo.getAppKey(), appInfo.getOwnerUid(),AppStatus.AUDIT_OK.getCode());
		
		if(targetApp==null){
			throw new OpenPlatformException(OpConstants.CODE_APP_NOT_EXIST,"应用不合法");
		}
	
		if(StringUtils.isNotEmpty(appInfo.getAppName())&&(!targetApp.getAppName().equals(appInfo.getAppName()))){
			Integer count = this.appMapper.checkAppName(appInfo.getAppName());
			if(count==1){
				throw new OpenPlatformException(OpConstants.CODE_ILLEGAL_ARGUMENT,"应用名称已存在");
			}
		}
		
		AppInfo updateRequest=new AppInfo();
		
		updateRequest.setAppKey(appInfo.getAppKey());
		
		//可以直接编辑的字段
		updateRequest.setAppDesc(appInfo.getAppDesc());
		updateRequest.setAppHomepage(appInfo.getAppHomepage());
		updateRequest.setAppIcon(appInfo.getAppIcon());
		updateRequest.setAppName(appInfo.getAppName());
		updateRequest.setAppPlatforms(appInfo.getAppPlatforms());
		updateRequest.setAppTag(appInfo.getAppTag());
		updateRequest.setOwnerUid(appInfo.getOwnerUid());
		updateRequest.setVideoNotifyUrl(appInfo.getVideoNotifyUrl());
		updateRequest.setRedirectUri(appInfo.getRedirectUri());
		long time=System.currentTimeMillis()/1000;
		
		updateRequest.setUpdateTime(time);
		
		return appMapper.updateByAppDeveloperSelective(updateRequest);
	}

	@Override
	@DBAccess
	public Integer updateAppRequest(AppInfo appInfo) {
		
		Assert.notNull(appInfo,"应用信息为空");
		Assert.hasText(appInfo.getAppKey(),"appKey为空");
		Assert.notNull(appInfo.getOwnerUid(),"开发者信息为空");
		
		if(appInfo.getAppTotalQuota()!=null){
			Assert.state(appInfo.getAppTotalQuota()>=0, "上传quota必须为正数");
		}
		
		AppInfo targetApp = 
				appMapper.selectAppDetailByDeveloper(appInfo.getAppKey(), appInfo.getOwnerUid(),AppStatus.AUDIT_OK.getCode());
		
		if(targetApp==null){
			throw new OpenPlatformException(OpConstants.CODE_APP_NOT_EXIST,"应用不合法");
		}
		
		if(targetApp.getIsAuditing()==OpConstants.APP_IS_AUDITING){
			throw new OpenPlatformException(OpConstants.CODE_APP_ALREADY_AUDITING, "应用正在审核中");
		}
		
		long time=System.currentTimeMillis()/1000;

		
		AppInfo updateRequest = new AppInfo();
		updateRequest.setAppKey(targetApp.getAppKey());
		updateRequest.setAppName(targetApp.getAppName());
		updateRequest.setAppIcon(targetApp.getAppIcon());
		updateRequest.setAppDesc(targetApp.getAppDesc());
		updateRequest.setAppType(targetApp.getAppType());
		updateRequest.setAppPlatforms(targetApp.getAppPlatforms());
		
		updateRequest.setIsAuditing(OpConstants.APP_IS_AUDITING);
		updateRequest.setAppStatus(AppRequestStatus.UPDATE_REQUEST.getCode());
		updateRequest.setUpdateTime(time);
		updateRequest.setCreateTime(time);
		
		//需要审批的变更字段
		updateRequest.setAppTotalQuota(appInfo.getAppTotalQuota());
	
		//添加审批记录
		Integer ret=this.appMapper.insertForAuditing(updateRequest);
		if(ret!=1){
			throw new OpenPlatformException(OpConstants.CODE_DATABASE_ERROR,"数据访问异常");
		}
		
		//更新app状态
		targetApp.setIsAuditing(OpConstants.APP_IS_AUDITING);
		return appMapper.updateByAppKeySelective(targetApp);
		
	}

	@Override
	@DBAccess
	public Integer resetAppSecret(Long uid, String appKey) {
		Assert.hasText(appKey,"appKey为空");
		Assert.notNull(uid,"开发者信息为空");
		
		AppInfo targetApp = 
				appMapper.selectAppDetailByDeveloper(appKey, uid,AppStatus.AUDIT_OK.getCode());
		
		if(targetApp==null){
			throw new OpenPlatformException(OpConstants.CODE_APP_NOT_EXIST,"应用不合法");
		}
	
		return this.appMapper.resetAppSecret(appKey, uid, this.generateAppSecret(targetApp));
		
	
	}

	@Override
	@DBAccess
	public Integer deleteAppByDeveloper(Long uid, String appKey) {
		Assert.hasText(appKey,"appKey为空");
		Assert.notNull(uid,"开发者id为空");
		
		AppInfo targetApp = 
				appMapper.selectAppDetailByDeveloper(appKey, uid,AppStatus.AUDIT_OK.getCode());
		
		if(targetApp==null){
			throw new OpenPlatformException(OpConstants.CODE_APP_NOT_EXIST,"应用不合法");
		}
		
		return this.appMapper.deleteByAppKey(appKey,uid);
	}

	@Override
	@DBAccess(readOnly=true)
	public List<OpResourceInfo> getAppPermissions(Long uid, String appKey) {
		AppInfo targetApp = 
				appMapper.selectAppDetailByDeveloper(appKey, uid,null);
		
		if(targetApp!=null){
			return this.opResourceInfoMaper.selectAppPermission(targetApp.getId());
		}else{
			throw new OpenPlatformException(OpConstants.CODE_APP_NOT_EXIST,"未找到应用");
		}
		
		
	}

	@Override
	@DBAccess
	public Integer applyAppPermission(Long uid, String appKey, int resourceId,
			String applyDesc) {
		
		AppInfo targetApp = 
				appMapper.selectAppDetailByDeveloper(appKey, uid,AppStatus.AUDIT_OK.getCode());
		
		if(targetApp!=null){
			long time=System.currentTimeMillis()/1000;
			
			OpAppResourceRelation record=new OpAppResourceRelation();
			record.setApplyReason(applyDesc);
			record.setApproveStatus(AppResApproveStatus.WAIT_FOR_AUDIT.getCode());
			record.setUpdateTime(time);
			record.setCreateTime(time);
			
			OpAppResourceRelation permission=new OpAppResourceRelation();
			permission.setAppId(targetApp.getId());
			permission.setResourceId(resourceId);
			List<OpAppResourceRelation> result = this.appResourceRelationMapper.checkAppResourcePermission(permission);
			if(result.size()==0){
				
				record.setResourceId(resourceId);
				record.setState(1);
				record.setAppId(targetApp.getId());
				
				return this.appResourceRelationMapper.insertSelective(record);
			}else{
				OpAppResourceRelation currentRecord = result.get(0);
				if(currentRecord.getApproveStatus()==AppResApproveStatus.AUDIT_OK.getCode()){
					throw new OpenPlatformException(OpConstants.CODE_ILLEGAL_ARGUMENT,"用户已经拥有此权限");
				}else{
					//update existing record, change to wait for audit
					record.setApproveDate(0L);
					record.setId(currentRecord.getId());
					record.setApproveDesc("");
					
					return this.appResourceRelationMapper.updateByPrimaryKeySelective(record);
				}
			}
			
			
		}else{
			throw new OpenPlatformException(OpConstants.CODE_APP_NOT_EXIST,"应用不合法");
		}
	}

	@Override
	@DBAccess
	public Integer setUserAppPermission(Long uid, String appKey,
			int resourceId, UserAppAuthStatus status) {
		
		AppInfo appInfo = this.appMapper.selectByAppKey(appKey,AppStatus.AUDIT_OK.getCode());
		if(appInfo!=null){
			
			long time=System.currentTimeMillis()/1000;
			
			OpAppUserPermission permission = this.appUserPermissionMapper.getAppUserPermission(appInfo.getId(), uid, resourceId);
			if(permission==null){
				//新增记录
				permission=new OpAppUserPermission();
				permission.setAppId(appInfo.getId());
				permission.setAuthStatus(status.getCode());
				permission.setCreateTime(time);
				permission.setResourceId(resourceId);
				permission.setUid(uid);
				permission.setUpdateTime(time);
				
				return this.appUserPermissionMapper.insertSelective(permission);
			}else if(permission.getAuthStatus()!=status.getCode()){
				//记录已经存在，更新状态
				permission.setAuthStatus(status.getCode());
				permission.setUpdateTime(time);
				return this.appUserPermissionMapper.updateByPrimaryKeySelective(permission);
			}else{
				//不需要更改
				return 1;
			}
		}else{
			throw new OpenPlatformException(OpConstants.CODE_APP_NOT_EXIST,"应用不合法");
		}
	
	}

	@Override
	@DBAccess
	public Integer addAppQuotaUsage(String appKey, long quota) {
		Assert.hasText(appKey,"appKey为空");
		Assert.state(quota>0, "quota 必须为正");
		
		AppInfo appInfo = this.appMapper.selectByAppKey(appKey, AppStatus.AUDIT_OK.getCode());
		if(appInfo==null){
			throw new OpenPlatformException(OpConstants.CODE_APP_INVALID,"应用不合法");
		}
		
		Integer ret = this.appMapper.addAppQuotaUsag(appKey, quota);
		
		if(ret!=null){
			return ret;
		}else{
			throw new OpenPlatformException(OpConstants.CODE_DATABASE_ERROR,null);
		}
	}

	
	@Override
	public String generateAppKey(AppInfo appInfo) {
		String uuid=java.util.UUID.randomUUID().toString();
		return StringUtils.join(uuid.split("-"));
	}

	
	@Override
	public String generateAppSecret(AppInfo appInfo) {	
		return MD5EncoderUtils.md5(java.util.UUID.randomUUID().toString());
	}
	

	@Override
	public AppInfo getAppInfoByToken(String accessToken) {
		
		return null;
	}

	@Override
	@DBAccess(readOnly=true)
	public AppInfo getAppInfoByAppKey(String appKey) {
		Assert.hasText(appKey,"appkey为空");
		return this.appMapper.selectByAppKey(appKey,null);
	}

	
	@Override
	@DBAccess(readOnly=true)
	public List<AppInfo> getAppForAudit(int pageNum, int pageSize) {
		int start=PaginationUtils.getBegin(pageNum, pageSize);
		return this.appMapper.selectAppForAuidt(start, pageSize);
	}

	@Override
	public Integer cancelPermission(String appKey,Long uid) {
		AppInfo targetApp = this.appMapper.selectByAppKey(appKey, AppStatus.AUDIT_OK.getCode());
		if(targetApp!=null){
			if(this.oauthAccessTokenSerive.removeTokens(String.valueOf(uid), appKey)
					&&this.opPermissionService.removeUserAppAuth(appKey, uid)){
				return 1;
			}else{
				return 0;
			}		
					
		}else{
			throw new OpenPlatformException(OpConstants.CODE_APP_NOT_EXIST,"应用不合法");
		}
		
	
		
	}

	
	@Override
	@DBAccess(readOnly=true)
	public List<UploadDailyReport> getUploadReport(String appKey,
			String startDate, String endDate, Integer pageSize, Integer pageNumber) {
		List<UploadDailyReport> results=new ArrayList<UploadDailyReport>();
		
		if(pageSize==null){
			pageSize=OpConstants.PAGESIZE;
		}
		
		if(pageNumber==null){
			pageNumber=1;
		}
		
		Integer begin=this.dateStrToInt(startDate);
		Integer end=this.dateStrToInt(endDate);
	
		
		int start=PaginationUtils.getBegin(pageNumber, pageSize);
		
		AppInfo appInfo = this.appMapper.selectByAppKey(appKey, AppStatus.AUDIT_OK.getCode());
		if(appInfo!=null&&begin!=null&&end!=null){
			results=this.uploadDailyReportMapper.selectByPeriod(appInfo.getId(), begin, end, start, pageSize);
		}
		
		return results;
	}
	
	@Override
	@DBAccess(readOnly=true)
	public int getUploadReportCount(String appKey,
			String startDate, String endDate) {
		
		Integer begin=this.dateStrToInt(startDate);
		Integer end=this.dateStrToInt(endDate);
	
		
		AppInfo appInfo = this.appMapper.selectByAppKey(appKey, AppStatus.AUDIT_OK.getCode());
		if(appInfo!=null&&begin!=null&&end!=null){
			 return this.uploadDailyReportMapper.selectByPeriodCount(appInfo.getId(), begin, end);
		}
		
		return 0;
	}
	
	private Integer dateStrToInt(String dateStr){
		try{
			if(StringUtils.isNotBlank(dateStr)){
				return Integer.parseInt(StringUtils.join(StringUtils.split(dateStr, "-"),""));
			}
		}catch(Exception e){
		}
		return null;
	}

}
