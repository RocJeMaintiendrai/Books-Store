package com.atguigu.service.impl;

import java.util.List;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.service.BookService;

/**
 * 图书业务逻辑实现
 * 
 * @author lfy
 * 
 */
public class BookServiceImpl implements BookService {

	private BookDao bd = new BookDaoImpl();

	// 添加图书
	@Override
	public boolean add(Book book) {
		return bd.addBook(book);
	}

	// 修改图书
	@Override
	public boolean update(Book book) {
		long id = Thread.currentThread().getId();
		System.out.println("bookDao中的线程号："+id);
		return bd.updateBook(book);
	}

	// 删除图书
	@Override
	public boolean delete(Book book) {
		return bd.delBook(book);
	}

	// 查找一本图书
	@Override
	public Book getOne(Book book) {
		return bd.getBook(book);
	}

	// 查找所有图书
	@Override
	public List<Book> getAll() {
		return bd.getAllBook();
	}

	/**
	 * 获取分页数据
	 */
	@Override
	public Page<Book> getPage(String pageNo, String pageSize) {
		// TODO Auto-generated method stub
		// 1、将用户传入的数据先封装部分
		Page<Book> page = new Page<Book>();
		// 将用户传入的数据转型并封装，设置默认值
		int pn = 1;
		int ps = page.getPageSize();
		try {
			pn = Integer.parseInt(pageNo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			ps = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// 2、因为要使用totalCount也即是当前总记录数，所以还需要查数据库
		// 先要设置页面大小
		page.setPageSize(ps);
		int totalCount = bd.getTotalCount();// 获取总记录数
		// 再设置总记录数
		page.setTotalCount(totalCount);
		// 这样就可以算出总页面 getTotalPage();
		page.setPageNo(pn);
		// 3、查询数据并封装
		List<Book> list = bd.getPageList(page.getIndex(), page.getPageSize());
		page.setPageData(list);
		return page;
	}

	/**
	 * 获取分页数据
	 */
	public Page<Book> getPageByPrice(String pageNo, String pageSize,
			String maxPrice, String minPrice) {
		// TODO Auto-generated method stub
		double min = 0.0;
		double max = Double.MAX_VALUE;
		try {
			min = Double.parseDouble(minPrice);
			max = Double.parseDouble(maxPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 1、将用户传入的数据先封装部分
		Page<Book> page = new Page<Book>();
		// 将用户传入的数据转型并封装，设置默认值
		int pn = 1;
		int ps = page.getPageSize();
		try {
			pn = Integer.parseInt(pageNo);
			ps = Integer.parseInt(pageSize);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		// 2、将页码以及页面大小设置进入page对象
		// 按照价格区间获取总记录数
		int count = bd.getTotalCountByPrice(min, max);
		System.out.println("min-->"+min+"max-->"+max);
		page.setTotalCount(count);
		page.setPageSize(ps);
		
		//这是最后设置
		page.setPageNo(pn);

		//3、查询相应数据
		List<Book> list = bd.getPageByPrice(page.getIndex(), page.getPageSize(), min, max);
		
		//4、封装进page对象
		page.setPageData(list);

		return page;
	}
}
