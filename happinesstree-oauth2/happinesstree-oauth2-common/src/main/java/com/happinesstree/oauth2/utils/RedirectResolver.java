package com.happinesstree.oauth2.utils;

import com.happinesstree.oauth2.clients.ClientDetails;
import com.happinesstree.oauth2.exceptions.OAuth2Exception;

/**
 * 
 * @Title: RedirectResolver.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午6:25:06
 * @author shuhuan@qiyi.com
 */
public interface RedirectResolver {

  String resolveRedirect(String requestedRedirect, ClientDetails client) throws OAuth2Exception;

}
