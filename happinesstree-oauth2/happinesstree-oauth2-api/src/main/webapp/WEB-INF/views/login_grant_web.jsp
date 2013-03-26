<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="shortcut icon" href="http://www.qiyipic.com/common/images/logo.ico" type="image/icon" />
<script type="text/javascript">
    var __LT__ = window.__LT__ || {
        j:{},
        c:{},
        f:{},
        l:{},
        i:{},
        s:{}
    };
    __LT__.c['global'] = new Date();
</script>
<script type="text/javascript">
    __LT__.c['global'] = new Date() - __LT__.c['global'];
    var info = info || {};
    __LT__.j['config'] = new Date();
</script>
<script type="text/javascript" src="http://static.qiyi.com/js/qiyi/config.js"></script>
<script type="text/javascript">
    __LT__.j['config'] = new Date() - __LT__.j['config'];
</script>

<script type="text/javascript" src="http://static.qiyi.com/js/lib/sea1.2.js"></script>
<script type="text/javascript">
Q.projectName = 'qiyiV2';
</script>

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
            <p>没有爱奇艺账号？ <a href="http://passport.qiyi.com/register/reg.php?url=" class="c5189">立即注册</a></p>
            <p style="display:none">crystal西西<span class="line">|</span><a href="javascript:void(0);" class="c5189">换个账号</a></p>
        </div>
        <!--right_end--> 
    </div>
</div>
<div class="mainPassport" data-widget-vocp="vocpAuth">
    <div class="contentPassport">
        <div class="mesGrant clearfix"> 
            <!--授权checkbox状态 选中：checkbox_on  未选：checkbox_off  不可选：checkbox_no -->
            <dl class="mesGrant_mes">
                <dt>授权<span class="c5189"><c:out value="${client.clientId}"/></span>进行如下操作：</dt>
                <dd><span class="checkbox_on"></span>获取您的个人信息</dd>
                <dd><span class="checkbox_off"></span>获取您的评论</dd>
            </dl>
            <div class="mesGrant_logo"> <a href="http://www.iqiyi.com/" class="grant_logo"><img src="http://tp2.sinaimg.cn/1731986465/50/5650413312/1" width="64" height="64"></a> </div>
        </div>
        <div class="passportLogin clearfix">
            <p class="h30 pl50"><span j-msg="j-mail" data-vocp-elem="errorTip"></span></p>
            <p><span class="w50">帐号：</span>
                <input type="text" data-vocp-elem="txtMail" autocomplete="off" id="j-mail" onFocus="this.className='text_out text_on'" onBlur="this.className='text_out'" class="text_out">
            </p>
            <p><span class="w50">密码：</span>
                <input type="password" data-vocp-elem="txtPwd" id="j-pwd" maxlength="16" onFocus="this.className='text_out text_on'" onBlur="this.className='text_out'" class="text_out">
            </p>
            <p class="btnBox pl50">
            	<input name="login" value="" type="button" data-vocp-elem="btnGetAuth" class="btnLogin" onmouseout="this.className='btnLogin';" onmouseover="this.className='btnLogin_hover';" onclick="this.className='btnLogin_active';"/> 
            	<a href="javascript:void(0);" data-vocp-elem="btnCancel">取消</a>
	        </p>
             <!--按钮不可用状态：<a class="btn_common btn_no" href="javascript:void(0);"><span class="btn_left"></span><span>登录授权中</span></a>-->
        </div>
    </div>
    <div class="footerTips">
        <p>提示：为保证您的账号安全，请核实本页URL地址应该以http://api.iqiyi.com 开头</p>
    </div>
</div>
</body>
<script>
	Q.projectName = 'qiyiV2';
	Q.ready(function (lib) {
		Q.load('vcopSdk');
	});
</script>
</html>