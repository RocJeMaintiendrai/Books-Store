package com.atguigu.service.impl;

import java.util.List;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

	OrderItemDao itemDao = new OrderItemDaoImpl();


	@Override
	public List<OrderItem> getOrderItems(String orderid) {
		// TODO Auto-generated method stub
		return itemDao.getOrderItems(orderid);
	}


	@Override
	public void saveItem(List<OrderItem> orderItem) {
		// TODO Auto-generated method stub
//		for (OrderItem orderItem2 : orderItem) {
//			itemDao.saveOrderItem(orderItem2);
//		}
		long id = Thread.currentThread().getId();
		System.out.println("orderItemDao中的线程号："+id);
		itemDao.saveBatch(orderItem);
	}

}
