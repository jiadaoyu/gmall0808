<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	
	<!--  转发到BookClinetServlet查询分页数据-->
	<jsp:forward page="/BookClientServlet?type=findPage&pageNumber=1"></jsp:forward>
	
	
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>