<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
		</div>
		
		<div id="main">
			<form action="BookManagerServlet" method="post">
				<!-- 修改图书：需要使用图书id确定要修改的图书 -->
				<input type="hidden" name="imgPath" value="${book.imgPath }"/>
				<input type="hidden" name="id" value="${book.id }"/>
				<input type="hidden" name="type" value="updateBook"/>
				<!-- 通过隐藏域携带修改之前的页面地址 -->
				<input type="hidden" name="ref" value="${ref }"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value="${book.title }"/></td>
						<td><input name="price" type="text" value="${book.price }"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author }"/></td>
						<td><input name="sales" type="text" value="${book.sales }"/></td>
						<td><input name="stock" type="text" value="${book.stock }"/></td>
						<td><input  type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>