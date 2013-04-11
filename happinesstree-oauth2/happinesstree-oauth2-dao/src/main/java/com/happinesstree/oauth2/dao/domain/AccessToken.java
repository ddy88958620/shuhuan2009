package com.happinesstree.oauth2.dao.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @Title: AccessToken.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0访问令牌
 * 对应数据库表:oauth_access_token
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午3:27:21
 * @author shuhuan2009@gmail.com
 */
public class AccessToken implements Serializable {
	
	private static final long serialVersionUID = -5509849461393217087L;

	private int id;                      // 主键ID
	
	private String accessToken;          // access_token字符串
	
	private byte[] token;                // access_token对象字节流
	
	private long expiration;             // 访问令牌过去时间戳
	
	private String appKey;               // 客户端AppKey
	
	private long uid;                    // 系统用户uid
	
	private String refreshToken;         // refresh_token字符串
	
	private long createTime;             // 创建时间戳
	
	private long updateTime;             // 更新时间戳
	
	private int state;                   // 默认有效1，0为逻辑删除

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public byte[] getToken() {
		return token;
	}

	public void setToken(byte[] token) {
		this.token = token;
	}

	public long getExpiration() {
		return expiration;
	}

	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
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
		result = prime * result
				+ ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result + ((appKey == null) ? 0 : appKey.hashCode());
		result = prime * result + (int) (createTime ^ (createTime >>> 32));
		result = prime * result + (int) (expiration ^ (expiration >>> 32));
		result = prime * result + id;
		result = prime * result
				+ ((refreshToken == null) ? 0 : refreshToken.hashCode());
		result = prime * result + state;
		result = prime * result + Arrays.hashCode(token);
		result = prime * result + (int) (uid ^ (uid >>> 32));
		result = prime * result + (int) (updateTime ^ (updateTime >>> 32));
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
		AccessToken other = (AccessToken) obj;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (appKey == null) {
			if (other.appKey != null)
				return false;
		} else if (!appKey.equals(other.appKey))
			return false;
		if (createTime != other.createTime)
			return false;
		if (expiration != other.expiration)
			return false;
		if (id != other.id)
			return false;
		if (refreshToken == null) {
			if (other.refreshToken != null)
				return false;
		} else if (!refreshToken.equals(other.refreshToken))
			return false;
		if (state != other.state)
			return false;
		if (!Arrays.equals(token, other.token))
			return false;
		if (uid != other.uid)
			return false;
		if (updateTime != other.updateTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AccessToken [id=" + id + ", accessToken=" + accessToken
				+ ", token=" + Arrays.toString(token) + ", expiration="
				+ expiration + ", appKey=" + appKey + ", uid=" + uid
				+ ", refreshToken=" + refreshToken + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", state="
				+ state + "]";
	}

}