package com.atguigu.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

public class BookDaoTest {
	BookDao bd = new BookDaoImpl();
	
	/**
	 * 获取所有图书
	 */
	@Test
	public void test(){
		List<Book> list = bd.getAllBook();
		System.out.println(list);
	}
	
	/**
	 * 添加图书
	 */
	@Test
	public void test2(){
		//创建一个图书
		Book book = new Book(null, "java入门2", "雷枫叶", 50.12, 0, 200, null);
		System.out.println(book);
		boolean b = bd.addBook(book);
		System.out.println(b);
	}
	
	@Test
	public void test3(){
		Book book = new Book();
		book.setId(2);
		boolean b = bd.delBook(book);
		System.out.println(b);
	}
	
	@Test
	public void test4(){
		Book book = new Book(3, "java入门4", "雷枫", 50.12, 0, 200, null);
		boolean b = bd.updateBook(book);
		System.out.println(b);
		
	}
	
	//获取一本图书
	@Test
	public void test5(){
		Book b = new Book();
		b.setId(4);
		Book book = bd.getBook(b);
		System.out.println(book);
	}
	
	@Test
	public void test6(){
		BookService bs = new BookServiceImpl();
		Page<Book> page = bs.getPage("0", "4");
		System.out.println(page.getPageData());
		System.out.println(page.getPageNo());
		System.out.println(page.getPageSize());
		System.out.println(page.getTotalCount());
		System.out.println(page.getTotalPage());
	}
	
	@Test
	public void test7(){
		BookService bs = new BookServiceImpl();
		Page<Book> page = bs.getPageByPrice("1", "4","50", "0");
		System.out.println(page.getPageData());
		System.out.println(page.getPageNo());
		System.out.println(page.getPageSize());
		System.out.println(page.getTotalCount());
		System.out.println(page.getTotalPage());
	}

	
}
