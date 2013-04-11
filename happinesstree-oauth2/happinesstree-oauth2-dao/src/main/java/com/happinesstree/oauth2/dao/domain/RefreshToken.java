package com.happinesstree.oauth2.dao.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @Title: RefreshToken.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0刷新令牌
 * 对应数据库表:oauth_refresh_token
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午3:30:18
 * @author shuhuan2009@gmail.com
 */
public class RefreshToken implements Serializable {
	
	private static final long serialVersionUID = 7458692065014738410L;

	private int id;               // 主键ID
	
	private String refreshToken;  // 刷新令牌refresh_token字符串
	
	private byte[] token;         // 刷新令牌refresh_token
	
	private long expiration;      // 刷新令牌过期时间戳
	
	private long createTime;      // 创建时间戳
	
	private long updateTime;      // 更新时间戳
	
	private int state;            // 默认为1，逻辑删除为0

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
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
		result = prime * result + (int) (createTime ^ (createTime >>> 32));
		result = prime * result + (int) (expiration ^ (expiration >>> 32));
		result = prime * result + id;
		result = prime * result
				+ ((refreshToken == null) ? 0 : refreshToken.hashCode());
		result = prime * result + state;
		result = prime * result + Arrays.hashCode(token);
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
		RefreshToken other = (RefreshToken) obj;
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
		if (updateTime != other.updateTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OauthRefreshToken [id=" + id + ", refreshToken=" + refreshToken
				+ ", token=" + Arrays.toString(token) + ", expiration="
				+ expiration + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", state=" + state + "]";
	}

}