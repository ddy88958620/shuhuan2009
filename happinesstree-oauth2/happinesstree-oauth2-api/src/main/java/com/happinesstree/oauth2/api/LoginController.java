package com.happinesstree.oauth2.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.happinesstree.oauth2.common.Constants;
import com.happinesstree.oauth2.dao.domain.User;
import com.happinesstree.oauth2.utils.RequestUtils;

/**
 * 
 * @Title: LoginController.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 * OAuth2奇艺用户登录Controller
 *               <br>
 * @Company: iqiyi.com
 * @Created on 2013-3-16 上午10:29:08
 * @author shuhuan@qiyi.com
 */
@Controller
@RequestMapping("/oauth2/")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到登录页
	 * 根据display选择相应的登录页
	 * 
	 * eg.display=client
	 * 
	 * default	默认的授权页面，适用于web浏览器。
	 * mobile	移动终端的授权页面，适用于支持html5的手机
	 * client	客户端版本授权页面，适用于PC桌面应用。
	 * 
	 * @param model
	 * @param display
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public ModelAndView login(Map<String, Object> model,
			@RequestParam(value = "display", required = false, defaultValue = "none") String display,
			@RequestParam Map<String, String> parameters) throws Exception {

		String loginJSP = Constants.OAUTH2_LOGIN_WEB;
		
		if( StringUtils.isNotBlank(display) ) {
			if( Constants.OAUTH2_MOBILE.equals(display) ) {
				loginJSP = Constants.OAUTH2_LOGIN_MOBILE;
			}
		}
		
		// 获得授权URL
		String authorizeUrl = mapToString(parameters);
		
		return new ModelAndView(loginJSP, "authorize_url", authorizeUrl);
	}
	
	/**
	 * 奇艺用户登录，跳转到授权页面
	 * 
	 * @param request
	 * @param response
	 * @param username     奇艺用户名
	 * @param password     奇艺用户密码
	 * @param authorizeUrl 授权URL
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logon", method = RequestMethod.POST)
	public ModelAndView logon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "j_username", required = true, defaultValue = "none") String username,
			@RequestParam(value = "j_password", required = true, defaultValue = "none") String password,
			@RequestParam(value = "authorize_url", required = true, defaultValue = "none") String authorizeUrl) throws Exception {

		// 获取请求URL
		String requestURL = request.getContextPath() + Constants.OAUTH2_AUTHORIZE + "?" + authorizeUrl;
		
		// 验证用户登录
		User user = null;
        
        if( StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) ) {
        	user = userService.login(username, password);
        }
        
        if( null != user ) {
        	// 5min
        	RequestUtils.setCookie(request, response, Constants.COOKIE_KEY, user.getAuthCookie(), 300);
        }
		
		// 跳转授权请求
		return new ModelAndView(new RedirectView(requestURL));
	}
	
	/**
	 * Map转换String
	 * 
	 * @param map
	 *            :需要转换的Map
	 * @return String转换后的字符串
	 */
	private static String mapToString(Map<?, ?> map) {
		
		String SEP1 = "=";  
		String SEP2 = "&";  

		StringBuffer sb = new StringBuffer();
		// 遍历map
		for (Object obj : map.keySet()) {
			if (obj == null) {
				continue;
			}
			Object key = obj;
			Object value = map.get(key);
			if (value instanceof String) {
				sb.append(key.toString() + SEP1 + value.toString());
				sb.append(SEP2);
			}
		}
		
		String str = sb.toString();
		
		str = StringUtils.substring(str, 0, StringUtils.lastIndexOf(str, "&"));
		
		return str;
	} 

}// end class
