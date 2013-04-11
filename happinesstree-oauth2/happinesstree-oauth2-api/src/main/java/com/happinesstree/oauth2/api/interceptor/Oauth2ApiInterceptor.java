package com.happinesstree.oauth2.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.happinesstree.oauth2.utils.RequestUtils;

/**
 * session拦截器，check access_token
 * 
 */
public class Oauth2ApiInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(Oauth2ApiInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");

		try {
			String accessToken = RequestUtils.getParam(request, "access_token", null);

			if (StringUtils.isNotBlank(accessToken)) {

				// do validat accestoken

			} else {

				// return errorcode and message
				//return false ;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return super.preHandle(request, response, handler);

	}
}
