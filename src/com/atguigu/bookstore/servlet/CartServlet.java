package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;

/**
 * 处理用户购物车相关的请求
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private BookService service  = new BookServiceImpl();   
    
    /**
     * 更新购物项数量的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String bookId = request.getParameter("bookId");
    	String count = request.getParameter("count");
    	
    	//获取session中的购物车对象
    	HttpSession session = request.getSession();
    	Cart cart = (Cart) session.getAttribute("cart");
    	cart.updateCartItemCountByBookId(bookId, count);
    	
    	//重定向回之前的页面
    	//response.sendRedirect(request.getHeader("referer"));
    	//给ajax请求响应结果   三个数据： 1、修改数量的购物项的单品总金额 ， 2、购物车商品总数量 3、购物车的商品总金额
    	Map<String, String> map = new HashMap<String,String>();
    	map.put("amount", cart.getMap().get(bookId).getAmount()+"");
    	map.put("totalCount", cart.getTotalCount()+"");
    	map.put("totalAmount", cart.getTotalAmount()+"");
    	Gson gson = new Gson();
    	
    	String json = gson.toJson(map);
    	response.getWriter().write(json);
    	
    	
    }
    /**
     * 删除购物项
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//获取要删除的购物项对应的图书id
    	String bookId = request.getParameter("bookId");
    	//获取session中的购物车
    	HttpSession session = request.getSession();
    	Cart cart = (Cart) session.getAttribute("cart");
    	//调用购物车对象的方法处理删除业务
    	cart.deleteCartItemByBookId(bookId);
    	//重定向回之前的页面
    	response.sendRedirect(request.getHeader("referer"));
    	
    }
    
    /**
     * 处理清空购物车请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//获取session中的购物车
    	HttpSession session = request.getSession();
    	Cart cart = (Cart)session.getAttribute("cart");
    	//调用购物车对象的清空方法处理业务
    	cart.clearCart();
    	
    	//给用户响应： 重定向到之前的页面
    	response.sendRedirect(request.getHeader("referer"));
    }
    
    
    /**
     * 添加图书到购物车的功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要添加图书的id
		String bookId = request.getParameter("bookId");
		//根据id查询指定的图书对象
		Book book = service.findBook(bookId);
		//将图书添加到购物车中[购物车存在session中]
		//判断session域中是否已经存在购物车对象，如果存在将图书添加到购物车中，如果不存在先创建购物车存到session然后再将图书添加到购物车中
		HttpSession session = request.getSession();
		//从session中 获取购物车对象
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null) {
			//第一次使用购物车
			//session中还没有购物车对象，需要新创建并存到session中
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		//多次使用第一次创建的购物车对象
		cart.addBook2Cart(book);
		
		//将最新添加的图书的标题存到session中
		session.setAttribute("title", book.getTitle());
		
		//重定向到添加之前的页面
		//response.sendRedirect(request.getHeader("referer"));
		
		//给ajax请求响应需要的数据 将需要的数据 封装为json字符串响应给浏览器
		//  如果是map： map.put("key1" , "value1")      {"key1":"value1" , "key2": "value2"}
		Map<String , String> map = new HashMap<String ,String>();
		map.put("title", book.getTitle());
		map.put("totalCount", cart.getTotalCount()+"");
		//使用Gson将map转为json字符串
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		//将jsonStr写入到响应体中
		response.getWriter().write(jsonStr);
		
	}

}
