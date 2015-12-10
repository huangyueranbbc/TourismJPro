<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理中心 >> 用户信息列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/listStyle.css" type="text/css" rel="stylesheet" />

</head> 

<body>

	<div class="listStyle">
		<div class="title">
			<span> 管理中心 >> 用户信息列表 </span>
		</div>

 
		<table  style="text-align:center;">
			<tr>
				<th width="15%">用户名</th>
				<th width="10%">用户手机号</th>
				<th width="17%">用户邮箱</th>
				<th width="8%">是否冻结</th>
				<th width="8%">是否删除</th>
				<th width="20%">管理员操作</th>  
			</tr>
			<c:forEach var="userList" items="${pageModel.list}">
				<tr>
					<td>${userList.name}</td>
					<td>${userList.phone}</td>
					<td>${userList.email}</td>
 					<td>
					<c:if test="${userList.status == 0}">
						未冻结
					</c:if>
					<c:if test="${userList.status == 1}">
						已冻结
					</c:if> 
					</td> 
					
					<td>100</td> 
					
					<td>  
					<c:if test="${userList.status == 0}">
						<a href="ManagerUserServlet?userStatus=1&userId=${userList.ID}">冻结</a>
						<a><font color=#A3A3A3>解冻</font></a> 
					</c:if>
					<c:if test="${userList.status == 1}"> 
						<a><font color=#A3A3A3>冻结</font></a>
						<a href="ManagerUserServlet?userStatus=0&userId=${userList.ID}">解冻</a> 
					</c:if> 
					</td>
					
				</tr> 


			</c:forEach>
			<tr>
				<td colspan="9">&nbsp;</td>
			</tr>

			<tr>
				<td colspan="9" class="pageinfo" align="right">
					<div> 
						现在是第 ${pageModel.page}页, 共 ${pageModel.totalPage}页 ${pageModel.totalCount}条记录&nbsp;&nbsp; <a
							href="GetUserListServlet?page=1&size=${pageModel.size}">首页</a> <a
							href="GetUserListServlet?page=${pageModel.pre}&size=${pageModel.size}">上一页</a>
						<a
							href="GetUserListServlet?page=${pageModel.next}&size=${pageModel.size}">下一页</a>
						<a
							href="GetUserListServlet?page=${pageModel.totalPage}&size=${pageModel.size}">末页</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>