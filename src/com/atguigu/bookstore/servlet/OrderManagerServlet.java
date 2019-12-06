package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

/**
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private OrderService service = new OrderServiceImpl();  
    /**
     * 处理发货请求的方法
     */
    protected void sendGoods(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String orderId = request.getParameter("orderId");
    	boolean b = service.sendGoods(orderId);
    	
    	//跳转回之前的页面
    	response.sendRedirect(request.getHeader("referer"));
    }
    
    
    /**
     * 管理员查询所有订单集合的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	protected void findAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> list = service.getAllOrders();
		request.setAttribute("list", list);
		//转发到管理员管理订单的页面   /pages/manager/order_manager.jsp
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
		
	}

}
