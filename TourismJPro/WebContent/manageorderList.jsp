<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>管理中心 - 受理订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/trackOrder.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<div class="trackOrder">
			<div class="title">
				<span> 管理中心 - 受理订单 </span>
			</div>


			<table style="text-align: center;">
				<tr>

					<th width="16%">订单号</th>
					<th width="25%">旅游线路</th>

					<th width="8%">订单金额</th>
					<th width="26%">下单时间</th>
					<th width="8%">订单状态</th>
					<th>操作</th>
				</tr>

				<!-- 订单显示列表开始 -->
				<c:forEach var="orderInfo" items="${pageModel.list}">
					<tr>
						<td><a href="#"> ${orderInfo.order.orderId } </a></td>
						<td style="text-align: left;">
							<div>
								<a target="_blank"
									href="TourismDetailServlet?tourismid=${orderInfo.tourismImages[0].tourism_id}">
									<img class="unit-pic" style="width: 180px; height: 100px;"
									src="<%=basePath %>${orderInfo.tourismImages[0].small}" />
								</a>
							</div>
							<div>${orderInfo.toutismTitle }</div>
						</td>
						<td>￥${orderInfo.order.price }</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${orderInfo.order.createTime }" type="both"/></td>
 
						<c:if test="${orderInfo.order.status == 1}">
							<td>待支付</td>
							<td><a
								href="handleOrderServlet?orderStatus=3&orderNo=${orderInfo.order.orderId}">受理</a></td>
							<td><a
								href="handleOrderServlet?orderStatus=4&orderNo=${orderInfo.order.orderId}">拒绝</a></td>
						</c:if>
						<c:if test="${orderInfo.order.status == 2}">
							<td>已支付</td>
							<td><a
								href="handleOrderServlet?orderStatus=3&orderNo=${orderInfo.order.orderId}">受理</a></td>
							<td><a
								href="handleOrderServlet?orderStatus=4&orderNo=${orderInfo.order.orderId}">拒绝</a></td>
						</c:if>
						<c:if test="${orderInfo.order.status == 3}">
							<td>受理通过</td>
						</c:if>
						<c:if test="${orderInfo.order.status == 4}">
							<td>受理拒绝</td>
						</c:if> 
					</tr>
				</c:forEach>
				<!-- 订单显示列表开结束 -->




				<tr>
					<td colspan="7" class="pageinfo" align="right">
						<div>
							现在是第 ${pageModel.page} 页，共 ${pageModel.totalPage} 页
							${pageModel.totalCount} 条记录 &nbsp;&nbsp; <a
								href="GetUserOrderListServlet?page=1&size=${pageModel.size}">首页</a>
							&nbsp;&nbsp; <a
								href="GetUserOrderListServlet?page=${pageModel.pre}&size=${pageModel.size}">上一页</a>
							&nbsp;&nbsp; <a
								href="GetUserOrderListServlet?page=${pageModel.next}&size=${pageModel.size}">下一页</a>
							&nbsp;&nbsp; <a
								href="#GetUserOrderListServlet?page=${pageModel.totalPage}&size=${pageModel.size}">末页</a>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>