package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.dao.BookDao;

import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class BookDaoTest {
	private BookDao dao = new BookDaoImpl();
	
	@Test
	public void testSave() {
		//保存的图书数据是管理员提交的数据    servlet接受后会封装为book对象
		Book book1 = new Book(null, "tj和51个男学生的一天", "ly", "/static/img/default.jpg", 0.1, 100, 52);
		Book book2 = new Book(null, "tj和51个男学生的27天", "ly", "/static/img/default.jpg", 0.1, 100, 52);
		Book book3 = new Book(null, "ly和51个男学生的一天", "tj", "/static/img/default.jpg", 1.1, 50, 520);
		Book book4 = new Book(null, "ly和51个男学生的3天", "tj", "/static/img/default.jpg", 1.1, 100, 52);
		int i = dao.saveBook(book1);
		System.out.println(i);//i>0代表保存成功
		dao.saveBook(book2);
		dao.saveBook(book3);
		dao.saveBook(book4);
		
	}
	@Test
	public void testSelectAll() {
		List<Book> list = dao.getAllBooks();
		System.out.println(list);
	}
	@Test
	public void testSelect() {
		String bookId = "2";
		Book book = dao.getBookById(bookId);
		System.out.println(book);
	}
	@Test
	public void testUpdate() {
		//修改图书时 必须提交要修改图书的id
		Book book3 = new Book(3, "刘优和51个男学生的一天", "tj", "/static/img/default.jpg", 1.1, 50, 520);
		int i = dao.updateBookById(book3);
		System.out.println(i);
	}
	@Test
	public void testDelete() {
		String id = "4";
		int i = dao.deleteBookById(id);
		System.out.println(i);
	}

}
