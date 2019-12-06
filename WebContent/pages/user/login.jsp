<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<!-- 判断打开login页面是第一次登陆打开还是登陆失败转发过来的请求
									如果request域中能够获取到errorMsg证明是登录失败转发过来的请求
									如果没有获取到证明是第一次打开登录页面
								 -->
								 <%-- <%
								 	//String request.getParameter("");//获取用户提交的请求参数
								 	String errorMsg = (String)request.getAttribute("errorMsg");//获取请求域中的数据
								 	if(errorMsg == null){
								 		//第一次打开  给errorMsg设置默认值
								 		errorMsg = "请输入用户名和密码";
								 	}
								 	
								 %>
								<span class="errorMsg"><%=errorMsg %></span> --%>
								<span class="errorMsg">${empty errorMsg?"请输入用户名和密码":errorMsg }</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<!-- 使用隐藏域携带type参数 -->
									<input type="hidden" name="type" value="login"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>