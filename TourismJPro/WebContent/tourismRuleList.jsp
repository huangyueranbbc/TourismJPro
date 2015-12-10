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
<title>管理中心 >> 查看详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/listStyle.css" type="text/css" rel="stylesheet" />

</head>

<body>

	<div class="listStyle">
		<div class="title">
			<span> 管理中心 >>查看详情 </span>
		</div>
		<div class="information">
			<div class="subtitle">
				<span> 旅游规则 </span>
			</div>
		</div>
		<table style="position: absolute; top: 80px;">
			<tr>
				<th width="20%">出游时间</th>
				<th width="20%">报名截止时间</th>
				<th width="20%">价格</th>
				<th width="20%">优惠</th>
				<th width="20%">备注</th>

			</tr>

			<c:forEach var="tourismRules" items="${tourismRules	}">
				<tr>
					<td>${tourismRules.tourismTime }</td>
					<td>${tourismRules.deadline }</td>
					<td>${tourismRules.price }<font color="red">￥</font>

					</td>
					<td>${tourismRules.discount }</td>
					<td>${tourismRules.remark }</td>
				</tr>

			</c:forEach>

		</table>


		<!-- 详细描述 -->
		<div class="information" style="position: absolute; top: 220px;">
			<div class="subtitle">
				<span> 旅 游 说 明</span>
			</div>
			<div class="detail">${tourism.description }</div>
		</div>
	</div>

</body>
</html>