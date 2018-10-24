package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements
		OrderItemDao {

	@Override
	public List<OrderItem> getOrderItems(String orderId) {
		// TODO Auto-generated method stub
		//根据订单号,获取所有的订单项
		String sql = "select id,title,count,price,total_price totalPrice,"
				+ "order_id orderId"
				+ "from bs_order_item where order_id=?";
		return getBeanList(sql, orderId);
	}

	@Override
	public int saveOrderItem(OrderItem item) {
		// TODO Auto-generated method stub
		String sql = "insert into bs_order_item(title,count,price,total_price,order_id) "
				+ "values(?,?,?,?,?)";
		return update(sql, item.getTitle(),item.getCount(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
	}
	
	/**
	 * 执行批量保存
	 * @param params
	 * @return
	 */
	@Override
	public int saveBatch(List<OrderItem> params){
		String sql="insert into bs_order_item(title,count,price,"
				+ "total_price,order_id) "
				+ "values(?,?,?,?,?)";
		
		Object[][] objs = new Object[params.size()][5];
		//objs[0] = new Object[]{};
		int count = 0;
		for (OrderItem item : params) {
			objs[count++] = new Object[]{item.getTitle(),
					item.getCount(),item.getPrice(),
					item.getTotalPrice(),item.getOrderId()};
			
		}
		batch(sql, objs);
		return 1;
	}

}
