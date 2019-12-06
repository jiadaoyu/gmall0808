package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public User getUserByUsernameAndPassword(User user) {
		//操作的表和返回的对象以及需求都能确定，参数也传入进来了
		String sql = "SELECT id , username , password , email FROM bs_user"
				+ " WHERE username = ? AND password = ? ";
		return getBean(User.class, sql, user.getUsername() , user.getPassword());
	}

	@Override
	public int saveUser(User user) {
		String sql = "INSERT INTO bs_user(username , password , email) VALUES(? , ? ,? )";
		//处理异常，根据自己的要求返回数据
		try {
			//如果没有异常正常返回
			return update(sql, user.getUsername() , user.getPassword() , user.getEmail());
		}catch(Exception e) {
			//如果有异常 返回0
			return 0;
		}
	}

	@Override
	public User getUserByUsername(String username) {
		//操作的表和返回的对象以及需求都能确定，参数也传入进来了
		String sql = "SELECT id , username , password , email FROM bs_user"
				+ " WHERE username = ? ";
		return getBean(User.class, sql, username);
	}

}
