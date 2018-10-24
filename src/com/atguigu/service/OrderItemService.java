package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OrderItem;

public interface OrderItemService {
	
	/**
	 * 保存订单项
	 * @param orderItem
	 */
	public void saveItem(List<OrderItem> orderItem);
	
	/**
	 * 获取订单的所有订单项
	 * @param orderid
	 * @return
	 */
	public List<OrderItem> getOrderItems(String orderid);

}
