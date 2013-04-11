package com.happinesstree.oauth2.common;

/**
 * 
 * @Title: Constants.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-4-7 下午5:07:03
 * @author shuhuan@happinesstree.com
 */
public interface Constants {
	
	/* 成功的返回code */
	public static final String SUCCESS_CODE = "A00000";

	/* 失败的返回code */
	public static final String ERROR_CODE = "A00001";

	/*
	 * OAuth2配置项
	 */
	public static String COOKIE_KEY                = "SH0001";        // cookie键
	public static String OAUTH2_AUTHORIZE          = "/oauth2/authorize";
	public static String OAUTH2_TOKEN              = "/oauth2/token";
	public static String OAUTH2_USER_APPROVAL_PAGE = "forward:/oauth2/confirm_access";
	public static String OAUTH2_ERROR_PAGE         = "forward:/oauth2/error";
	public static String OAUTH2_LOGIN_PAGE         = "forward:/oauth2/login";
	public static String OAUTH2_MOBILE             = "mobile";              // 移动终端
	public static String OAUTH2_LOGIN_MOBILE       = "login_grant_mobile";  // 移动终端的授权页面，适用于支持html5的手机
	public static String OAUTH2_ACCESS_WEB         = "grant_web";           // 默认的授权页面，适用于web浏览器
	public static String OAUTH2_LOGIN_WEB          = "login_grant_web";     // 默认的授权页面，适用于web浏览器
	
	/*
	 * OAuth2错误代码
	 * 与新浪微博错误码一致
	 */
	public static String OAUTH2_REDIRECT_URI_MISMATCH     = "21322";  // 重定向地址不匹配
	public static String OAUTH2_INVALID_REQUEST           = "21323";  // 请求不合法
	public static String OAUTH2_INVALID_CLIENT            = "21324";  // client_id或client_secret参数无效
	public static String OAUTH2_INVALID_GRANT             = "21325";  // 提供的AuthorizationCode是无效的、过期的或已撤销的
	public static String OAUTH2_UNAUTHORIZED_CLIENT       = "21326";  // 客户端没有权限
	public static String OAUTH2_EXPIRED_TOKEN             = "21327";  // token过期
	public static String OAUTH2_UNSPPORTED_GRANT_TYPE     = "21328";  // 不支持的 GrantType
	public static String OAUTH2_UNSUPPORTED_RESPONSE_TYPE = "21329";  // 不支持的 ResponseType
	public static String OAUTH2_ACCESS_DENIED             = "21330";  // 用户或授权服务器拒绝授予数据访问权限
	public static String OAUTH2_TEMPORARILY_UNAVAILABLE   = "21331";  // 服务暂时无法访问
	
}
