package com.atguigu.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;

public class CartTest {
	
	Book book1 = new Book(1, "西游记1", "", 0.06, 100, 100, "");
	Book book2 = new Book(2, "西游记2", "", 0.01, 100, 100, "");
	Book book3 = new Book(3, "西游记3", "", 10.6, 100, 100, "");
	Book book4 = new Book(4, "西游记4", "", 11.5, 100, 100, "");
	Book book5 = new Book(5, "西游记5", "", 16.8, 100, 100, "");
	
	@Test
	public void test1(){
		
		Cart cart = new Cart();
		/*cart.a*/
		cart.addBook2Cart(book1);
		cart.addBook2Cart(book2);
		
		
		System.out.println("当前总计"+cart.getTotalCount()+"本书");
		System.out.println("当前总价"+cart.getTotalMoney());
		System.out.println("当前购物车中的项目："+cart.getAllItems());
		
		
		
/*		System.out.println("删除之后。。。。。");
 * 		cart.deleteItem(book1.getId().toString());
		System.out.println("当前总计"+cart.getTotalCount()+"本书");
		System.out.println("当前总价"+cart.getTotalMoney());
		System.out.println("当前购物车中的项目："+cart.getAllItems());*/
		
	/*	System.out.println("修改之后.....");
		cart.updateCount(book2.getId().toString(), 3);
		System.out.println("当前总计"+cart.getTotalCount()+"本书");
		System.out.println("当前总价"+cart.getTotalMoney());
		System.out.println("当前购物车中的项目："+cart.getAllItems());*/
		
	}
	
	@Test
	public void test2(){
		//大整数运算问题
		int i = 1;
		for (int j = 1; j < 21; j++) {
			i*=j;
		}
		System.out.println(i);
		
		//浮点数运算问题
		double a = 0.01,b=0.06;
		System.out.println(a+b);
		//解决运算精度问题BigDecimal,解决大整数运算
		//只需要将需要运算的数字使用BigDecimal，利用BigDecimal提供的运算进行计算
		BigDecimal bigDecimal = new BigDecimal(1);
		for (int j = 1; j < 1000; j++) {
			bigDecimal = bigDecimal.multiply(new BigDecimal(j));
		}
		System.out.println(bigDecimal);
		
		
		/*BigDecimal bigDecimal2 = new BigDecimal(a);
		BigDecimal bigDecimal3 = new BigDecimal(b);
		System.out.println(bigDecimal2.add(bigDecimal3));
		浮点数需要使用String类型的构造器
		*/
		BigDecimal bigDecimal2 = new BigDecimal(a+"");
		BigDecimal bigDecimal3 = new BigDecimal(b+"");
		System.out.println(bigDecimal2.add(bigDecimal3));
		
	}

}
