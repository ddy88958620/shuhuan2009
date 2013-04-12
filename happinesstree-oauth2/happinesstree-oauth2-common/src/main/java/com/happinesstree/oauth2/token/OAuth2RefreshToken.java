package com.happinesstree.oauth2.token;

import java.util.Date;

/**
 * 
 * @Title: OAuth2RefreshToken.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface OAuth2RefreshToken {

	String getValue();
	
	Date getExpiration();

}