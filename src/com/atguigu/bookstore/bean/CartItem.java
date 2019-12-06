package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物项类
 * 		一个购物车可以有多个购物项
 * 		一本图书在购物车中最多对应一个购物项
 * @author Administrator
 *
 */
public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 购物项对应的图书信息
	 */
	private Book  book;
	/**
	 * 单本图书购买的数量
	 */
	private int count;
	/**
	 * 单本图书购买的总金额
	 * 		根据数量*单价计算得到
	 * 		int*double  : 小数精度问题  
	 * 			0.1+0.2==0.3
	 */
	private double amount;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		//计算得到金额
		BigDecimal bd1 = new BigDecimal(book.getPrice()+"");
		BigDecimal bd2 = new BigDecimal(count+"");
		BigDecimal result = bd1.multiply(bd2);
		
		return result.doubleValue();
	}
	/*public void setAmount(double amount) {
		this.amount = amount;
	}*/
	public CartItem(Book book, int count, double amount) {
		super();
		this.book = book;
		this.count = count;
		this.amount = amount;
	}
	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	public CartItem() {
		super();
	}
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount=" + amount + "]";
	}
	

}
