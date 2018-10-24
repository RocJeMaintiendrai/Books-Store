package com.atguigu.test;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.User;
import com.atguigu.service.BookService;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.service.impl.OrderServiceImpl;

public class OrderServiceTest {
	BookService bookService = new BookServiceImpl();
	OrderService orderService = new OrderServiceImpl();
	
	
	@Test
	public void test(){
		Book book = new Book();
		book.setId(7);
		Book one = bookService.getOne(book);
		Cart cart = new Cart();
		/*cart.a*/
		cart.addBook2Cart(one);
		cart.addBook2Cart(one);
		
		String orderid = orderService.checkout(cart, new User(1, "", "", ""));
		System.out.println(orderid);
		
	}

}
