package com.atguigu.bookstore.service.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService{
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public String createOrder(Cart cart, User user) {
		//就是给订单需要的数据进行初始化
		//1、订单id   唯一，不能暴露公司信息，便于售后使用   时间戳+暗号+用户id
		String id = System.currentTimeMillis()+""+user.getId();
		//2、订单时间
		Date orderTime = new Date();
		//3、订单状态  默认未发货
		int state = 0;
		
		//创建订单对象
		Order order = new Order(id, orderTime, state, cart.getTotalCount(), cart.getTotalAmount(), user.getId());
		//4、调用OrderDao将订单数据存到数据库中
		orderDao.saveOrder(order);
		
		//5、将购物项集合转为一个个的订单项存到数据库中
		List<CartItem> list = cart.getCartItemList();
		//使用批处理优化：遍历的过程其实就是封装批处理需要的二维数据
		Object[][] bookParams = new Object[list.size()][];//数组的长度就是批处理执行的次数  长度和购物项集合的长度一样
		Object[][] orderItemParams = new Object[list.size()][];
		int index = 0;
		for (CartItem cartItem : list) {
			//将购物项转为订单项
			Book book = cartItem.getBook();
			OrderItem item = new OrderItem(null, book.getTitle(), book.getImgPath(), book.getAuthor(), book.getPrice(),
					cartItem.getAmount(), cartItem.getCount(), id);
			//调用OrderItemDao将订单项存到数据库中
			//orderItemDao.saveOrderItem(orderItem);
			//bookDao.updateBookById(book);
			//每次遍历给二维数组对应的位置填充数据
			//sales  stock  id
			bookParams[index] = new Object[] {book.getSales()+cartItem.getCount() ,book.getStock()-cartItem.getCount(),book.getId() };
			//title , author , img_path , count , amount , price , order_id
			orderItemParams[index] = new Object[] {item.getTitle() , item.getAuthor() ,
					item.getImgPath() , item.getCount() ,  item.getAmount() ,item.getPrice() ,item.getOrderId()};
			index++;
		}
		//统一执行批处理
		orderItemDao.batchSaveOrderItem(orderItemParams);
		bookDao.batchUpdateBookStockAndSales(bookParams);
		//7、清空购物车数据
		cart.clearCart();
		
		
		//返回订单id
		return id;
	}
	@Override
	public List<Order> findUserOrders(int userId) {
		
		return orderDao.getOrdersByUserId(userId);
	}
	@Override
	public boolean takeGoods(String orderId) {
		
		return orderDao.updateStateByOrderId(orderId, 2)>0;
	}
	@Override
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}
	@Override
	public boolean sendGoods(String orderId) {
		return orderDao.updateStateByOrderId(orderId, 1)>0;
	}

}
