package com.atguigu.bookstore.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

/**
 * 验证订单Servlet登录权限的Filter
 */
public class LoginFilter extends  HttpFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//检查用户是否登录，如果没有登录需要重新登录
    	HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			//未登录，不能执行订单操作，跳转到登录页面并给出错误提示
			//在请求域中设置一个错误消息
			String errorMsg = "订单操作必须登录！！！";
			request.setAttribute("errorMsg", errorMsg);
			//如果失败转发到login页面让用户继续登录   转发的路径由服务器解析，基准地址到项目名
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
	    	//已登录，放行请求到达目标资源
			chain.doFilter(request, response);
		}
		
		
	}

   
}
