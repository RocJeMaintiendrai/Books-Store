package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;

/**
 * 图书业务逻辑
 * @author lfy
 *
 */
public interface BookService {
	/**
	 * 添加图书
	 * @param book   要添加的图书
	 * @return  true添加成功
	 */
	public boolean add(Book book);
	
	/**
	 * 修改图书
	 * @param book  要修改的图书   按照id 修改其他属性   
	 * @return true修改成功
	 */
	public boolean update(Book book);
	
	/**
	 * 删除图书
	 * @param book  要删除的图书  按照id删
	 * @return
	 */
	public boolean delete(Book book);
	
	/**
	 * 获取一本图书
	 * @param book 要查找的图书  id
	 * @return  返回Book对象
	 */
	public Book  getOne(Book book);
	
	/**
	 * 查询出所有图书
	 * @return  返回的是一个List<Book>
	 */
	public List<Book> getAll();
	
	/**
	 * 返回分页数据
	 * @return
	 */
	public Page<Book> getPage(String pageNo,String pageSize);
	
	public Page<Book> getPageByPrice(String pageNo, String pageSize,String maxPrice,String minPrice);
	
}

