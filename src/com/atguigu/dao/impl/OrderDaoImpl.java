package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.Order;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

	@Override
	public int saveOrder(Order order) {
		// TODO Auto-generated method stub
		long id = Thread.currentThread().getId();
		System.out.println("orderDao中的线程号："+id);
		String sql = "insert into bs_order(order_id,create_date,total_money,status,user_id) "
				+ "values(?,?,?,?,?)";
		int update = update(sql, order.getOrderId(), order.getCreateDate(),
				order.getTotalMoney(), order.getStatus(), order.getUserId());
		return update;
	}

	@Override
	public int updateStatus(Order order) {
		// TODO Auto-generated method stub
		String sql = "update bs_order set status=? where order_id=?";
		return update(sql, order.getStatus(),order.getOrderId());
	}

	/**
	 * 返回所有订单
	 */
	@Override
	public List<Order> getOrderList() {
		// TODO Auto-generated method stub
		String sql = "select order_id orderId,create_date createDate,total_money totalMoney,"
				+ "status,user_id userId from bs_order";
		return getBeanList(sql);
	}

	/**
	 * 
	 */
	@Override
	public List<Order> getOrderByUserId(Integer userId) {
		String sql = "select order_id orderId,create_date createDate,total_money totalMoney,"
				+ "status,user_id userId from bs_order where user_id=?";
		return getBeanList(sql,userId);
	}

}
