<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//给添加到购物车的a标签绑定单击事件，点击时 发送ajax请求
		//默认行为：  提交按钮和a标签都有默认行为
		$(".addA").click(function(){
			var id = this.id;//要添加的图书id
			$.ajax({
				type:"GET",//get请求  如果地址不变   参数不变，浏览器会认为是重复的请求
				url:"CartServlet",//请求地址
				//type=addBook&bookId=
				data: {type:"addBook" , bookId:id}, //请求参数
				dataType:"json",
				success:function(a){
					//alert(a.title+","+a.totalCount);
					//将响应结果通过dom操作设置到页面中
					$("#countSpan").text("您的购物车中有"+a.totalCount +"件商品");
					$("#titleDiv").html("您刚刚将<span style='color: red'>"+a.title+"</span>加入到了购物车中");
					
				}//提供相应成功的处理函数
			});
			//阻止标签的默认行为发生
			return false;
			
		});
		
		
		
		$("#queryBtn").click(function(){
			//获取价格区间并提交请求给BookClientServlet.findPageByprice方法查询价格区间的分页数据
			var min = $("input[name='min']").val();
			var max = $("input[name='max']").val();
			//提交请求
			window.location = "BookClientServlet?type=findPageByPrice&min="+min+"&max="+max;
		});
	});

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<!-- 表单数据回显：获取请求参数中的数据 -->
				价格：<input type="text" name="min" value="${param.min }"> 元 - <input type="text" name="max" value="${param.max }"> 元 <button id="queryBtn">查询</button>
			</div>
			<c:choose>
				<c:when test="${empty cart.map }">
					<!-- 购物车中没有数据，显示没有数据的文案 -->
					<div style="text-align: center" >
						<span id="countSpan">您的购物车中还没有商品</span>
						<div id="titleDiv">
							<br/>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<!-- 购物车有数据的文案 -->
					<div style="text-align: center">
						<span id="countSpan">您的购物车中有${cart.totalCount }件商品</span>
						<div id="titleDiv">
							您刚刚将<span style="color: red">${title }</span>加入到了购物车中
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			
			
			
			<!-- 判断查询的分页对象集合是否有数据 -->
			<c:choose>
				<c:when test="${empty page.data }">
					<h3 style="color:red; text-align: center;margin-top: 100px;">你来晚了一步，图书卖空了....</h3>
				</c:when>
				<c:otherwise>
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<!-- 由于图片存储时是绝对路径，需要在前面添加项目名 -->
								<img class="book_img" alt="" src="${pageContext.request.contextPath }${book.imgPath }" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span>
									<span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span>
									<span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span>
									<span class="sp2">￥${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span>
									<span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span>
									<span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<!-- 使用a标签的id属性  携带 被点击a标签对应的图书的id -->
									<a class="addA" id="${book.id }" href="CartServlet?type=addBook&bookId=${book.id }">加入购物车</a>
								</div>
							</div>
						</div>
					</c:forEach>
				
				</c:otherwise>
			</c:choose>
			
		</div>
		
		<!-- 引入分页导航栏 
				使用分页导航栏必须保证page对象的所有属性值都有
		-->
		<%@ include file="/WEB-INF/include/nav.jsp" %>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>