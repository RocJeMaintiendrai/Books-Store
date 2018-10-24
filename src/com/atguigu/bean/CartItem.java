package com.atguigu.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 每个购物项
 * @author lfy
 *
 */
public class CartItem implements Serializable{
	//代表当前买的是哪本图书
	private Book book;
	//代表购买的数量
	private int count;
	//代表总金额
	private double totalPrice;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * 计算总金额
	 * @return
	 */
	public double getTotalPrice() {
		//将获取到的价格使用BigDecimal包装
		BigDecimal price = new BigDecimal(getBook().getPrice()+"");
		BigDecimal count = new BigDecimal(getCount()+"");
		BigDecimal multiply = price.multiply(count);
		//使用BigDecimal.doubleValue将数据转为double类型
		return multiply.doubleValue();
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public CartItem(Book book, int count, double totalPrice) {
		super();
		this.book = book;
		this.count = count;
		this.totalPrice = totalPrice;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", totalPrice="
				+ totalPrice + "]";
	}
	
	
	
}
