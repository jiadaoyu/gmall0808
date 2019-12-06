<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<!-- 使用js给删除a绑定单击事件 -->
<script type="text/javascript">
	$(function(){
		$(".delA").click(function(){
			//给用户提示
			//获取删除a标签所在行的图书标题
			var title = $(this).parents("tr").children("td:first").text();
			var flag = confirm("你真的要删除《"+title+"》吗？");
			if(!flag){
				//取消删除， 取消a的默认行为
				return false;
			}
			
		});
		
	});
	
</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<!-- 
			1、先判断 是否有图书集合
			2、遍历显示域中的list集合
			3、修改manager_header.jsp页面中的图书管理超链接，点击时应该提交请求给BookManagerServlet先查询数据然后在转发到book_manager.jsp页面显示
		 -->
		<c:choose>
			<c:when test="${empty page.data }">
				<!-- 图书集合为null ， 不遍历 -->
				<h2 style="color:red;text-align: center;margin-top: 120px;">生意太好了，图书卖空了，赶紧添加吧！<a href="pages/manager/book_add.jsp">添加图书</a></h2>
			</c:when>
			<c:otherwise>
				<!-- 图书集合不为null  遍历显示 -->
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>
					<c:forEach items="${page.data }" var="book">
						<tr>
							<td>${book.title }</td>
							<td>${book.price }</td>
							<td>${book.author }</td>
							<td>${book.sales }</td>
							<td>${book.stock }</td>
							<!-- 修改超链接，点击提交请求给BookManagerServlet.findBook,并需要提交bookId -->
							<td><a href="BookManagerServlet?type=findBook&bookId=${book.id }">修改</a></td>
							<!-- 修改删除超链接，点击时提交请求给BookManagerServlet.deleteBook方法处理 -->
							<td><a class="delA" href="BookManagerServlet?type=deleteBook&bookId=${book.id }">删除</a></td>
						</tr>	
					</c:forEach>		
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="pages/manager/book_add.jsp">添加图书</a></td>
					</tr>	
				</table>
			
			</c:otherwise>
		</c:choose>
	</div>
		<!-- 将分页导航栏提取为一个nav.jsp页面，再通过include指令引入 -->
		<%@ include file="/WEB-INF/include/nav.jsp" %>
	
	
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>