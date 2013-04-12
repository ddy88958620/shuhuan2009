package com.happinesstree.oauth2.dao.domain;

import java.io.Serializable;

/**
 * 
 * @Title: AppInfo.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 应用App
 * 对应数据库表:app_info
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-9 上午11:14:17
 * @author shuhuan2009@gmail.com
 */
public class AppInfo implements Serializable {
    
	private static final long serialVersionUID = 5810542666734280048L;

	private int id;                // 主键ID

    private String appKey;         // 应用Key

    private String appSecret;      // 应用密钥

    private String appName;        // 应用名称

    private int appType;           // 应用类型 1 pc 2 mobile

    private String appDesc;        // 应用描述

    private String appHomepage;    // 应用主页

    private String appTag;         // 应用标签

    private String appIcon;        // 应用icon URL

    private int ownerType;         // 应用所有者类型 1 个人 2 企业

    private String ownerEmail;     // 应用所有者邮箱

    private int ownerUid;          // 所有者在本系统中的Uid

    private String ownerPhone;     // 应用所有者手机号

    private String ownerName;      // 应用所有者姓名

    private String ownerCompanyName; // 应用所有者所属公司

    private String ownerAddress;      // 应用所有者公司地址

    private Long createTime;       // 创建时间戳

    private Long updateTime;       // 更新时间戳

    private String authorizedGrantTypes; // 可支持的授权方式

    private String redirectUri;          // 回调地址

    private int accessTokenValidity;     // 访问令牌有效期

    private int refreshTokenValidity;    // 刷新令牌有效期
    
    private int state;                   // 默认有效1，0为逻辑删除

    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public String getAppHomepage() {
		return appHomepage;
	}

	public void setAppHomepage(String appHomepage) {
		this.appHomepage = appHomepage;
	}

	public String getAppTag() {
		return appTag;
	}

	public void setAppTag(String appTag) {
		this.appTag = appTag;
	}

	public String getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public int getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(int ownerType) {
		this.ownerType = ownerType;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public int getOwnerUid() {
		return ownerUid;
	}

	public void setOwnerUid(int ownerUid) {
		this.ownerUid = ownerUid;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerCompanyName() {
		return ownerCompanyName;
	}

	public void setOwnerCompanyName(String ownerCompanyName) {
		this.ownerCompanyName = ownerCompanyName;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public int getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(int accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public int getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(int refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accessTokenValidity;
		result = prime * result + ((appDesc == null) ? 0 : appDesc.hashCode());
		result = prime * result
				+ ((appHomepage == null) ? 0 : appHomepage.hashCode());
		result = prime * result + ((appIcon == null) ? 0 : appIcon.hashCode());
		result = prime * result + ((appKey == null) ? 0 : appKey.hashCode());
		result = prime * result + ((appName == null) ? 0 : appName.hashCode());
		result = prime * result
				+ ((appSecret == null) ? 0 : appSecret.hashCode());
		result = prime * result + ((appTag == null) ? 0 : appTag.hashCode());
		result = prime * result + appType;
		result = prime
				* result
				+ ((authorizedGrantTypes == null) ? 0 : authorizedGrantTypes
						.hashCode());
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((ownerAddress == null) ? 0 : ownerAddress.hashCode());
		result = prime
				* result
				+ ((ownerCompanyName == null) ? 0 : ownerCompanyName.hashCode());
		result = prime * result
				+ ((ownerEmail == null) ? 0 : ownerEmail.hashCode());
		result = prime * result
				+ ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result
				+ ((ownerPhone == null) ? 0 : ownerPhone.hashCode());
		result = prime * result + ownerType;
		result = prime * result + ownerUid;
		result = prime * result
				+ ((redirectUri == null) ? 0 : redirectUri.hashCode());
		result = prime * result + refreshTokenValidity;
		result = prime * result + state;
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppInfo other = (AppInfo) obj;
		if (accessTokenValidity != other.accessTokenValidity)
			return false;
		if (appDesc == null) {
			if (other.appDesc != null)
				return false;
		} else if (!appDesc.equals(other.appDesc))
			return false;
		if (appHomepage == null) {
			if (other.appHomepage != null)
				return false;
		} else if (!appHomepage.equals(other.appHomepage))
			return false;
		if (appIcon == null) {
			if (other.appIcon != null)
				return false;
		} else if (!appIcon.equals(other.appIcon))
			return false;
		if (appKey == null) {
			if (other.appKey != null)
				return false;
		} else if (!appKey.equals(other.appKey))
			return false;
		if (appName == null) {
			if (other.appName != null)
				return false;
		} else if (!appName.equals(other.appName))
			return false;
		if (appSecret == null) {
			if (other.appSecret != null)
				return false;
		} else if (!appSecret.equals(other.appSecret))
			return false;
		if (appTag == null) {
			if (other.appTag != null)
				return false;
		} else if (!appTag.equals(other.appTag))
			return false;
		if (appType != other.appType)
			return false;
		if (authorizedGrantTypes == null) {
			if (other.authorizedGrantTypes != null)
				return false;
		} else if (!authorizedGrantTypes.equals(other.authorizedGrantTypes))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (id != other.id)
			return false;
		if (ownerAddress == null) {
			if (other.ownerAddress != null)
				return false;
		} else if (!ownerAddress.equals(other.ownerAddress))
			return false;
		if (ownerCompanyName == null) {
			if (other.ownerCompanyName != null)
				return false;
		} else if (!ownerCompanyName.equals(other.ownerCompanyName))
			return false;
		if (ownerEmail == null) {
			if (other.ownerEmail != null)
				return false;
		} else if (!ownerEmail.equals(other.ownerEmail))
			return false;
		if (ownerName == null) {
			if (other.ownerName != null)
				return false;
		} else if (!ownerName.equals(other.ownerName))
			return false;
		if (ownerPhone == null) {
			if (other.ownerPhone != null)
				return false;
		} else if (!ownerPhone.equals(other.ownerPhone))
			return false;
		if (ownerType != other.ownerType)
			return false;
		if (ownerUid != other.ownerUid)
			return false;
		if (redirectUri == null) {
			if (other.redirectUri != null)
				return false;
		} else if (!redirectUri.equals(other.redirectUri))
			return false;
		if (refreshTokenValidity != other.refreshTokenValidity)
			return false;
		if (state != other.state)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppInfo [id=" + id + ", appKey=" + appKey + ", appSecret="
				+ appSecret + ", appName=" + appName + ", appType=" + appType
				+ ", appDesc=" + appDesc + ", appHomepage=" + appHomepage
				+ ", appTag=" + appTag + ", appIcon=" + appIcon
				+ ", ownerType=" + ownerType + ", ownerEmail=" + ownerEmail
				+ ", ownerUid=" + ownerUid + ", ownerPhone=" + ownerPhone
				+ ", ownerName=" + ownerName + ", ownerCompanyName="
				+ ownerCompanyName + ", ownerAddress=" + ownerAddress
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", authorizedGrantTypes=" + authorizedGrantTypes
				+ ", redirectUri=" + redirectUri + ", accessTokenValidity="
				+ accessTokenValidity + ", refreshTokenValidity="
				+ refreshTokenValidity + ", state=" + state + "]";
	}

}