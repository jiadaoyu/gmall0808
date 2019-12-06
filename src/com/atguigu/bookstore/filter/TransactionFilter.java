package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 */
public class TransactionFilter extends HttpFilter {
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1、获取当前线程对象绑定数据库的连接
		Connection conn = JDBCUtils.getConn();
		//2、开启事务
		try {
			conn.setAutoCommit(false);
			//3、放行请求到目标资源[请本次线程的请求继续执行]
			//执行多个sql操作
			chain.doFilter(request, response);
			//没有异常，提交事务
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//有异常 回滚
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//给用户响应一个错误页面
			response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
		}finally {
			//5、响应回来时[当放行方法执行完毕，代表响应回来]释放资源
			//将JDBCUtils中的map当前线程对象的数据库连接释放并移除
			JDBCUtils.releaseConnection();
		}
	
		
		
		
			
		
	}

}
