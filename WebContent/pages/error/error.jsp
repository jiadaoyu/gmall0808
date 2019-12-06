<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误页面</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<h3 style="color:red;text-align: center; margin-top: 120px;">服务器走丢了，请联系管理员解决！！！！</h3>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>