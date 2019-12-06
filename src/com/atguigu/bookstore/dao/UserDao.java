package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * 通过接口约束对bs_user表的操作
 *
 */
public interface UserDao {
	
	
	//根据账号询用户对象的方法
	User getUserByUsername(String username);
	
	//根据账号密码查询用户对象的方法
	User getUserByUsernameAndPassword(User user);
	//保存数据到数据库的方法
	int saveUser(User user);
}
