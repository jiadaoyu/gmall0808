package com.atguigu.bookstore.bean;
/**
 * 对应bs_orderitem表的实体类
 * @author Administrator
 *
 */
public class OrderItem {
	/**
	 * 订单项id
	 */
	private Integer id;
	/**
	 * 购买时订单项的图书标题
	 */
	private String title;
	/**
	 * 购买时订单项的图书封面地址
	 */
	private String imgPath;
	/**
	 * 购买时订单项的图书作者
	 */
	private String author;
	/**
	 * 购买时订单项的图书单价
	 */
	private double price;
	/**
	 * 购买时订单项的单品金额
	 */
	private double amount;
	/**
	 * 购买时订单项的单品数量
	 */
	private int count;
	/**
	 * 订单项所属的订单的id
	 */
	private String orderId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public OrderItem(Integer id, String title, String imgPath, String author, double price, double amount, int count,
			String orderId) {
		super();
		this.id = id;
		this.title = title;
		this.imgPath = imgPath;
		this.author = author;
		this.price = price;
		this.amount = amount;
		this.count = count;
		this.orderId = orderId;
	}
	public OrderItem() {
		super();
	}
	
}
