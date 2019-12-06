package com.atguigu.bookstore.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BookService service = new BookServiceImpl();   
	/**
	 * 处理用户的查询价格区间的分页数据的请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
    protected void findPageByPrice(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//1、获取请求参数[页码、价格区间]
    	String pageNumber = request.getParameter("pageNumber");
    	int size = 4;
    	String minPrice = request.getParameter("min");
    	String maxPrice = request.getParameter("max");
    	//2、调用service完成分页查询的业务
    	Page<Book> page = service.findPageByPrice(pageNumber, size, minPrice, maxPrice);
    	//给page对象设置路径[分页导航栏需要使用]
    	page.setPath(WebUtils.getPath(request));
    	
    	
    	//3、将page设置到域中共享
    	//由于list页面复用了，可以展示分页数据和 按价格查询的分页数据 ， 所以两个方法查询的page对象设置的属性名需要一致
    	request.setAttribute("page", page);
    	//4、转发到list.jsp页面显示分页数据
    	request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
    	
    	
    }
    
    
    /**
	 * 用户查询分页数据的请求
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void findPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size = 4;
		Page<Book> page = service.findPage(pageNumber, size);
		//还需要给page对象绑定path属性[分页导航栏需要使用]
		page.setPath(WebUtils.getPath(request));
		
		//设置到request域中
		request.setAttribute("page", page);
		//转发到  index.jsp 显示分页数据
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
		
	}

}
