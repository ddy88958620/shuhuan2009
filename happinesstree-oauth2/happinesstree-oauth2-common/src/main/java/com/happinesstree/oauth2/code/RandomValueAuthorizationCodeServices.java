package com.happinesstree.oauth2.code;

import com.happinesstree.oauth2.request.AuthorizationRequest;
import com.happinesstree.oauth2.utils.RandomValueStringGenerator;

/**
 * 
 * @Title: RandomValueAuthorizationCodeServices.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-16 下午6:23:31
 * @author shuhuan2009@gmail.com
 */
public abstract class RandomValueAuthorizationCodeServices implements AuthorizationCodeServices {

	private RandomValueStringGenerator generator = new RandomValueStringGenerator();

	protected abstract void store(String code, AuthorizationRequest authorizationRequest);

	public abstract AuthorizationRequest remove(String code);

	public String createAuthorizationCode(AuthorizationRequest authorizationRequest) {
		String code = generator.generate();
		store(code, authorizationRequest);
		return code;
	}

}
