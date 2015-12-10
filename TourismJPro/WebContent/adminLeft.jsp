<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>旅游服务系统 - 管理员菜单栏</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="menu">
		<dl>
			<dt>旅游信息管理</dt>
			<dd>
				<a href="ToAddTourismServlet" target="main"> 添加旅游信息 </a>
			</dd>
			<dd>
				<a href="ToTourismListServlet" target="main"> 查询旅游信息 </a>
			</dd>  
		</dl>
		<dl>
			<dt>订单管理</dt>
			<dd>
				<a href="GetUserOrderListServlet" target="main"> 受理订单 </a>
			</dd> 
		</dl>
		<dl>
			<dt>评论管理</dt>
			<dd>
				<a href="ManagerUserCommentServlet" target="main"> 评论列表 </a>
			</dd> 
		</dl>
		<dl>
			<dt>用户管理</dt>
			<dd> 
				<a href="GetUserListServlet" target="main"> 用户列表 </a>
			</dd> 
		</dl>
	</div>
</body>

</html>