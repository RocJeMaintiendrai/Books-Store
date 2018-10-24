package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.Book;

public interface BookDao {
	/**
	 * 获取所有图书
	 * @return 返回图书的List
	 */
	public List<Book> getAllBook();
	
	/**
	 * 添加一本图书
	 * @param book  传入要添加的图书
	 * @return  true代表添加成功
	 */
	public boolean addBook(Book book);
	
	/**
	 * 删除一本图书
	 * @param book  要删除的图书，id
	 * @return
	 */
	public boolean delBook(Book book);
	
	/**
	 * 修改一本图书
	 * @param book  要修改的图书,book是修改后的样子
	 * @return
	 */
	public boolean updateBook(Book book);
	
	/**
	 * 根据图书的id查找图书
	 * @param book  包含图书id
	 * @return
	 */
	public Book getBook(Book book);
	
	/**
	 * 分页查找图书的方法
	 * @param index
	 * @param size
	 * @return
	 */
	public List<Book> getPageList(int index,int size);
	
	
	/**
	 * 获取所有图书的总记录数
	 * @return
	 */
	public int getTotalCount();
	
	
	public int getTotalCountByPrice(double minPrice,double maxPrice);
	
	
	
	/**
	 * 按照价格查找图书
	 * @param index
	 * @param size
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	public List<Book> getPageByPrice(int index, int size,double minPrice,double maxPrice);
	
	public void updateStockAndSales(Integer bookid,Integer stock,Integer sales);
}
