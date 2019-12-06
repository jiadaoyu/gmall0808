package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService {
	
	private BookDao dao = new BookDaoImpl();
	@Override
	public List<Book> findAllBooks() {
		//调用BookDao可以查询到集合
		return dao.getAllBooks();
	}
	@Override
	public boolean deleteBook(String bookId) {
		return dao.deleteBookById(bookId)>0;
	}
	@Override
	public boolean addBook(Book book) {
		return dao.saveBook(book)>0;
	}
	@Override
	public Book findBook(String bookId) {
		return dao.getBookById(bookId);
	}
	@Override
	public boolean updateBook(Book book) {
		return dao.updateBookById(book)>0;
	}
	@Override
	public Page<Book> findPage(String pageNumber, int size) {
		//1、创建分页对象
		Page<Book> page = new Page<Book>();
		//将String类型转为int类型   由于页码是用户传入，可能不是一个数字，可能有异常
		int no = 1;
		try {
			no = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//2、将参数设置给page对象
		page.setPageNumber(no);
		page.setSize(size);
		//3、调用dao完成数据库数据的查询
		return dao.getPage(page);
	}
	@Override
	public Page<Book> findPageByPrice(String pageNumber, int size, String minPrice, String maxPrice) {
		Page<Book> page = new Page<Book>();
		//数据类型转换
		int no = 1;
		double min = 0;
		double max = 20000;
		//异常分开处理，  避免一个异常出现影响其他正确值的类型转换
		try {
			no = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			min = Double.parseDouble(minPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			max = Double.parseDouble(maxPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将转换过的数据设置给page对象
		page.setPageNumber(no);
		page.setSize(size);
		//调用dao查询价格区间的数据再设置给page对象
		
		return dao.getPageByPrice(page, min, max);
	}

}
