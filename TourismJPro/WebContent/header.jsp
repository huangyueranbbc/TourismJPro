<%@page import="com.ruanko.model.UserBo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>旅游服务网 - 管理中心页头</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<!-- header start -->
	<div class="header">
		<div class="logo">
			<a href="index.htm" target="_top"> </a>
		</div>
		<div class="toplinks">
			您好：
			<%
			UserBo user = (UserBo) session.getAttribute("userBo");
			out.println(user.getName());%>
		，欢迎使用旅游服务网！ [<a href="LoginoutServlet" target="_top">注销登录</a>]</span>
		</div>
	</div>
	<!-- header end -->
</body>
</html>