package com.atguigu.bookstore.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 管理数据库连接
 * @author Administrator
 *
 */
public class JDBCTools {

	private static DataSource source = null;
	//初始化连接池
	static {
		Properties info = new Properties();
		//使用自己创建类的类加载器可以加载到类路径下的配置文件
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		//使用info将is流中的properties文件加载到properties对象中
		try {
			info.load(is);
			//创建连接池对象并将初始化参数info传入
			source = DruidDataSourceFactory.createDataSource(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ThreadLocal<T>：类似线程帮助工具类，默认以当前线程对象作为键，可以随意给当前线程对象绑定一个值
	 * 		T 要绑定的数据类型
	 * 	void set(T t);  默认以当前线程对象为键，将t保存到内部维护的map中
	 *  T get();  默认以当前线程对象为键取出内部map中的值
	 *  void remove();  默认以当前线程对象为键移除map中的值
	 */
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	public static Connection getConn() {
		//获取当前线程对象绑定的连接
		Connection conn = local.get();
		//判断连接是否存在
		if(conn==null) {
			//创建连接并绑定给当前线程对象
			try {
				conn = source.getConnection();
				local.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void cloesConnection() {
		Connection conn = local.get();
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		local.remove();
	}
}
