package com.happinesstree.oauth2.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.happinesstree.oauth2.clients.ClientDetailsService;
import com.happinesstree.oauth2.code.AuthorizationCodeServices;
import com.happinesstree.oauth2.exceptions.InvalidGrantException;
import com.happinesstree.oauth2.exceptions.InvalidRequestException;
import com.happinesstree.oauth2.exceptions.OAuth2Exception;
import com.happinesstree.oauth2.exceptions.UnsupportedResponseTypeException;
import com.happinesstree.oauth2.exceptions.UserDeniedAuthorizationException;
import com.happinesstree.oauth2.request.AuthorizationRequest;
import com.happinesstree.oauth2.request.AuthorizationRequestManager;
import com.happinesstree.oauth2.request.DefaultAuthorizationRequest;
import com.happinesstree.oauth2.request.DefaultAuthorizationRequestManager;
import com.happinesstree.oauth2.token.OAuth2AccessToken;
import com.happinesstree.oauth2.utils.DefaultRedirectResolver;
import com.happinesstree.oauth2.utils.OAuth2Utils;
import com.happinesstree.oauth2.utils.RedirectResolver;
import com.happinesstree.oauth2.utils.RequestUtils;

/**
 * 
 * @Title: AuthorizationEndpoint.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * 授权入口 /oauth2/authorize
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 下午3:49:30
 * @author shuhuan@qiyi.com
 */
@Controller
@SessionAttributes("authorizationRequest")
@RequestMapping(value = "/oauth2/authorize")
public class AuthorizationEndpoint extends BaseController {

	// 客户端App管理
	@Resource(name="qiyiClientDetailsService")
	private ClientDetailsService clientDetailsService;

	// 授权码code管理
	@Resource(name="qiyiAuthorizationCodeService")
	private AuthorizationCodeServices authorizationCodeServices;
	
	// 令牌管理
	@Resource(name="qiyiTokenService")
	private QiyiTokenService qiyiTokenService;
	
	// Passport奇艺用户管理
	@Autowired
	private PassportService passportService;

	// 重定向
	private RedirectResolver redirectResolver = new DefaultRedirectResolver();

	// 授权请求管理
	private AuthorizationRequestManager authorizationRequestManager = new DefaultAuthorizationRequestManager();

	/**
	 * 授权请求入口
	 * 
	 * @param responseType
	 * @param display
	 * @param parameters
	 * @return
	 */
	@RequestMapping
	public ModelAndView authorize(HttpServletRequest request,
			Map<String, Object> model,
			@RequestParam(value = "response_type", required = false, defaultValue = "none") String responseType,
			@RequestParam(value = "display", required = false, defaultValue = "none") String display,
			@RequestParam Map<String, String> parameters) throws Exception {
		
		// 验证用户是否登录/ ThreadLocal拦截
		PassportUser passportUser = verify(request);

		Set<String> responseTypes = OAuth2Utils.parseParameterList(responseType);

		/**
		 * 检查授权请求链接，是否标识授权类型： response_type=code 授权码方式 response_type=token
		 * 隐式授权方式
		 * 
		 */
		if (!responseTypes.contains("token") && !responseTypes.contains("code")) {
			throw new UnsupportedResponseTypeException("Unsupported response types: " + responseTypes);
		}

		DefaultAuthorizationRequest authorizationRequest = new DefaultAuthorizationRequest(
				getAuthorizationRequestManager().createAuthorizationRequest(
						parameters));
		// 保存用户信息
		authorizationRequest.setUser(passportUser);

		/**
		 * 验证客户端信息 1、验证clientId是否存在 2、验证redirectUri是否match
		 */
		 getAuthorizationRequestManager().validateParameters(parameters,
				 clientDetailsService.loadClientByClientId(authorizationRequest.getClientId()));

		/**
		 * 这里暂且放在session中，后续处理方案：
		 * 1、存放到cache中，通过cache中查找clientId
		 */
		model.put("authorizationRequest", authorizationRequest);

		return getUserApprovalPageResponse(model, authorizationRequest);

	}
	
