package com.happinesstree.oauth2.utils;

import com.happinesstree.oauth2.clients.ClientDetails;
import com.happinesstree.oauth2.exceptions.OAuth2Exception;

/**
 * 
 * @Title: RedirectResolver.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan2009@gmail.com
 */
public interface RedirectResolver {

  String resolveRedirect(String requestedRedirect, ClientDetails client) throws OAuth2Exception;

}
