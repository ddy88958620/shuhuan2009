<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" charset="utf-8"/>
<link rel="stylesheet" type="text/css" href="http://static.qiyi.com/css/common/passport_css/h5.css">
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
<title>passport_h5</title>
</head>
<body>
<!--top-->
<div class="navTopNew">
  <!--left_logo-->
  <div class="topLogo"> <img src="http://www.qiyipic.com/common/fix/public_images/logo108x35.png" title="爱奇艺 iQIYI.COM" alt="爱奇艺 iQIYI.COM"> </div>
  <!--left_logo_end-->
  <!--right_login-->
  <div class="topNavRt">
    <p class="c5189">登录及授权</p>
  </div>
  <!--right_end-->
</div>
<!--end-->
<!--wrap-->
<div class="wrap" data-widget-vocp="vocpAuth">
  <div class="h30"> <span j-msg="j-mail" data-vocp-elem="errorTip"></span> </div>
  <div class="margin_b20">
    <input type="text" data-vocp-elem="txtMail" class="text0" onBlur="this.className='text0'" onFocus="this.className='text0 text_on'" id="j-mail" autocomplete="off">
  </div>
  <div class="margin_b20">
    <input type="password" data-vocp-elem="txtPwd" class="text0" onBlur="this.className='text0'" onFocus="this.className='text0 text_on'" id="j-pwd" autocomplete="off">
  </div>
  <div class="sq_2013">
    <p>授权<span class="c5189"><c:out value="${client.clientId}"/></span>进行以下操作：</p>
  </div>
  <div class="sqlist_2013 margin_b20">
    <ul>
      <li class="first_online"> <span class="checkbox_on"></span>获取您的个人信息 </li>
      <li> <span class="checkbox_off"></span>获取您的评论 </li>
      <li> <span class="checkbox_no"></span>获取您的评论 </li>
    </ul>
  </div>
  <div class="login_2013"> <a href="javascript:void(0);" class="btn_new"  data-vocp-elem="btnGetAuth" >登录并授权</a> </div>
</div>
<!--end-->
</body>
<script>
	Q.projectName = 'qiyiV2';
	Q.ready(function (lib) {
		Q.load('vcopSdk');
	});
</script>
</html>
