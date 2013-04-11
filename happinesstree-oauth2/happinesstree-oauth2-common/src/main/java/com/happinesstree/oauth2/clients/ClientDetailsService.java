package com.happinesstree.oauth2.clients;

import com.happinesstree.oauth2.exceptions.InvalidClientException;

/**
 * 
 * @Title: ClientDetailsService.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-16 下午6:23:45
 * @author shuhuan2009@gmail.com
 */
public interface ClientDetailsService {

	ClientDetails loadClientByClientId(String clientId) throws InvalidClientException;

}