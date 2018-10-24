package com.atguigu.dao.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.BookDao;

/**
 * 操作图书的Dao
 * Navicat for Mysql
 * @author lfy
 * 
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	// 获取所有图书
	@Override
	public List<Book> getAllBook() {
		// img_path
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book";
		// 返回所有的图书
		return getBeanList(sql);
	}

	// 添加图书
	@Override
	public boolean addBook(Book book) {
		String sql = "insert into bs_book(title,author,price,sales,"
				+ "stock,img_path) values(?,?,?,?,?,?)";
		int update = update(sql, book.getTitle(), book.getAuthor(),
				book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath());

		return update > 0;
	}

	// 删除图书
	@Override
	public boolean delBook(Book book) {
		String sql = "delete from bs_book where id=?";
		int update = update(sql, book.getId());
		return update > 0;
	}

	// 修改图书
	@Override
	public boolean updateBook(Book book) {
		/**
		 * String sql = "insert into bs_book(title,author,price,sales," +
		 * "stock,img_path) values(?,?,?,?,?,?)";
		 */
		String sql = "update bs_book set title=?,author=?,price=?,sales=?"
				+ ",stock=?,img_path=? where id=?";
		// book 修改后的样子
		int update = update(sql, book.getTitle(), book.getAuthor(),
				book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath(), book.getId());
		return update > 0;
	}

	//获取一本图书
	@Override
	public Book getBook(Book book) {
		String sql = "select id,title,author,price,sales,stock,"
				+ "img_path imgPath from bs_book where id=?";
		return getBean(sql, book.getId());
	}

	@Override
	public List<Book> getPageList(int index, int size) {
		// TODO Auto-generated method stub
		String sql = "select id,title,author,price,sales,stock,"
				+ "img_path imgPath from bs_book limit ?,?";
		return getBeanList(sql, index,size);
	}
	
	/***
	 * 根据图书价格查询图书
	 * @param index
	 * @param size
	 * @param minPrice  最小价格
	 * @param maxPrice	最大价格
	 * @return
	 */
	public List<Book> getPageByPrice(int index, int size,double minPrice,double maxPrice) {
		// TODO Auto-generated method stub
		String sql = "select id,title,author,price,sales,stock,"
				+ "img_path imgPath from bs_book where price between ? and ? limit ?,?";
		return getBeanList(sql,minPrice,maxPrice, index,size);
	}

	@Override
	public int getTotalCount() {
		String sql ="select count(*) from bs_book";
		Object object = getSingleValue(sql);
		int parseInt = 0;
		try {
			parseInt = Integer.parseInt(object.toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return parseInt;
	}

	/**
	 * 根据图书价格查找出相应的记录数
	 */
	@Override
	public int getTotalCountByPrice(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from bs_book where price between ? and ?";
		Object object = getSingleValue(sql, minPrice,maxPrice);
		int i = 0;
		try {
			i = Integer.parseInt(object.toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public void updateStockAndSales(Integer bookid, Integer stock, Integer sales) {
		// TODO Auto-generated method stub
		
	}
	
	

}
