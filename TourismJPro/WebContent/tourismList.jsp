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
<title>管理中心 - 商品列表页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/listStyle.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	function _confirm() {
		if (confirm("确定要拒绝吗?")) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>

<body>

	<div class="listStyle">
		<div class="title">
			<span> 管理中心 >> 旅游线路列表 </span>
		</div>
		<div id="search">
			<form action="#" method="post">
				<div>
					<input class="input" type="text" name="searchName"
						value="输入商品名称或关键字进行搜索" /> <input class="button" type="submit"
						value="搜索" />
				</div>
			</form>
		</div>

		<table>
			<tr>
				<th width="4%">景点</th>
				<th width="10%">类型</th>
				<th width="12%">图片</th>
				<th width="8%">旅游天数</th>
				<th width="8%">基本价格</th>
				<th width="7%">人数下限</th>
				<th width="7%">人数上限</th>
				<th width="20%">说明</th>
				<th width="10%">出发城市</th>

				<th width="10%">操作</th>
			</tr>
			<c:forEach var="touristList" items="${pageModel.list}">
				<tr>
					<td>${touristList.tourism.title }</td>
					<td><c:if test="${touristList.tourism.type==0}">自助游</c:if> <c:if
							test="${touristList.tourism.type==1}">跟团游</c:if></td>
					<td><img class="unit-pic" style="width:227px;height:151px;" src="<%=basePath %>${touristList.tourismImages[0].big}" /></td>
					<td style="text-align: left;">${touristList.tourism.days }</td> 
					<td>${touristList.tourism.basePrice }</td>
					<td>${touristList.tourism.minNumber }</td>
					<td>${touristList.tourism.maxNumber }</td>
					<td>${touristList.tourism.description }</td>
					<td>${touristList.tourism.city }</td>
					<td><a href="ToGetTourismRuleListServlet?tourism_id=${touristList.tourism.ID}">查看规则 </a></td>
				</tr>
			</c:forEach>

			<tr>
				<td colspan="10"></td>
			</tr>

			<tr>
				<td colspan="10" class="pageinfo" align="right">
					<div>
						现在是第 ${pageModel.page}页 , 共 ${pageModel.totalPage}页
						${pageModel.totalCount}条记录&nbsp;&nbsp; <a
							href="GetTourismListServlet?page=1&size=${pageModel.size}">首页</a>
						<a
							href="GetTourismListServlet?page=${pageModel.pre}&size=${pageModel.size}">上一页</a>
						<a
							href="GetTourismListServlet?page=${pageModel.next}&size=${pageModel.size}">下一页</a>
						<a
							href="GetTourismListServlet?page=${pageModel.totalPage}&size=${pageModel.size}">末页</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>