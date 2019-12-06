package com.atguigu.bookstore.dao;
/**
 * 数据库操作的基类，提供增删改查的方法
 * @author Administrator
 *
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCUtils;

public class BaseDao {
	//创建操作数据库的对象
	private QueryRunner runner = new QueryRunner();
	
	//增删改  返回值<=0代表增删改失败
	public int update(String sql , Object...params) {
		int count = 0;
		//创建数据库连接
		Connection conn = JDBCUtils.getConn();
		try {
			//接受返回值
			count = runner.update(conn, sql, params);
		} catch (SQLException e) {
//			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			//关闭数据库连接[将连接还给连接池]
			//JDBCUtils.closeConn(conn);
		}
		return count;
	}
	//查询一条记录封装为一个javabean
	public <T>T getBean(Class<T>type , String sql , Object...params){
		T t = null;
		Connection conn = JDBCUtils.getConn();
		//参数3：  帮助工具类，可以根据提供的类型，将查询到的数据封装成指定类型的对象
		//BeanHandler将一条数据封装为一个对象    BeanListHandler将多条记录封装为对象的集合   ScalarHandler将单个字段的值封装为对象
		try {
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//关闭数据库连接[将连接还给连接池]
		//	JDBCUtils.closeConn(conn);
		}
		return t;
	}
	//查询多条记录封装为对象的集合
	public <T>List<T>  getBeanList(Class<T>type , String sql , Object...params){
		List<T> list = null;
		Connection conn = JDBCUtils.getConn();
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//关闭数据库连接[将连接还给连接池]
			//JDBCUtils.closeConn(conn);
		}
		return list;
	}
	//查询表记录总条数的方法     查询总数量时数量显示在查询的第一行第一列
	public int getCount(String sql , Object...params) {
		int count = 0;
		Connection conn = JDBCUtils.getConn();
		try {
			//查询到数据库的总数量是数字，ScalarHandler以Long形式读取，再将类型提升为Object
			//返回值真实类型是Long，不能强转为Integer
			count = (int)((long) runner.query(conn, sql, new ScalarHandler(), params));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//关闭数据库连接[将连接还给连接池]
			//JDBCUtils.closeConn(conn);
		}
		return count;
	}
	//批量增删改的方法：  如果涉及到大量的增删改操作，sql语句结构一样，但是参数值不一样，使用批处理可以提高操作效率
	//批处理需要知道 执行的次数(二维数组的第一维) ， 每次执行时的参数列表 （二维数组的第二维）
	public void batchUpdate(String sql , Object[][]params) {
		Connection conn = JDBCUtils.getConn();
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//关闭数据库连接[将连接还给连接池]
			//JDBCUtils.closeConn(conn);
		}
	}
}
