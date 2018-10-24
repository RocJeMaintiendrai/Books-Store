package com.atguigu.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取和释放数据库连接
 * 
 * @author lfy
 * 
 */
public class JDBCUtils {

	/**
	 * 保证书城使用的是一个数据库连接池
	 */
	private static DataSource ds = new ComboPooledDataSource("webDataSource");
	private static Map<Long, Connection> conns = new HashMap<>();
	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		long id = Thread.currentThread().getId();
		System.out.println("JDBCUtils中的线程号："+id);
		//获取当前线程的连接
		Connection connection = conns.get(Thread.currentThread().getId());
		if(connection==null){
			try {
				connection = ds.getConnection();
				//connection.setAutoCommit(false);
				//把链接保存在map中,当我们第一次要连接的时候，map中没有，我们新建了一个
				//把他保存，以后任何地方只要拿当前线程号获取，都获取到的是当前线程对应的连接
				conns.put(id, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	/**
	 * 释放数据库连接
	 * @param connection
	 */
	public static void releaseConnection() {
		//获取当前线程的连接
		Connection connection = getConnection();
		try {
			connection.close();
			conns.remove(Thread.currentThread().getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
