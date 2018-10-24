package com.atguigu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.atguigu.bean.User;
import com.atguigu.utils.WebUtils;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter已经启动");
		//验证用户是否登陆，如果登陆放行，否则转到登陆页面
		HttpServletRequest req = (HttpServletRequest) request;
		//获取已经登陆的用户
		User user = WebUtils.getLoginUser(req);
		if(user==null){
			//未登录
			request.setAttribute("msg", "此操作需要登陆，请先登陆");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//已登录
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
