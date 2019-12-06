<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	/* 给删除超链接绑定单击事件，点击时给用户提示 */
	$(function(){
		$(".countInput").change(function(){
			//alert("hehe");
			//向CartServlet发送修改购物项数量的请求：  修改哪个购物项 、 修改的数量时多少
			//this代表触发内容改变监听的表单项
			var $amountTd = $(this).parents("tr").children("td:eq(3)");
			var bookId = this.name;//获取修改了数量的购物项的图书id
			var count = this.value;//获取修改的数量
			
			//向CartServlet.updateCount方法发送修改请求
			//window.location = "CartServlet?type=updateCount&bookId="+bookId+"&count="+count;
			$.ajax({
				"type": "GET",
				"url":"CartServlet",
				"data":{"type":"updateCount" , "bookId":bookId , "count": count},
				"dataType":"json" , //设置响应数据的格式
				"success": function(a){
					//alert(a.amount);
					//将服务器响应的数据展示到页面中
					$amountTd.text(a.amount);//设置单品总金额： 只能获取到修改数量所在行的 单品金额进行修改
					$(".b_price").text(a.totalAmount);//设置购物车总金额
					$(".b_count").text(a.totalCount);//设置购物车的总数量
				}
			});
		});
		
		
		
		$(".delA").click(function(){
			//获取删除a标签所在行的图书标题
			var title = $(this).parents("tr").children("td:first").text();
			if(!confirm("你真的要删除<<"+title+">>吗？")){
				//选择取消删除， 阻止标签的默认行为
				return false;
			}
		});
	});

</script>
</head>
<body>
	<!-- 所有的html标签中由于设置了base标签都使用相对路径 -->
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<!--所有的jsp内容路径都是由服务器解析，推荐使用绝对路径  -->
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty cart.cartItemList }">
				<h3>购物车中还没有数据</h3>
			
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>		
					<c:forEach items="${cart.cartItemList }" var="cartItem">
						<tr>
							<td>${cartItem.book.title }</td>
							<td><input name="${cartItem.book.id }" class="countInput" type="text" value="${cartItem.count }" style="width: 40px;text-align: center;"/></td>
							<td>${cartItem.book.price }</td>
							<td>${cartItem.amount }</td>
							<td><a class="delA" href="CartServlet?type=deleteCartItem&bookId=${cartItem.book.id }">删除</a></td>
						</tr>	
					</c:forEach>
						
					
				</table>
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount }</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalAmount }</span>元</span>
					<span class="cart_span"><a href="CartServlet?type=clear">清空购物车</a></span>
					<span class="cart_span"><a href="OrderClientServlet?type=checkout">去结账</a></span>
				</div>
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