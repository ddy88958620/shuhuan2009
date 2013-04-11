package com.happinesstree.oauth2.dao.domain;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 
 * @Title: AuthorizationCode.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2.0授权码
 * 对应数据库表:oauth_code
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午3:26:46
 * @author shuhuan2009@gmail.com
 */
public class AuthorizationCode implements Serializable {
	
	private static final long serialVersionUID = 3641010980714243598L;

	private int id;                // 主键ID
	
	private String code;           // 授权码
	
	private byte[] authorizeRequest; // 授权请求对象
	
	private long expiration;       // 授权码过期时间戳
	
	private long createTime;       // 创建时间戳
	
	private long updateTime;       // 更新时间戳
	
	private int state;             // 默认为1，逻辑删除为0

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte[] getAuthorizeRequest() {
		return authorizeRequest;
	}

	public void setAuthorizeRequest(byte[] authorizeRequest) {
		this.authorizeRequest = authorizeRequest;
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
		result = prime * result + Arrays.hashCode(authorizeRequest);
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + (int) (createTime ^ (createTime >>> 32));
		result = prime * result + (int) (expiration ^ (expiration >>> 32));
		result = prime * result + id;
		result = prime * result + state;
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
		AuthorizationCode other = (AuthorizationCode) obj;
		if (!Arrays.equals(authorizeRequest, other.authorizeRequest))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (createTime != other.createTime)
			return false;
		if (expiration != other.expiration)
			return false;
		if (id != other.id)
			return false;
		if (state != other.state)
			return false;
		if (updateTime != other.updateTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OauthCode [id=" + id + ", code=" + code + ", authorizeRequest="
				+ Arrays.toString(authorizeRequest) + ", expiration="
				+ expiration + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", state=" + state + "]";
	}

}