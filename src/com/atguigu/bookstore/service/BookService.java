package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 图书操作的业务方法
 * @author Administrator
 *
 */
public interface BookService {
	/**
	 * 按价格处理分页业务的方法
	 * @param pageNumber
	 * @param size
	 * @return
	 */
	Page<Book>	findPageByPrice(String pageNumber , int size , String minPrice , String maxPrice);
	/**
	 * 处理分页业务的方法
	 * @param pageNumber
	 * @param size
	 * @return
	 */
	Page<Book>	findPage(String pageNumber , int size);
	/**
	 * 修改图书的方法
	 * @param book   管理员从book_edit.jsp页面提交的包括id的所有的数据的图书对象
	 * @return
	 */
	boolean updateBook(Book book);
	/**
	 * 查询指定id图书的业务方法
	 * @param bookId
	 * @return
	 */
	Book findBook(String bookId);
	/**
	 * 查询所有图书的方法
	 * @return
	 */
	List<Book> findAllBooks();
	/**
	 * 删除指定图书的业务方法
	 * @param bookId
	 * @return
	 */
	boolean deleteBook(String bookId);
	
	/**
	 * 保存图书的业务方法
	 * @param book
	 * @return
	 */
	boolean addBook(Book book);
}
