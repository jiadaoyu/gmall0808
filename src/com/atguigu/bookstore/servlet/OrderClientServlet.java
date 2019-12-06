package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 * 处理用户的订单相关的请求
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private OrderService service = new OrderServiceImpl();  
    /**
     * 处理用户收货的请求方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void takeGoods(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//检查用户是否登录，如果没有登录需要重新登录
    	String orderId = request.getParameter("orderId");
    	boolean b = service.takeGoods(orderId);
    	//重定向回之前的页面
    	response.sendRedirect(request.getHeader("referer"));
	
    }
    
    
    /**
     * 处理用户根据自己id查询订单集合的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findUserOrders(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	//检查用户是否登录，如果没有登录需要重新登录
    	HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//已登录，调用servcie处理业务
		List<Order> list = service.findUserOrders(user.getId());
		//将集合共享到request域中
		request.setAttribute("list", list);
		//转发到/pages/order/order.jsp显示订单集合
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
	
    }
    /**
     * 处理结账请求
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、判断用户是否登录
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//已登录
		//2、获取session中的购物车对象
		Cart cart = (Cart) session.getAttribute("cart");
		
		//3、调用service处理结账的业务
		String orderId = service.createOrder(cart, user);
		///pages/cart/checkout.jsp
		//4、到结账成功页面显示 订单id
		//转发地址栏地址不变
		//使用重定向跳转到成功页面
		//将订单id共享到session中
		session.setAttribute("orderId", orderId);
		response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
			
	}

}
