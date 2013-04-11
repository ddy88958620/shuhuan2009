package com.happinesstree.oauth2.api;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.happinesstree.oauth2.clients.ClientDetails;
import com.happinesstree.oauth2.clients.ClientDetailsService;
import com.happinesstree.oauth2.common.Constants;
import com.happinesstree.oauth2.dao.domain.User;
import com.happinesstree.oauth2.request.AuthorizationRequest;

/**
 * 
 * @Title: AccessConfirmationController.java
 * @Copyright: Copyright (c) 2012
 * @Description: <br>
 *               <br>
 * @Company: happinesstree.com
 * @Created on 2013-3-16 下午3:49:08
 * @author shuhuan2009@gmail.com
 */
@Controller
@SessionAttributes("authorizationRequest")
public class AccessConfirmationController {

	@Resource(name="qiyiClientDetailsService")
	private ClientDetailsService clientDetailsService;
	
	@RequestMapping("/oauth2/confirm_access")
	public ModelAndView getAccessConfirmation(Map<String, Object> model) throws Exception {
		AuthorizationRequest clientAuth = (AuthorizationRequest) model.remove("authorizationRequest");
		
		// 获得App信息
		ClientDetails client = clientDetailsService.loadClientByClientId(clientAuth.getClientId());
		
		String uname = "";
		String icon  = "";
		String accessConfirmationJSP = Constants.OAUTH2_LOGIN_WEB;
		User user = (User) clientAuth.getUser();
		if( null != user ) {
			uname = user.getUserNick();
			icon  = user.getIcon();
			accessConfirmationJSP = Constants.OAUTH2_ACCESS_WEB;
		}
		
		if( clientAuth.getAuthorizationParameters().containsKey("display") ) {
			String display = clientAuth.getAuthorizationParameters().get("display");
			if(Constants.OAUTH2_MOBILE.equals(display) ) {
				accessConfirmationJSP = Constants.OAUTH2_LOGIN_MOBILE; // 手机端均跳到登录并授权h5页面 
			}
		}
		
		model.put("uname", uname);
		model.put("icon", icon);
		model.put("client", client);
		
		return new ModelAndView(accessConfirmationJSP, model);
	}
	
}
