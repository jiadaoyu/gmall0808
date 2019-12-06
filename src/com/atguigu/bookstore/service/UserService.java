package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.User;

/**
 * 处理用户相关的业务逻辑
 * 	- 相当于 厨师
 * 		数据库交互
 * 		servlet的请求业务由service处理
 * @author Administrator
 *
 */
public interface UserService {
	/**
	 * 检查用户名是否可用的业务方法
	 * @param username
	 * @return
	 */
	boolean checkUsername(String username);
	/**
	 * 登录业务方法
	 * @param user
	 * @return
	 */
	User login(User user);
	/**
	 * 注册业务方法
	 * @param user
	 * @return
	 */
	boolean regist(User user);
}
