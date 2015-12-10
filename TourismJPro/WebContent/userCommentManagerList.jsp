<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理中心 >> 用户评论列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/listStyle.css" type="text/css" rel="stylesheet" />

</head>

<body>
	<div class="listStyle">
		<div class="title">
			<span> 管理中心 >> 用户评论列表 </span>
		</div>


		<table style="text-align: center;">
			<tr>
				<th width="8%">用户名</th>
				<th width="6%">满意度</th>
				<th width="30%">评论内容</th>
				<th width="10%">评论时间</th>
				<th width="10%">管理员操作</th>
			</tr>
			<c:forEach var="comment" items="${pageModel.list}">
				<tr>
					<td>${comment.userName}</td>
					<td><c:if test="${comment.satisfaction == 3}">很满意</c:if> <c:if
							test="${comment.satisfaction == 2}">一般</c:if> <c:if
							test="${comment.satisfaction == 0}">不满意</c:if></td>
					<td>${comment.content}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
							value="${comment.createDate}" type="both" /></td>
							<td><a href="RemoveCommentServlet?commentId=${comment.ID}">删除</a></td>
				</tr> 
  
			</c:forEach>


			<tr>
				<td colspan="9" class="pageinfo" align="right">
					<div>
						现在是第 ${pageModel.page} 页，共 ${pageModel.totalPage} 页
						${pageModel.totalCount} 条记录 &nbsp;&nbsp; <a
							href="GetCommentList?page=1&size=${pageModel.size}">首页</a> <a
							href="GetCommentList?page=${pageModel.pre}&size=${pageModel.size}">上一页</a>
						<a
							href="GetCommentList?page=${pageModel.next}&size=${pageModel.size}">下一页</a>
						<a
							href="GetCommentList?page=${pageModel.totalPage}&size=${pageModel.size}">末页</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>