package com.happinesstree.oauth2.token;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;


/**
 * 
 * @Title: DefaultOAuth2RefreshToken.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:24:29
 * @author shuhuan@qiyi.com
 */
public class DefaultOAuth2RefreshToken implements Serializable, OAuth2RefreshToken {

	private static final long serialVersionUID = 8349970621900575838L;

	private String value;
	
	private final Date expiration;

	/**
	 * Create a new refresh token.
	 */
	@JsonCreator
	public DefaultOAuth2RefreshToken(String value) {
		this(value, null);
	}
	
	@JsonCreator
	public DefaultOAuth2RefreshToken(String value, Date expiration) {
		this.value = value;
		this.expiration = expiration;
	}
	
	/**
	 * Default constructor for JPA and other serialization tools.
	 */
	@SuppressWarnings("unused")
	private DefaultOAuth2RefreshToken() {
		this(null, null);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.common.IFOO#getValue()
	 */
	@JsonValue
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return getValue();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DefaultOAuth2RefreshToken)) {
			return false;
		}

		DefaultOAuth2RefreshToken that = (DefaultOAuth2RefreshToken) o;

		if (value != null ? !value.equals(that.value) : that.value != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@JsonValue
	public Date getExpiration() {
		return expiration;
	}
}
