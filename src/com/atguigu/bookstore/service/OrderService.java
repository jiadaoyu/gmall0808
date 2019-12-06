package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.User;

/**
 * 约束订单操作相关的业务方法
 * @author Administrator
 *
 */
public interface OrderService {
	/**
	 * 管理员查询所有订单集合的业务方法
	 * @return
	 */
	List<Order> getAllOrders();
	
	/**
	 * 处理管理员发货的业务方法
	 * 		将订单state改为2
	 * @return
	 */
	boolean sendGoods(String orderId);
	/**
	 * 处理收货的业务方法
	 * 		将订单state改为2
	 * @return
	 */
	boolean takeGoods(String orderId);
	
	/**
	 * 根据用户id查询订单集合的方法
	 * @param userId
	 * @return
	 */
	List<Order> findUserOrders(int userId);
	/**
	 * 创建订单的业务方法：
	 * 		将购物车转为订单对象+将订单和用户关联
	 * 		将购物车的购物项集合转为一个个的订单项对象
	 * @param order
	 * @param user
	 * @return
	 */
	String createOrder(Cart cart , User user);

}
