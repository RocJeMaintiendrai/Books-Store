package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.OrderItem;

/**
 * 操作订单项dao
 * @author lfy
 *
 */
public interface OrderItemDao {
	
	/**
	 * 获取某个订单的所有订单项
	 * @return
	 */
	public List<OrderItem> getOrderItems(String orderId);
	
	/**
	 * 保存某个订单项
	 * @return
	 */
	public int saveOrderItem(OrderItem item);
	
	public int saveBatch(List<OrderItem> item);
	
}
