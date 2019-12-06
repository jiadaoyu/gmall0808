package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

/**
 * 约束对订单项表的操作
 * @author Administrator
 *
 */
public interface OrderItemDao {
	
	
	/**
	 * 批量保存订单项的方法
	 * 		批处理执行多少次
	 * 		每次批处理需要的参数列表
	 * @param item
	 * @return
	 */
	void batchSaveOrderItem(Object[][] params);
	/**
	 * 保存订单项的方法
	 * @param item
	 * @return
	 */
 	int saveOrderItem(OrderItem item);
 	/**
 	 * 根据订单id查询订单项集合的方法
 	 * @param orderId
 	 * @return
 	 */
 	List<OrderItem>getOrderItemsByOrderId(String orderId);
}
