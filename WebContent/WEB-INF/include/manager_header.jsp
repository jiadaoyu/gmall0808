<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 相对路径+base标签 -->
<div>
	<!-- 所有的访问BaseServlet子类的请求必须提交type=方法名参数 -->
	<a href="BookManagerServlet?type=findPage&pageNumber=1">图书管理</a>
	<a href="OrderManagerServlet?type=findAllOrders">订单管理</a>
	<a href="index.jsp">返回商城</a>
</div>