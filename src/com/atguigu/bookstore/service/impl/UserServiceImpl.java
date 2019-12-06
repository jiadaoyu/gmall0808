package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService{
	//实现简单业务  调用dao直接可以实现
	private UserDao dao = new UserDaoImpl();
	
	@Override
	public User login(User user) {
		return dao.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		int i = dao.saveUser(user);
		return i>0;
	}

	@Override
	public boolean checkUsername(String username) {
		//如果查找的user对象为null则返回true，表示用户名可用
		return dao.getUserByUsername(username)==null;
	}

}