	/**
	 * 用户登录并授权
	 * 
	 * @param approvalParameters
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, params = AuthorizationRequest.USER_LOGIN_APPROVAL)
	public View loginAndApprove(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, String> approvalParameters, Map<String, Object> model,
			@RequestParam(value = "client_id", required = false, defaultValue = "none") String clientId,
			@RequestParam(value = "hashcode", required = false, defaultValue = "none") String hashcode) {
		
		// 获取授权请求信息
		DefaultAuthorizationRequest authorizationRequest = (DefaultAuthorizationRequest) model.get("authorizationRequest");
		
		// 用户登录
		String authcookie = approvalParameters.get("authcookie");
		
		// 验证用户登录
		PassportUser passportUser = null;
		
		if( StringUtils.isNotBlank(authcookie) ) {
        	passportUser = passportService.checkAuthCookie(authcookie);
        }
        
        if( null != passportUser ) {
        	// 保存用户信息
    		authorizationRequest.setUser(passportUser);
    		
    		/**
    		 * 这里暂且放在session中，后续处理方案：
    		 * 1、存放到cache中，通过cache中查找clientId
    		 */
    		model.put("authorizationRequest", authorizationRequest);
        	// 5min
        	// RequestUtils.setCookie(request, response, OpConstants.PASSPORT_COOKIE_KEY, passportUser.getAuthCookie(), 300);
        } else {
        	throw new InvalidRequestException("Username Or Password Error.");
        }
		        
        return approveOperation(approvalParameters, model, clientId, hashcode, true);
	}

	/**
	 * 用户授权或者取消
	 * 
	 * @param approvalParameters
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, params = AuthorizationRequest.USER_OAUTH_APPROVAL)
	public View approveOrDeny(@RequestParam Map<String, String> approvalParameters, Map<String, ?> model,
			@RequestParam(value = "client_id", required = false, defaultValue = "none") String clientId,
			@RequestParam(value = "hashcode", required = false, defaultValue = "none") String hashcode) {

		return approveOperation(approvalParameters, model, clientId, hashcode, false);
	}

	/**
	 * 授权操作
	 * 
	 * @param approvalParameters
	 * @param model    
	 * @param clientId
	 * @param hashcode
	 * @param isLogin
	 * @return
	 */
	private View approveOperation(Map<String, String> approvalParameters,
			Map<String, ?> model, String clientId, String hashcode,
			boolean isLogin) {

		// 获取授权请求信息
		AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
		
		// 利用Cache来管理授权请求AuthorizationRequest
		if( null != authorizationRequest ) {
			// 通过cache中查找clientId+hashcode
			// ....
		}

		if (authorizationRequest == null) {
			throw new InvalidRequestException("Cannot approve uninitialized authorization request.");
		}

		Set<String> responseTypes = authorizationRequest.getResponseTypes();

		DefaultAuthorizationRequest incomingRequest = new DefaultAuthorizationRequest(
				authorizationRequest);
		
		incomingRequest.setApprovalParameters(approvalParameters);

		// 检查用户是否授权
		if( isLogin ) { // 登录并授权
			if (!isLoginApproved(incomingRequest)) {
				return new RedirectView(getUnsuccessfulRedirect(incomingRequest,
						new UserDeniedAuthorizationException("User denied access"),
						responseTypes.contains("token")), false);
			}
		} else { // 已经登录
			if (!isApproved(incomingRequest)) {
				return new RedirectView(getUnsuccessfulRedirect(incomingRequest,
						new UserDeniedAuthorizationException("User denied access"),
						responseTypes.contains("token")), false);
			}
		}

		// 如果是隐式授权
		if (responseTypes.contains("token")) {
			// 返回redirect_uri，附带授权码access_token
			return getImplicitGrantResponse(incomingRequest).getView();
		}

		// 返回redirect_uri，附带授权码code
		return getAuthorizationCodeResponse(incomingRequest);
	}

	/**
	 * 生成授权码
	 * 
	 * @param authorizationRequest
	 * @return
	 */
	private View getAuthorizationCodeResponse(
			AuthorizationRequest authorizationRequest) {
		try {
			
			String code = authorizationCodeServices.createAuthorizationCode(authorizationRequest);
			
			return new RedirectView(getSuccessfulRedirect(authorizationRequest, code), false);
		} catch (OAuth2Exception e) {
			return new RedirectView(getUnsuccessfulRedirect(authorizationRequest, e, false), false);
		}
	}

	/**
	 * 隐式授权，生成access_token We can grant a token and return it with implicit
	 * approval
	 * 
	 * @param authorizationRequest
	 * @return
	 */
	private ModelAndView getImplicitGrantResponse(
			AuthorizationRequest authorizationRequest) {
		try {
			OAuth2AccessToken accessToken = qiyiTokenService.createAccessToken(authorizationRequest);

			if (accessToken == null) {
				throw new UnsupportedResponseTypeException("Unsupported response type: token");
			}
			return new ModelAndView(new RedirectView(appendAccessToken(
					authorizationRequest, accessToken), false));
		} catch (OAuth2Exception e) {
			return new ModelAndView(new RedirectView(getUnsuccessfulRedirect(
					authorizationRequest, e, true), false));
		}
	}

	private boolean isApproved(AuthorizationRequest authorizationRequest) {

		String flag = authorizationRequest.getApprovalParameters().get(
				AuthorizationRequest.USER_OAUTH_APPROVAL);
		boolean approved = flag != null && flag.toLowerCase().equals("true");

		return approved
				|| (authorizationRequest.getResponseTypes().contains("token"));

	}
	
	private boolean isLoginApproved(AuthorizationRequest authorizationRequest) {

		String flag = authorizationRequest.getApprovalParameters().get(
				AuthorizationRequest.USER_LOGIN_APPROVAL);
		boolean approved = flag != null && flag.toLowerCase().equals("true");

		return approved
				|| (authorizationRequest.getResponseTypes().contains("token"));

	}

	private String getSuccessfulRedirect(
			AuthorizationRequest authorizationRequest, String authorizationCode) {

		if (authorizationCode == null) {
			throw new IllegalStateException(
					"No authorization code found in the current request scope.");
		}

		String requestedRedirect = authorizationRequest.getRedirectUri();
		String[] fragments = requestedRedirect.split("#");
		String state = authorizationRequest.getState();

		StringBuilder url = new StringBuilder(fragments[0]);
		if (requestedRedirect.indexOf('?') < 0) {
			url.append('?');
		} else {
			url.append('&');
		}
		url.append("code=").append(authorizationCode);

		if (state != null) {
			url.append("&state=").append(state);
		}

		if (fragments.length > 1) {
			url.append("#" + fragments[1]);
		}

		return url.toString();
	}

	private String getUnsuccessfulRedirect(
			AuthorizationRequest authorizationRequest, OAuth2Exception failure,
			boolean fragment) {

		if (authorizationRequest == null
				|| authorizationRequest.getRedirectUri() == null) {
			// we have no redirect for the user. very sad.
			throw new OAuth2Exception(
					"Authorization failure, and no redirect URI.", failure);
		}

		String redirectUri = authorizationRequest.getRedirectUri();

		// extract existing fragments if any
		String[] fragments = redirectUri.split("#");

		StringBuilder url = new StringBuilder(fragment ? redirectUri
				: fragments[0]);

		char separator = fragment ? '#' : '?';
		if (redirectUri.indexOf(separator) < 0) {
			url.append(separator);
		} else {
			url.append('&');
		}
		url.append("error=").append(failure.getOAuth2ErrorCode());
		try {

			url.append("&error_description=").append(
					URLEncoder.encode(failure.getMessage(), "UTF-8"));

			if (authorizationRequest.getState() != null) {
				url.append('&').append("state=")
						.append(authorizationRequest.getState());
			}

			if (failure.getAdditionalInformation() != null) {
				for (Map.Entry<String, String> additionalInfo : failure
						.getAdditionalInformation().entrySet()) {
					url.append('&')
							.append(additionalInfo.getKey())
							.append('=')
							.append(URLEncoder.encode(
									additionalInfo.getValue(), "UTF-8"));
				}
			}

		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}

		if (!fragment && fragments.length > 1) {
			url.append("#" + fragments[1]);
		}

		return url.toString();

	}

	/**
	 * 跳转至授权页面
	 * 
	 * @param model
	 * @param authorizationRequest
	 * @return
	 */
	private ModelAndView getUserApprovalPageResponse(Map<String, Object> model,
			AuthorizationRequest authorizationRequest) {
		// In case of a redirect we might want the request parameters to be
		// included
		model.putAll(authorizationRequest.getAuthorizationParameters());

		return new ModelAndView(OpConstants.OAUTH2_USER_APPROVAL_PAGE, model);
	}

	private String appendAccessToken(AuthorizationRequest authorizationRequest,
			OAuth2AccessToken accessToken) {
		String requestedRedirect = authorizationRequest.getRedirectUri();
		if (accessToken == null) {
			throw new InvalidGrantException(
					"An implicit grant could not be made");
		}
		StringBuilder url = new StringBuilder(requestedRedirect);
		if (requestedRedirect.contains("#")) {
			url.append("&");
		} else {
			url.append("#");
		}
		url.append("access_token=" + accessToken.getValue());
		url.append("&token_type=" + accessToken.getTokenType());
		String state = authorizationRequest.getState();
		if (state != null) {
			url.append("&state=" + state);
		}
		Date expiration = accessToken.getExpiration();
		if (expiration != null) {
			long expires_in = (expiration.getTime() - System
					.currentTimeMillis()) / 1000;
			url.append("&expires_in=" + expires_in);
		}
		Map<String, Object> additionalInformation = accessToken
				.getAdditionalInformation();
		for (String key : additionalInformation.keySet()) {
			Object value = additionalInformation.get(key);
			if (value != null) {
				url.append("&" + key + "=" + value); // implicit call of
														// .toString() here
			}
		}
		// Do not include the refresh token (even if there is one)
		return url.toString();
	}

	public ClientDetailsService getClientDetailsService() {
		return clientDetailsService;
	}

	public void setClientDetailsService(
			ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
	}

	public AuthorizationCodeServices getAuthorizationCodeServices() {
		return authorizationCodeServices;
	}

	public void setAuthorizationCodeServices(
			AuthorizationCodeServices authorizationCodeServices) {
		this.authorizationCodeServices = authorizationCodeServices;
	}

	public RedirectResolver getRedirectResolver() {
		return redirectResolver;
	}

	public void setRedirectResolver(RedirectResolver redirectResolver) {
		this.redirectResolver = redirectResolver;
	}

	public AuthorizationRequestManager getAuthorizationRequestManager() {
		return authorizationRequestManager;
	}

	public void setAuthorizationRequestManager(
			AuthorizationRequestManager authorizationRequestManager) {
		this.authorizationRequestManager = authorizationRequestManager;
	}

	/**
	 * 是否已登录
	 * 
	 * @param cookies
	 * @return
	 * @throws OAuth2LogonException
	 */
	public PassportUser verify(HttpServletRequest request)
			throws OAuth2LogonException {
		PassportUser passportInfo = null;

		String authcookie = RequestUtils.getCookieValue(request, OpConstants.PASSPORT_COOKIE_KEY);
		
		passportInfo = passportService.checkAuthCookie(authcookie);

//		if (passportInfo == null) {// 登录失败
//			throw new PassportLogonException();
//		} else {
//			return passportInfo;
//		}
		
		return passportInfo;
	}
	
}
