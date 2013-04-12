package com.happinesstree.oauth2.dao.domain;

import java.io.Serializable;

/**
 * 
 * @Title: User.java
 * @Copyright: Copyright (c) 2013
 * @Description: <br>
 * OAuth2.0用户
 * 对应数据库表:oauth_user
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-11 下午3:26:46
 * @author shuhuan2009@gmail.com
 */
public class User implements Serializable {
	
	private static final long serialVersionUID = 3641010980714243598L;

	private long id;               // 主键ID
	
	private String email;          // 邮箱
	
	private String password;       // 密码(md5 salt加密)
	
	private long createTime;       // 创建时间戳
	
	private long updateTime;       // 更新时间戳
	
	private int state;             // 默认为1，逻辑删除为0
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		User other = (User) obj;
		if (createTime != other.createTime)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (state != other.state)
			return false;
		if (updateTime != other.updateTime)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", state=" + state + "]";
	}

}