package com.atguigu.bookstore.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;

/**
 * web项目的工具类
 * @author Administrator
 *
 */
public class WebUtils {
	
	/**
	 * 从request中拼接分页导航栏需要使用的路径
	 */
	public static String getPath(HttpServletRequest request) {
    	//动态获取分页需要使用的路径    /项目名/BookManagerServlet?type=findPage
    	String requestURI = request.getRequestURI();//获取资源定位路径[？左边到端口号之间的]
    	String queryString = request.getQueryString();//获取?右边的查询字符串
    	//截取参数字符串   type=findPage&pageNumber=1
    	if(queryString.contains("&pageNumber")) {
    		queryString = 	queryString.substring(0, queryString.indexOf("&pageNumber"));
    	}
    	String path = requestURI+"?"+queryString;
    	return path;
	}
	
	
	/**
	 * 将request中的请求参数自动封装给javabean的方法
	 */
	public static <T>T param2Bean(T t , HttpServletRequest request){
		//获取数据源
		Map<String, String[]> map = request.getParameterMap();
    	try {
    		//给t对象设置属性值
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//返回设置完属性值的对象
    	return t;
	}

}
