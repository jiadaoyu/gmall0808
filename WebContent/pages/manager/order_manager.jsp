<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty list }">
				<h3>生意不太行，还没有订单！！！</h3>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>详情</td>
						<td>发货</td>
						
					</tr>		
					<c:forEach items="${list }" var="order">
						<tr>
							<td>${order.id }</td>
							<td>${order.orderTime }</td>
							<td>${order.totalAmount }</td>
							<td><a href="#">查看详情</a></td>
							<td>
								<c:choose>
									<c:when test="${order.state==0 }"><a href="OrderManagerServlet?type=sendGoods&orderId=${order.id }">点击发货</a></c:when>
									<c:when test="${order.state==1 }">等待用户签收</c:when>
									<c:when test="${order.state==2 }">交易完成</c:when>
								</c:choose>
							
							</td>
						</tr>	
					</c:forEach>
						
				</table>
			</c:otherwise>
		</c:choose>
		
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>