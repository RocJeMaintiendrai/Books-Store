package com.atguigu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

/**
 * Servlet implementation class BookClientServlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
     BookService bookService = new BookServiceImpl();
  
	/**
	 * 给用户展示图书的分页数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("我是前端的分页方法。。。");
		// TODO Auto-generated method stub
		//用户点击图书管理显示部分数据，页码应该是用户传进的
		String pn = request.getParameter("pn");
		Page<Book> page = bookService.getPage(pn, "4");
		page.setUrl("client/BookClientServlet?method=page");
		//将第一页的数据放到页面显示
		request.setAttribute("page", page);
		// 交给页面
		request.getRequestDispatcher("/pages/book/booklist.jsp")
				.forward(request, response);
	}
	
	protected void pageByPrice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取价格区间
		String max = request.getParameter("max");
		String min = request.getParameter("min");
		String pn = request.getParameter("pn");
		System.out.println("按价格查询方法:min->"+min+"---Max:"+max);
		
		//查询价格区间的所有图书
		Page<Book> page = bookService.getPageByPrice(pn, "4", max, min);
		page.setUrl("client/BookClientServlet?method=pageByPrice&max="+max+"&min="+min);
		//返回页面显示
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);
	}

}
