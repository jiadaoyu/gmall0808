package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有Servlet的基类
 * 	- 在doget中通过type值通过反射调用子类的方法
 * 
 * 1、创建BaseServlet并继承HttpServlet
 * 2、在doGet中通过反射处理请求，调用子类的type值对应的方法
 * 3、修改UserServlet继承BaseServlet
 * 4、删除UserServlet的doGet和doPost方法
 * 
 * 使用BaseServlet时需要注意：
 * 	  BaseServlet中this代表子类对象
 * 	    子类不能重写doGet和doPost
 * 	    所有请求BaseServlet的子类的请求必须提交type参数值，并且对应子类中的一个方法名
 * 	   子类的方法必须按照doGet方法的结构来写
 * 
 * BaseServlet不必在配置文件中配置，用户不会直接访问BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、获取方法名参数
		String type = request.getParameter("type");
		//2、获取子类的类型[需要在子类中去根据type匹配方法]
		Class<? extends BaseServlet> cla = this.getClass();
		//3、查找cla中确定的方法对象
		try {
			Method method = cla.getDeclaredMethod(type, HttpServletRequest.class , HttpServletResponse.class);
			//4、调用方法
			method.invoke(this, request , response);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
