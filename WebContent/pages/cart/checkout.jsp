<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">结算</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为 ${sessionScope.orderId }</h1>
		<!-- 使用完id后销毁 -->
		<c:remove var="orderId" scope="session"/>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>