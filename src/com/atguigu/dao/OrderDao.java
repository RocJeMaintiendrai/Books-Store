package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Order;

/**
 * 操作订单的dao
 * @author lfy
 *
 */
public interface OrderDao {
	/**
	 * 保存订单
	 * @param order
	 * @return
	 */
	public int saveOrder(Order order);
	
	/**
	 * 修改订单状态
	 * @param order
	 * @return
	 */
	public int updateStatus(Order order);
	
	/**
	 * 获取所有订单(管理员用)
	 * @return
	 */
	public List<Order> getOrderList();
	
	/**
	 * 获取某个用户的所有订单
	 * @param userId
	 * @return
	 */
	public List<Order> getOrderByUserId(Integer userId);
}
