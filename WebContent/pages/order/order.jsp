<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 引入格式化标签库 -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>

	<div id="main">
		<c:choose>
			<c:when test="${empty list }">
				<h3>您还没有订单呢，快去购买吧！！！</h3>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>详情</td>
					</tr>
					<!-- 遍历显示list集合中订单 -->
					<c:forEach items="${list }" var="order">
						<!-- 
							fmt:formatDate  :处理日期时间的标签
								value：  要处理的日期时间对象
								type：  显示日期还是时间
										默认值 date
										time 显示时间
										both 显示日期时间
								dateStyle: 显示日期的类型
											full/long/short
								timeStyle: 显示时间的类型
											full/long/short
								
						 -->
						<tr>
							<td>${order.id }</td>
							<td><fmt:formatDate value="${order.orderTime }" type="both" dateStyle="short" timeStyle="short"/></td>
							<td>${order.totalAmount }</td>
							<td>
								<!-- 根据状态值显示不同的文本 -->
								<c:choose>
									<c:when test="${order.state==0 }">未发货</c:when>
									<c:when test="${order.state==1 }"><a href="OrderClientServlet?type=takeGoods&orderId=${order.id }">确认收货</a></c:when>
									<c:when test="${order.state==2 }">交易完成</c:when>
								</c:choose>
							</td>
							<td><a href="#">查看详情</a></td>
						</tr>
					</c:forEach>
					
				</table>
			</c:otherwise>
		</c:choose>
		


	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2018 </span>
	</div>
</body>
</html>