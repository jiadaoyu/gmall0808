package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

/**
 * 对订单表操作的方法的约束
 * @author Administrator
 *
 */
public interface OrderDao {
	/**
	 * 保存订单到订单表的方法
	 * @param order
	 * @return
	 */
	int saveOrder(Order order);
	/**
	 * 根据用户id查询用户的订单集合的方法
	 * @param userId
	 */
	List<Order> getOrdersByUserId(int userId);
	/**
	 * 管理员查询所有订单集合的方法
	 * @return
	 */
	List<Order> getAllOrders();
	/**
	 * 根据订单id修改订单状态值的方法
	 * @param orderId
	 * @param state
	 * @return
	 */
	int updateStateByOrderId(String orderId , int state);
	
	
	
	
	
	
	
}
