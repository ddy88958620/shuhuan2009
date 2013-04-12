package com.happinesstree.oauth2.api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.happinesstree.oauth2.api.exception.OAuth2LogonException;
import com.happinesstree.oauth2.common.Constants;
import com.happinesstree.oauth2.exceptions.OAuth2Exception;
import com.happinesstree.oauth2.service.AccessTokenService;

/**
 * 
 * @Title: BaseController.java
 * @Copyright: Copyright (c) 2013
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-10 下午2:01:50
 * @author shuhuan2009@gmail.com
 */
public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	ObjectMapper jacksonObjectMapper;
	
	@Autowired
	public AccessTokenService oauthAccessTokenService;
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleException(HttpServletRequest request, Exception ex, HttpServletResponse response) {
		logger.error(ex.getMessage(), ex);

		return createResponse(request, Constants.ERROR_CODE, "操作失败", null);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handHttpMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
		logger.error("[没有请求方法异常]" + ex.getMessage(), ex);
		
		return createResponse(request, Constants.ERROR_CODE, "[没有请求方法异常]", null);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
		logger.error("[参数异常]" + ex.getMessage(), ex);

		return createResponse(request, Constants.ERROR_CODE, "[参数异常]", null);
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleSQLException(HttpServletRequest request, SQLException ex) {
		logger.error("[数据访问异常]" + ex.getMessage(), ex);

		return createResponse(request, Constants.ERROR_CODE, "[数据访问异常]", null);
	}

	/**
	 * 拦截奇艺用户是否已登录
	 * 
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(OAuth2LogonException.class)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView handleOAuth2LogonException(Exception ex) throws Exception {
		logger.info("[跳转登录页]");
		
		return new ModelAndView(Constants.OAUTH2_LOGIN_PAGE);
	}
	
	/**
	 * 捕获OAuth2Exception异常
	 * 
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(OAuth2Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleOAuth2Exception(HttpServletRequest request, OAuth2Exception ex) {
		logger.error("[OAuth2异常]" + ex.getMessage(), ex);

		return createResponse(request, ex.getOAuth2ErrorCode(), "[OAuth2异常]", null);
	}
	
	/**
	 * 返回响应
	 * 
	 * @param code
	 * @param message
	 * @param request
	 * @return
	 */
	public String createResponse(HttpServletRequest request,
			String code, String message, Object data) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("code", code);
		
		if (message != null) {
			model.put("msg", message);
		}

		if (data != null) {
			model.put("data", data);
		}
		
		JSONObject jsonObject = JSONObject.fromObject( model );
		
		return jsonObject.toString();
	}

}
