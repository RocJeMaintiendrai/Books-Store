package com.atguigu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bean.Cart;
import com.atguigu.bean.Constants;
import com.atguigu.bean.Order;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;

/**
 * Servlet implementation class OrderClientServlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderServiceImpl();
	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long id = Thread.currentThread().getId();
		System.out.println("servelt中的线程号："+id);
		HttpSession session = request.getSession();
		Cart cart = WebUtils.getCart(request);
		User loginUser = WebUtils.getLoginUser(request);
		
		//结算，返回订单号
		String orderid;
		orderid = orderService.checkout(cart, loginUser);
		session.setAttribute("orderId", orderid);
		response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
		
	}
	
	/**
	 * 列出当前用户的所有订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = WebUtils.getLoginUser(request);
		List<Order> list = null;
		try {
			list = orderService.getMyOrders(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//域中保存了该用户的所有订单
		request.setAttribute("orders",list);
		
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
		
	}
	
	//确认收货
	protected void received(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderid = request.getParameter("orderid");
		//确认收货
		orderService.updateStatus(orderid, Constants.DELIVERED+"");
		
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}

}
