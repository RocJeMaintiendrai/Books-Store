package com.atguigu.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.utils.JDBCUtils;

public class BaseDao<T> {

	private QueryRunner runner = new QueryRunner();
	// 需要获取实际的type
	private Class<T> type;

	public BaseDao() {
		// TODO Auto-generated constructor stub
		// 获取父类的类型,父类是带参数的
		ParameterizedType superclass = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		System.out.println(superclass.getClass());
		// 获取参数的类型
		type = (Class<T>) superclass.getActualTypeArguments()[0];
	}

	/**
	 * 获取一个对象
	 * 
	 * @return
	 */
	public T getBean(String sql, Object... params) {
		Connection connection = JDBCUtils.getConnection();
		T query = null;
		try {
			query = runner.query(connection, sql, new BeanHandler<>(type),
					params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("发生异常");
		} 
		return query;
	}

	/**
	 * 获取对象的集合
	 * 
	 * @return
	 */
	public List<T> getBeanList(String sql, Object... params) {
		// 1、获取数据库连接
		Connection connection = JDBCUtils.getConnection();
		List<T> query = null;
		try {
			// 2、查询一组数据
			query = runner.query(connection, sql, new BeanListHandler<>(type),
					params);
		} catch (SQLException e) {
			throw new RuntimeException("发生异常");
		} 

		return query;
	}

	/**
	 * 执行增删改
	 * 
	 * @return
	 */
	public int update(String sql, Object... params) {
		// runner.u
		int count = 0;
		Connection connection = JDBCUtils.getConnection();
		try {
			count = runner.update(connection, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException("发生异常");
		} 
		return count;
	}

	/**
	 * 查询单个值
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object getSingleValue(String sql, Object... params) {
		Object query = null;
		Connection connection = JDBCUtils.getConnection();
		try {
			query = runner.query(connection, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new RuntimeException("发生异常");
		} 
		return query;
	}

	/**
	 * 批量执行sql的方法
	 * @param sql
	 * @param params
	 * @return
	 */
	public int batch(String sql, Object[][] params) {
		// batch(Connection arg0, String arg1, Object[][] arg2)
		Connection connection = JDBCUtils.getConnection();
		try {
			// Object[5][] params params[0] -->一维数组
			// 第一维的长度就代表sql的执行次数 第二维专门用来存放当前sql执行要用的可变参数
			// "insert into bs_order_item(title,count,price,total_price,order_id) values(?,?,?,?,?)"
			runner.batch(connection, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException("发生异常");
		} 
		return 0;
	}

}
