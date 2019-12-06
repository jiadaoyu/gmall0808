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

/**
 * 继承HttpFilter的子类实现重载doFilter方法
 * 
 * 	当用户访问HttpFilter子类拦截资源时，服务器会盗用HttpFilter子类的生命周期doFilter方法进行拦截
 * 		
 * 
 */
public abstract class HttpFilter implements Filter {

   
	public void destroy() {
	}
	//生命周期方法
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//将参数转为Http类型
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		//调用重载的doFilter方法
		doFilter(req, res, chain);
	}
	//重载方法
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
