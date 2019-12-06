package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

/**
 * 对book表操作的约束
 * @author Administrator
 *
 */
public interface BookDao {
	/**
	 * 批量更新图书销量库存的方法
	 */
	void batchUpdateBookStockAndSales(Object[][]params);
	
	/**
	 * 按价格区间查询分页数据的方法
	 * @param book   只包含了  pageNumber  size  index三个参数
	 * @return   封装完毕的分页对象
	 */
	Page<Book> getPageByPrice(Page<Book> page , double min , double max);
	/**
	 * 查询分页数据的方法
	 * @param book   只包含了  pageNumber  size  index三个参数
	 * @return   封装完毕的分页对象
	 */
	Page<Book> getPage(Page<Book> page);
	/**
	 * 查询所有图书的方法
	 * @return
	 */
	List<Book> getAllBooks();
	/**
	 * 根据id查询指定图书的方法
	 * @param id
	 * @return
	 */
	Book getBookById(String id);
	/**
	 * 删除指定id图书的方法
	 * @param id
	 * @return
	 */
	int deleteBookById(String id);
	/**
	 * 保存图书的方法
	 * 		- 参数book对象没有id
	 * @param book
	 * @return
	 */
	int saveBook(Book book);
	/**
	 * 修改指定id图书的方法
	 * @param book   根据id匹配图书后修改其他值
	 * @return
	 */
	int updateBookById(Book book);
}
