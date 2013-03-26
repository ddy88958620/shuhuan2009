package com.happinesstree.oauth2.common;

public interface Constants {

	/* 成功的返回code */
	public static final String SUCCESS_CODE = "A00000";

	/* 失败的返回code */
	public static final String ERROR_CODE = "Q00001";
	
	/* JSONP跨域调用--标识 */
    public static final String JSONP = "cb";
    
    /* 不合法的参数--标识 */
	public static final String CODE_ILLEGAL_ARGUMENT = "A00001";

	/* 系统出错--标识 */
	public static final String CODE_SYS_ERROR = "A00002";
	
	/* 数据库出错--标识 */
	public static final String CODE_DATABASE_ERROR = "A00003";

	/* API Invalid access token的返回code */
	public static final String CODE_INVALID_ACCESS_TOKEN = "A00004";

	/* API Access token expired的返回code */
	public static final String CODE_EXPIRED_ACCESS_TOKEN = "A00005";
	
	/* App不存在--标识 */
	public static final String CODE_APP_NOT_EXIST = "A00006";
	
	/* try to edit a audting app */
	public static final String CODE_APP_ALREADY_AUDITING = "A00007";
	
	/* app没有权限 */
	public static final String CODE_APP_NO_PERMISSION = "A00008";
	
	/* 用户未授权应用 */
	public static final String CODE_USER_NOT_AUTH = "A00009";
	
	/* 应用不合法 */
	public static final String CODE_APP_INVALID = "A00010"; 
	
	/* 资源不合法 */
	public static final String CODE_RES_INVALID = "A00011"; 
	
	public static final String CODE_USER_NOT_LOGIN="A00012";

	// pageSize默认每页20个
	public static final int PAGESIZE = 20;
	// page默认第一页
	public static final int PAGE = 1;

	public static final String CODE_APP_STATUS_AUDIT_WAIT    = "A00012";
	public static final String CODE_APP_STATUS_AUDIT_PASS    = "A00013";
	public static final String CODE_APP_STATUS_AUDIT_FAIL    = "A00014";
	public static final String CODE_APP_STATUS_AUDIT_FREEZED = "A00014";
	public static final String CODE_APP_STATUS_DELETED       = "A00016";
	public static final String CODE_APP_STATUS_REEDIT        = "A00017";
	public static final String CODE_APP_QUOTA_OVERFLOW       = "A00018";
	
	public static final String CODE_VIDEO_STATUS_PROCESSING = "A00019";
	public static final String CODE_VIDEO_STATUS_PROCESS_FAIL = "A00020";
	public static final String CODE_VIDEO_STATUS_NOT_EXIST = "A00021";
	
	public static final int APP_IS_AUDITING = 1;
	public static final int APP_NO_AUDITING = 2;

//	public static final String VIDEO_COUNT  = "video_count";
//	public static final String VIDEO_LIST   = "video_list";
//	public static final String VIDEO_DELETE = "video_delete";
	
	public static final String  PASSPORT_AUTHCOOKIE_NAME="P00001";
	
	public static final String RESOURCE_NAME_VIDEO_UPLOAD="videoUpload";
	
	public static final String RESOURCE_NAME_USER_PROFILE="userProfile";
	
	/*
	 * OAuth2配置项
	 */
	public static String PASSPORT_COOKIE_KEY  = "P00001";        // cookie键
	public static String OAUTH2_AUTHORIZE     = "/oauth2/authorize";
	public static String OAUTH2_TOKEN         = "/oauth2/token";
	public static String OAUTH2_USER_APPROVAL_PAGE = "forward:/oauth2/confirm_access";
	public static String OAUTH2_ERROR_PAGE         = "forward:/oauth2/error";
	public static String OAUTH2_LOGIN_PAGE         = "forward:/oauth2/login";
	public static String OAUTH2_MOBILE         = "mobile";              // 移动终端
	public static String OAUTH2_LOGIN_MOBILE   = "login_grant_mobile";  // 移动终端的授权页面，适用于支持html5的手机
	public static String OAUTH2_ACCESS_WEB     = "grant_web";           // 默认的授权页面，适用于web浏览器
	public static String OAUTH2_LOGIN_WEB      = "login_grant_web";     // 默认的授权页面，适用于web浏览器
	
	/*
	 * OAuth2错误代码
	 */
	public static String OAUTH2_REDIRECT_URI_MISMATCH     = "A21322";  // 重定向地址不匹配
	public static String OAUTH2_INVALID_REQUEST           = "A21323";  // 请求不合法
	public static String OAUTH2_INVALID_CLIENT            = "A21324";  // client_id或client_secret参数无效
	public static String OAUTH2_INVALID_GRANT             = "A21325";  // 提供的AuthorizationCode是无效的、过期的或已撤销的
	public static String OAUTH2_UNAUTHORIZED_CLIENT       = "A21326";  // 客户端没有权限
	public static String OAUTH2_EXPIRED_TOKEN             = "A21327";  // token过期
	public static String OAUTH2_UNSPPORTED_GRANT_TYPE     = "A21328";  // 不支持的 GrantType
	public static String OAUTH2_UNSUPPORTED_RESPONSE_TYPE = "A21329";  // 不支持的 ResponseType
	public static String OAUTH2_ACCESS_DENIED             = "A21330";  // 用户或授权服务器拒绝授予数据访问权限
	public static String OAUTH2_TEMPORARILY_UNAVAILABLE   = "A21331";  // 服务暂时无法访问
	
	/*
	 * api_user_app_upload table. field :file_status
	 */
	
	public static final String FILE_DELETE_STATUS     = "2";  // 表api_user_app_upload逻辑删除，字段file_status
	
	public static final String QIPA_FILE_STATUS_API = "http://cache.video.qiyi.com/mt/";
	
	public static final String STATUS_VIDEO_NOT_EXIST = "A00019" ;	//视频不存在 
	public static final String STATUS_VIDEO_UPLOADING = "A00020" ;//视频正在处理中
	public static final String STATUS_VIDEO_PROCESS_FAILED = "A00021" ;	//视频处理失败 
	public static final String STATUS_VIDEO_FINSIHED = "A00022" ;//视频处理完成
	
	/*
	 * 应用是否需要用户授权
	 * */
	public static final int APP_USER_AUTH_REQUIRED=2;
	public static final int APP_USER_AUTH_NOT_REQUIRED=1;
	
	/*
	 * 资源是否需应用申请
	 * */
	public static final int RESOURCE_AUDIT_REQUIRED=1;
	public static final int RESOURCE_AUDIT_NOT_REQUIRED=2;
}
