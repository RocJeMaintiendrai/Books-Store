<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--用户未登陆显示这个菜单  -->
<c:if test="${empty user}">
<!--用户未登陆  -->
<div>
	<a href="pages/user/login.jsp">登录</a> | <a
		href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp; <a
		href="pages/cart/cart.jsp">购物车</a> <a
		href="pages/manager/manager.jsp">后台管理</a>
</div>
</c:if>
<c:if test="${!empty user}">
<!--用户已登陆  -->
<!-- 用户登陆成功显示下面这个 -->
<div>
	<span>欢迎<span class="um_span">${user.username }</span>光临尚硅谷书城
	</span> 
	<a href="pages/cart/cart.jsp">购物车</a>
	<a href="client/OrderClientServlet?method=list">我的订单</a> 
	<a href="UserServlet?method=logout">注销</a>&nbsp;&nbsp;
	<a href="index.jsp">返回</a>
</div>
</c:if>



