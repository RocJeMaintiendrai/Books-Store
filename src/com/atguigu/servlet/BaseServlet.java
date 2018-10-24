package com.atguigu.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/*
		 * if(登陆请求){ 调用用户登陆 }else if(注册请求){ 调用用户注册 }else if(修改密码){ 调用修改密码 }
		 */
		//在这里解决乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		System.out.println(method);
		// getDeclaredMethod(方法名，参数列表)
		try {
			Method method2 = this.getClass().getDeclaredMethod(method,
					HttpServletRequest.class, HttpServletResponse.class);
			//把方法权限设大
			method2.setAccessible(true);
			//invoke(对象，参数);
			method2.invoke(this, request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("BaseServlet异常...");
		}
		/*
		 * if("regist".equals(method)){ //认为当前是注册请求 regist(request,response); }
		 * if("login".equals(method)){ //认为是登陆请求 login(request, response); }
		 */
	}

}
