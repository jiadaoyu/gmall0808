package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

	@Override
	public int saveOrderItem(OrderItem item) {
		String sql = "INSERT INTO bs_orderitem(title , author , img_path , count , amount , price , order_id ) "
				+ "VALUES(? , ? ,? ,? ,? ,? ,? )";
		return update(sql, item.getTitle() , item.getAuthor() ,
				item.getImgPath() , item.getCount() ,  item.getAmount() ,item.getPrice() ,item.getOrderId());
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(String orderId) {
		String sql = "SELECT id , title , author , img_path imgPath , count , amount , price , order_id orderId"
				+ " FROM bs_orderitem WHERE order_id = ? ";
		return getBeanList(OrderItem.class, sql, orderId);
	}

	@Override
	public void batchSaveOrderItem(Object[][] params) {
		String sql = "INSERT INTO bs_orderitem(title , author , img_path , count , amount , price , order_id ) "
				+ "VALUES(? , ? ,? ,? ,? ,? ,? )";
		batchUpdate(sql, params);
	}

}
