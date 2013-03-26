<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OAuth2 For Web</title>
<link rel="shortcut icon" href="http://www.qiyipic.com/common/images/logo.ico" type="image/icon" />
<link rel="stylesheet" type="text/css" href="http://static.qiyi.com/css/common/passport_css/passport.css" />
</head>
<body>
<div class="navTopNew">
    <div class="s_main"> 
        <!--left_logo-->
        <div class="topLogo"> <a href="http://www.iqiyi.com/"><img src="http://www.qiyipic.com/common/fix/public_images/logo108x35.png" title="爱奇艺 iQIYI.COM" alt="爱奇艺 iQIYI.COM"></a> </div>
        <!--left_logo_end--> 
        <!--right_login-->
        <div class="topNavRt">
            <p style="display:none">没有爱奇艺账号？ <a href="javascript:void(0);" class="c5189">立即注册</a></p>
            <p><c:out value="${uname}"/><span class="line">|</span><a href="javascript:void(0);" class="c5189">换个账号</a></p>
        </div>
        <!--right_end--> 
    </div>
</div>
<div class="mainPassport">
    <div class="contentPassport">
        <div class="mesGrant clearfix"> 
            <!--授权checkbox状态 选中：checkbox_on  未选：checkbox_off  不可选：checkbox_no -->
            <dl class="mesGrant_mes">
                <dt>授权<span class="c5189"><c:out value="${client.clientId}"/></span>进行如下操作：</dt>
                <dd><span class="checkbox_on"></span>获取您的个人信息</dd>
                <dd><span class="checkbox_off"></span>获取您的评论</dd>
            </dl>
            <div class="mesGrant_logo"> <a href="javascript:void(0);" class="grant_logo"><img src="http://tp2.sinaimg.cn/1731986465/50/5650413312/1" width="64" height="64"></a> </div>
        </div>
        <div class="actionGrant clearfix">
            <p class="h30">检测到您已经登录爱奇艺</p>
            <div class="userGet">
                <p><a href="javascript:void(0);" class="user_img"><img src="<c:out value="${icon}"/>" width="50" height="50"></a> </p>
                <p><span class="user_name"><c:out value="${uname}"/></span></p>
            </div>
            
            <form method="post" action="<%=request.getContextPath()%>/oauth2/authorize" name="confirmationForm" id="confirmationForm">
				<div class="btnBox">
                    <input type="hidden" value="true" name="user_oauth_approval">
                    <input type="submit" id="" value="" onclick="this.className='btnGrant_active';" onmouseover="this.className='btnGrant_hover';" onmouseout="this.className='btnGrant';" class="btnGrant" name="authorize">
	            	<!--提交按钮不可点击状态class为 btnGrant_no -->
		            <a href="javascript:void(0);">取消</a>
	            </div>
            </form>
        </div>
    </div>
    <div class="footerTips">
        <p>提示：为保证您的账号安全，请核实本页URL地址应该以http://api.iqiyi.com 开头</p>
    </div>
</div>
</body>
</html>
