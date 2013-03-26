<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String urlHeader = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>奇艺开放平台测试DEMO</title>
  <link type="text/css" rel="stylesheet" href="<c:url value="/style.css"/>"/>
  <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  		$("#loginbtn").click(function(){
  			 document.location.href = "<%=urlHeader%>/oauth/authorize?client_id=0E6E5A4747B5BF9E&response_type=token&scope=read&redirect_uri=http://www.anywhere";
  		});
  	});
  </script>
</head>
<body>

  <h1>奇艺开放平台</h1>

  <div id="content">
	
	<p>本期只开放支持Authorization Code授权方式，测试客户端(Consumer)信息如下：</p>
	
	<table border="1" cellspacing="0">
		<tr>
			<th>AppId</th>
			<th>AppSecret</th>
			<th>客户端名称</th>
			<th>客户端Logo</th>
		</tr>
		<tr>
			<td>0E6E5A4747B5BF9E</td>
			<td>0E6E5A4747B5BF9E</td>
			<td>test client</td>
			<td></td>
		</tr>
	</table>
	
	<h2>第三方登录测试</h2>
	
	<div class="btn_box">
        <input type="button" id="loginbtn" value="登录并授权">
    </div>
    
    <h2><a href="http://wiki.qiyi.domain/pages/viewpage.action?pageId=8126621">视频云API接口</a></h2>
	
  </div>

  <div id="footer">iqiyi OAuth2.0</div>

</body>
</html>
