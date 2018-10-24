package com.atguigu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.bean.Order;
import com.atguigu.bean.OrderItem;
import com.atguigu.bean.User;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderItemService;
import com.atguigu.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDao orderDao = new OrderDaoImpl();
	OrderItemService itemService = new OrderItemServiceImpl();
	BookService bookService = new BookServiceImpl();
	@Override
	public void updateStatus(String orderid, String status) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setOrderId(orderid);
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(status);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.setStatus(parseInt);
		orderDao.updateStatus(order);
	}

	@Override
	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		return orderDao.getOrderList();
	}

	@Override
	public List<Order> getMyOrders(Integer userId) {
		// TODO Auto-generated method stub

		return orderDao.getOrderByUserId(userId);
	}

	@Override
	public String checkout(Cart cart, User user) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 结账操作，把购物车里面的数据封装并保存
		// 1、封装订单对象
		// orderId需要使用算法生成
		long id = Thread.currentThread().getId();
		System.out.println("service中的线程号："+id);
		long millis = System.currentTimeMillis();
		// 生成订单号
		String orderId = millis + "" + user.getId();
		Order order = new Order();
		order.setCreateDate(new Date());
		order.setOrderId(orderId);
		order.setTotalMoney(cart.getTotalMoney());
		order.setStatus(0);
		order.setUserId(user.getId());
		// 312312872374274342

		// order.setOrderId(orderId);
		// 2、封装订单项对象
		List<CartItem> allItems = cart.getAllItems();
		//保存所有的订单项
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem cartItem : allItems) {
			//封装一个订单项
			OrderItem item = new OrderItem(cartItem.getBook().getTitle(),
					cartItem.getCount(), cartItem.getBook().getPrice(), 
					cartItem.getTotalPrice(), orderId);
			orderItems.add(item);
		}
		// 3、保存订单
		orderDao.saveOrder(order);
		
		// 4、保存所有订单项
		itemService.saveItem(orderItems);
		
		// 5、修改相应库存，改图书，改每一项
		for (CartItem cartItem : allItems) {
			//bookService.update(book)
			//获取详细信息,图书的信息应该从数据库得到
			Book book = cartItem.getBook();
			Book one = bookService.getOne(book);
			//修改库存和销量
			int count = cartItem.getCount();
			one.setStock(one.getStock()-count);
			one.setSales(one.getSales()+count);
			//更新信息
			bookService.update(one);
		}
		//6、清空购物车
		cart.clear();
		//相当于在整个方法没有异常再关连接
		
		return orderId;
	}

}
