package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao implements BookDao {

	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT id , title , author , sales , stock ,"
				+ " img_path imgPath, price FROM bs_book";
		return getBeanList(Book.class, sql);
	}

	@Override
	public Book getBookById(String id) {
		String sql = "SELECT id , title , author , sales , stock ,"
				+ " img_path imgPath, price FROM bs_book WHERE id = ?";
		return getBean(Book.class, sql, id);
	}

	@Override
	public int deleteBookById(String id) {
		String sql = "DELETE FROM bs_book WHERE id = ?";
		return update(sql, id);
	}

	@Override
	public int saveBook(Book book) {
		String sql = "INSERT INTO bs_book(title , author , sales , stock , img_path , price "
				+ ")  VALUES(?,?,?,?,?,?)";
		return update(sql, book.getTitle(),book.getAuthor() , book.getSales() , book.getStock() , book.getImgPath(),
				book.getPrice());
	}

	@Override
	public int updateBookById(Book book) {
		String sql = "UPDATE bs_book SET title = ?  , author = ? , sales= ? , stock=? , img_path=? , price=? "
				+ "WHERE id = ?";
		return update(sql, book.getTitle(),book.getAuthor() , book.getSales() , book.getStock() , book.getImgPath(),
				book.getPrice() , book.getId());
	}

	@Override
	public Page<Book> getPage(Page<Book> page) {
		//1、查询图书总数量 并设置给page对象
		String countSql = "SELECT COUNT(*) FROM bs_book";
		int count = getCount(countSql);
		page.setTotalCount(count);
		//调用page对象的getTotalPage方法会自动计算
		//2、查询分页需要显示的图书集合并设置给page
		String sql = "SELECT id , title , author , sales , stock , "
				+ "img_path imgPath, price FROM bs_book LIMIT ? , ? ";
		List<Book> list = getBeanList(Book.class, sql, page.getIndex() , page.getSize());	
		page.setData(list);
		//3、返回page对象
		return page;
	}

	@Override
	public Page<Book> getPageByPrice(Page<Book> page, double min, double max) {
		//1、查询价格区间的图书的总数量并设置给page对象
		String countSql = "SELECT COUNT(*) FROM bs_book WHERE price BETWEEN ? AND ?";
		int count = getCount(countSql, min , max);
		page.setTotalCount(count);
		//2、查询价格区间的分页的图书集合并设置给page对象
		String sql = "SELECT id , title , author , sales , stock , img_path imgPath, price "
				+ "FROM bs_book WHERE price BETWEEN ?  AND ? LIMIT ? , ?";
		List<Book> list = getBeanList(Book.class, sql, min , max , page.getIndex() , page.getSize());
		page.setData(list);
		
		return page;
	}

	@Override
	public void batchUpdateBookStockAndSales(Object[][] params) {
		String sql = "UPDATE bs_book SET sales= ? , stock=?  "
				+ "WHERE id = ?";
		batchUpdate(sql, params);
	}

}
