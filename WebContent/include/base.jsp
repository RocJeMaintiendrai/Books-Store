<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 所有页面要引入的资源
base标签的链接需要时动态获取的，而不是指定的
http://localhost:8080//BookStore02/
协议：//主机名：端口号/项目路径/
 -->

<base href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>