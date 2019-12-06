package com.atguigu.bookstore.utils;
/**
 * 数据库连接的工具类
 * @author Administrator
 *
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
	/**
	 * 为线程对象保存对应的数据库连接的map
	 * 	ConcurrentHashMap:  线程安全的map
	 * 
	 * 
	 * ThreadLocal 也可以给线程对象绑定一个数据，并且也是数据安全的
	 * 
	 */
	private static Map<Thread, Connection> map =  new ConcurrentHashMap<Thread, Connection>();
	/**
	 * 创建数据库连接池
	 * 	一个项目使用一个
	 */
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
	
	//获取线程绑定的数据库连接的方法
	public static Connection getConn() {
		//判断map中有没有当前线程对象对应的数据库连接
		Connection conn = map.get(Thread.currentThread());
		if(conn==null) {
			//线程没有对应的连接，需要创建并绑定
			try {
				conn = source.getConnection();
				map.put(Thread.currentThread(), conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	//释放线程对象绑定的连接的方法
	public static void releaseConnection() {
		Connection conn = map.get(Thread.currentThread());
		if(conn!=null) {
			try {
				//断开连接
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//移除
		map.remove(Thread.currentThread());
	}
	
	
	//释放连接的方法
	public static void closeConn(Connection conn) {
		if(conn!=null ) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
