package com.atguigu.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.code.kaptcha.Constants;

/**
 * 处理所有和用户有关系的请求 Servlet implementation class UserServlet
 * 抽取出BaseServlet以后
 * UserServlet里面只需要编写相应的处理逻辑即可
 */
public class UserServlet extends BaseServlet {

	private UserService us = new UserServiceImpl();
	private static final long serialVersionUID = 1L;
	//方法名(request,response)
	
	protected void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//编写修改用户密码的方法
	}
	
	/**
	 * 用户注册方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void regist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");*/
		//获取用户输入的验证码，获取session中的验证码
		//页面的验证码
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		System.out.println("页面的验证码："+code);
		System.out.println("session中的验证码："+sessionCode);
		//如果验证码一致则注册，否则回到注册页面并提示验证码错误
		if(!sessionCode.equals(code)){
			//验证码错误
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
			return;
		}
		
		User user = WebUtils.param2bean(request, new User());
		// 用户注册
		boolean b = us.regist(user);
		if (b) {
			// 注册成功 返回成功页面 重定向
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/regist_success.jsp");

		} else {
			// 注册失败 返回注册页面，重新注册 转发
			request.setAttribute("msg", "用户已存在");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(
					request, response);
		}
	}

	/**
	 * 用户登陆
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user2 = WebUtils.param2bean2(request, new User());
		//用户登陆，用户的详情
		User user = us.login(user2);
		System.out.println(user);
		//将用户保存到session中
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if (user == null) {
			// 登陆失败 返回登陆页面即可 转发
			request.setAttribute("msg", "用户名密码错误");
			// 也可以设置上要回显的项目。request.setAttribute("msg", "用户名密码错误");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(
					request, response);

		} else {
			// 登陆成功 返回成功页面 重定向
			response.sendRedirect(request.getContextPath()
					+ "/pages/user/login_success.jsp");

		}
	}
	
	/**
	 * 用户登出操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//销毁session即可
		session.invalidate();
		//点击登出以后返回商城首页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	
	/**
	 * 检查用户是否可用
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkuser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//页面应该把用户名传过来  key应该是username
		//页面传来的user对象
		User user = WebUtils.param2bean2(request, new User());
		
		//检查用户是否可用
		boolean b = us.checkuser(user);
		
		if(b){
			//可以注册,写数据就是给客户端响应
			response.getWriter().write("用户名可用");
		}else{
			//不可以注册
			response.getWriter().write("用户已存在");
		}
	
	}
	
	

}
