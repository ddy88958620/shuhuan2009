package com.happinesstree.oauth2.token;

import java.util.Date;

/**
 * 
 * @Title: OAuth2RefreshToken.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:24:49
 * @author shuhuan@qiyi.com
 */
public interface OAuth2RefreshToken {

	String getValue();
	
	Date getExpiration();

}