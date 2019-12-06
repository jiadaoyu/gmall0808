package com.atguigu.bookstore.bean;

import java.io.Serializable;

/**
 * bs_book表的实体类
 * @author Administrator
 *
 */
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图书id
	 */
	private Integer id;
	/**
	 * 图书标题
	 */
	private String title;
	/**
	 * 图书作者
	 */
	private String author;
	/**
	 * 图书封面地址
	 */
	private String imgPath;
	/**
	 * 图书单价
	 */
	private double price;
	/**
	 * 图书库存
	 */
	private int stock;
	/**
	 * 图书销量
	 */
	private int sales;
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
	//数据库查询时，使用BeanHandler和BeanListHandler将查询的数据封装成Book对象，调用了Book的无参构造器
	public Book() {
		super();
	}
	public Book(Integer id, String title, String author, String imgPath, double price, int stock, int sales) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.imgPath = imgPath;
		this.price = price;
		this.stock = stock;
		this.sales = sales;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", imgPath=" + imgPath + ", price="
				+ price + ", stock=" + stock + ", sales=" + sales + "]";
	}
	
	
	
	
	
}
