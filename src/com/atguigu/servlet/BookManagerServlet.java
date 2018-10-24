package com.atguigu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.Book;
import com.atguigu.bean.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {

	private BookService bookService = new BookServiceImpl();

	/**
	 * 显示分页数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//用户点击图书管理显示部分数据，页码应该是用户传进的
		String pn = request.getParameter("pn");
		Page<Book> page = bookService.getPage(pn, "4");
		page.setUrl("manager/BookManagerServlet?method=page");
		//将第一页的数据放到页面显示
		request.setAttribute("page", page);
		// 交给页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp")
				.forward(request, response);
	}
	/**
	 * 显示图书列表 现在没用了，我们要用page方法显示部分数据
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查出所有图书数据并显示
		List<Book> list = bookService.getAll();
		// 图书查出以后交给页面显示book_manager.jsp
		request.setAttribute("list", list);
		// 交给页面
		request.getRequestDispatcher("/pages/manager/book_manager.jsp")
				.forward(request, response);

	}
	
	/**
	 * 图书添加-->所有的都在update里面处理了
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("这里是图书添加");
		//在还未获取数据之前设置编码
		//1、将提交的图书信息封装为book对象,表单的name应该和对象的属性一一对应
		Book book = WebUtils.param2bean2(request, new Book());
		//2、将图书保存到数据库
		boolean b = bookService.add(book);
		//3、保存成功,重回列表页面，列表显示  http://localhost:8080/BookStore05/manager/BookManagerServlet?method=list
		//解耦，
		response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=page");
		
	}
	
	/**
	 * 图书删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pn = request.getParameter("pn");
		System.out.println("当前停留的页码。。。"+pn);
		//referer代表我从哪里来
		String string = request.getHeader("Referer");
		System.out.println(string);
		//封装要删除的book
		Book book = WebUtils.param2bean2(request, new Book());
		//调用删除方法
		bookService.delete(book);
		//回到列表显示
		response.sendRedirect(string);
	}
	
	/**
	 * 查出某本图书的详细信息，显示到页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//查出某本图书按照id ，
		System.out.println("页码："+request.getParameter("pn"));
		Book book = WebUtils.param2bean2(request, new Book());
		System.out.println(book);
		//获取详细信息
		Book one = bookService.getOne(book);
		//回到编辑页面进行显示
		request.setAttribute("book", one);
		//转发到页面进行显示
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
	
	/**
	 * 图书修改
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("这里都是图书修改");
		//代表的是上一次请求
		String header = request.getHeader("referer");
		System.out.println(header);
		String pn = request.getParameter("pn");
		//封装修改的图书信息
		Book book = WebUtils.param2bean2(request, new Book());
		System.out.println(book);
		//由于添加和修改操作，封装出的book，id有差别，所以可以通过id就直接断定是否为添加或者修改操作
		if(book.getId()==0){
			//添加图书
			bookService.add(book);
		}else{
			//修改图书
			bookService.update(book);
		}
		//返回列表页面
		response.sendRedirect(request.getContextPath()+"/manager/BookManagerServlet?method=page&pn="+pn);
	}

}
