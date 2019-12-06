package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车类
 * 		一个浏览器只能使用一个购物车
 * 		购物车和session对应一对一的关系
 * 		
 */
public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 保存购物项数据的集合
	 * 		键：  购物项对应的图书的id，在集合中是唯一的
	 * 		值： id图书对应的购物项队形
	 */
	private Map<String , CartItem> map = new LinkedHashMap<String,CartItem>();
	/**
	 * 购物车商品总数量：
	 * 		遍历map中的购物项累加数量得到
	 */
	private int totalCount;
	/**
	 * 购物车商品总金额
	 * 		遍历map的购物项累加金额得到
	 */
	private double totalAmount;
	public Map<String, CartItem> getMap() {
		return map;
	}
	public int getTotalCount() {
		//给totalCount设置初始值
		totalCount = 0;
		//遍历累加购物项的商品总数量
		for (CartItem item : getCartItemList()) {
			totalCount+=item.getCount();
		}
		return totalCount;
	}
	public double getTotalAmount() {
		totalAmount = 0;
		//遍历累加购物项的商品总金额
		BigDecimal bd1 = new BigDecimal(totalAmount+"");
		for (CartItem item : getCartItemList()) {
			BigDecimal bd2 = new BigDecimal(item.getAmount()+"");
			bd1 = bd1.add(bd2);
			
		}
		totalAmount = bd1.doubleValue();
		return totalAmount;
	}
	/**
	 * 将map的值转为list的方法
	 */
	public List<CartItem>  getCartItemList(){
		Collection<CartItem> values = map.values();
		ArrayList<CartItem> list = new ArrayList<CartItem>(values);
		return list;
	}
	
	/**
	 * 清空购物车的方法：
	 * 		就是将map中的数据全部清空掉
	 */
	public void clearCart() {
		map.clear();
	}
	
	/**
	 * 删除指定购物项的方法：
	 * 		就是根据图书id移除map中对应的购物项
	 */
	public void deleteCartItemByBookId(String bookId) {
		map.remove(bookId);
	}
	/**
	 * 添加图书到购物车的方法:
	 * 		需要判断购物车中是否存在图书对应的购物项
	 * 			- 如果有  在原有数量上+1即可
	 * 			- 如果没有  将图书转为购物项第一次添加到购物车中
	 * 
	 */
	public void addBook2Cart(Book book) {
		//1、判断购物车中是否有book的购物城
		//根据图书id去map中查找对应的购物项
		CartItem item = map.get(book.getId()+"");//一定要将id转为字符串使用
		if(item==null) {
			//2、没有
			//将图书转为购物项    第一次添加 数量默认为1
			item = new CartItem(book, 1);
			//将购物项添加到购物车中
			map.put(book.getId()+"", item);
		}else {
			//3、购物项存在
			//在原有数量上+1
			item.setCount(item.getCount()+1);
		}
	}
	/**
	 * 更新购物项数量的方法
	 */
	public void updateCartItemCountByBookId(String bookId,String count) {
		//获取图书id对应的购物项
		CartItem cartItem = map.get(bookId);
		int i = cartItem.getCount();//设置为之前的数量
		try {
			i = Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//修改购物项的数量
		cartItem.setCount(i);
	}
	
}
