package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class UserDaoTest {
	private UserDao dao = new UserDaoImpl();
	@Test
	public void testSave() {
		//模拟用户注册：用户注册时会提交注册参数给RegistServlet
		String username = "admin";
		String password = "123456";
		String email = "admin@atguigu.com";
		User user = new User(null, username, password, email);
		//调用dao存数据到数据库中
		int i = dao.saveUser(user);
		//根据返回值给用户响应
		if(i>0) {
			//注册成功
			System.out.println("恭喜你，注册成功了..");
		}else {
			//如果用户名重复注册会注册失败
			System.out.println("恭喜你，注册失败了..");
			
		}
	}
	@Test
	public void testGet() {
		String username = "admin1";
		String password = "123456";
		User user = new User(null, username, password, null);
		
		User u = dao.getUserByUsernameAndPassword(user);
		if(u==null) {
			//登录失败
			System.out.println("登录失败....");
		}else {
			System.out.println("登录成功...."+u);
			
		}
		
	}

}
