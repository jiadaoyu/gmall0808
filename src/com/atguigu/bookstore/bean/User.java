package com.atguigu.bookstore.bean;

import java.io.Serializable;

/**
 * 用户类，对应bs_user
 *
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id  
	 * 		使用包装类，new User()对象时，包装类属性默认值是null
	 * 		如果使用int，new User()时，属性值默认是0
	 */
	private Integer id;
	/**
	 * 用户账号
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户邮箱
	 */
	private String email;
	public User(Integer id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	

}
