package com.atguigu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService us = new UserServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = us.login(new User(null, username, password, null));
		if (user == null) {
			// 登陆失败 返回登陆页面即可 转发
			request.setAttribute("msg", "用户名密码错误");
			//也可以设置上要回显的项目。request.setAttribute("msg", "用户名密码错误");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);
			
		} else {
			// 登陆成功 返回成功页面 重定向
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/login_success.jsp");

		}
	}
}
