<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<!-- 通过include静态包含base.jsp -->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
<script type="text/javascript">
	//文档加载结束
	$(function(){
		//给用户名输入框绑定内容改变的监听，内容改变时 通过ajax检查用户名是否可用
		$("input[name='username']").change(function(){
			//alert(this.value);
			$.ajax({
				type:"GET",
				url:"UserServlet",
				data:{"type":"checkUsername" , "username": this.value },
				success:function(a){
					if(a==="0"){
						//用户名可用
						$(".errorMsg").text("恭喜你，用户名可用");
						$("#sub_btn").attr("disabled" , false);
						$("#sub_btn").css("background-color", "green");
					}else{
						//用户名不可用
						$(".errorMsg").text("用户名已存在");
						//设置注册按钮  禁用
						$("#sub_btn").attr("disabled" , true);
						$("#sub_btn").css("background-color", "gray");
					}
				}
			});
		});
		
		
		var  i = 0;
		$("#codeImg").click(function(){
			//验证码图片呗点击时  修改img的src属性值 为新图片的地址
			/*
				由于火狐和IE浏览器缓存比较严重，get请求如果请求url地址和参数拼接后一模一样 浏览器会认为是重复提交的请求
				不会向服务器发起新的请求，就使用缓存
				解决： 欺骗浏览器
					修改src属性值时，让url地址每次都和之前的不一样，在地址后拼接一个变化的参数
				
			*/
			this.src = "code.jpg?t="+(i++);
			
		});
		
		
		//给注册的提交按钮绑定单击事件，提交数据前先简单验证是否符合规则
		$("#sub_btn").click(function(){
			//获取用户的注册数据进行验证
			var username = $("[name='username']").val();
			var password = $("[name='password']").val();
			var repwd = $("[name='repwd']").val();
			var email = $("[name='email']").val();
			//通过正则验证
			//给每个需要验证规则的字符串创建一个正则对象，调用正则对象的test方法可以验证字符串，如果匹配成功返回true，验证失败返回false
			var unameReg = /^[a-zA-Z0-9_-]{3,16}$/;
			if(!unameReg.test(username)){
				//用户名不满足要求，取消提交
				alert("请输入正确的用户名....");
				return false;
			}
			//创建密码的正则对象验证密码
			var pwdReg = /^[a-zA-Z0-9_-]{6,18}$/;
			if(!pwdReg.test(password)){
				alert("密码格式错误...");
				return false;
			}
			//验证重复密码
			if(password!=repwd){
				alert("两次密码不一致..");
				return false;
			}
			//验证邮箱
			var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("请输入正确的邮箱地址...");
				return false;
			}
		});
		
	});
</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="../img/logo.png" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<!-- 判断如果没有错误消息显示空字符串 -->
								<%-- <%
									String errorMsg = (String)request.getAttribute("errorMsg");
									if(errorMsg==null){
										errorMsg = "";
									}
								%>
								<span class="errorMsg"><%=errorMsg%></span> --%>
								<%-- <span class="errorMsg"><%=request.getAttribute("errorMsg")==null?"":request.getAttribute("errorMsg")%></span> --%>
								<span class="errorMsg">${errorMsg }</span>
							</div>
							<!-- 获取注册失败的请求参数数据显示到页面中 -->
							<%-- username:<%=request.getParameter("username") %>
							email:<%=request.getParameter("email") %> --%>
							<div class="form">
								<form action="UserServlet" method="post">
									<!-- 使用隐藏域携带type参数 -->
									<input type="hidden" name="type" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username"
									value="${param.username }" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" 
									 value="${param.email }"  />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img id="codeImg" alt="" src="../img/code.bmp" style="float: right; margin-right: 40px;width: 90px;height:40px;">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
		<span>
		中国环境保护产业协会管理系统.Copyright &copy;2018
		</span>
	</div>
</body>
</html>