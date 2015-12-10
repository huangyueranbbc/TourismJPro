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
<title>用户中心 - 跟踪订单页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/trackOrder.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<!-- header start -->
		<%@ include file="/common_header.jspf"%>
		<!-- header end -->
		<!-- navmenu start -->
		<%@ include file="/common_navmenu.jspf"%>
		<!-- navmenu end -->
		<div class="trackOrder">
			<div class="title">
				<span> 我的订单 </span>
			</div>


			<table style="text-align: center;">
				<tr>

					<th width="17%">订单号</th>
					<th width="27%">旅游线路</th>

					<th width="9%">订单金额</th>
					<th width="28%">下单时间</th>
					<th width="10%">订单状态</th>
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
								href="ToPaymentServlet?orderId=${orderInfo.order.orderId }">支付</a></td>
						</c:if>
						<c:if test="${orderInfo.order.status == 2}">
							<td>已支付</td>
							<td><a
								href="handleOrderServlet?orderStatus=5&orderNo=${orderInfo.order.orderId}">取消</a></td>
						</c:if>
						<c:if test="${orderInfo.order.status == 3}">
							<td>已受理</td>
						</c:if>
						<c:if test="${orderInfo.order.status == 4}">
							<td>受理拒绝</td>
							<td><a href="#">拒绝理由</a></td>
						</c:if>
						<c:if test="${orderInfo.order.status == 5}">
							<td>已取消</td>
							<td></td>
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
		<!-- navigate start-->
		<div id="navigate">
			<div>
				<ul>
					<li class="title">预订帮助</li>
					<li><a href="#"> 搜索旅游信息 </a></li>
					<li><a href="#"> 预订流程 </a></li>
				</ul>
			</div>
			<div>
				<ul>
					<li class="title">付款</li>
					<li><a href="#"> 付款方式 </a></li>
					<li><a href="#"> 网上支付 </a></li>
				</ul>
			</div>

			<div>
				<ul>
					<li class="title">跟团游</li>
					<li><a href="#"> 成团规则 </a></li>
					<li><a href="#"> 出团守则 </a></li>
				</ul>
			</div>

			<div>
				<ul>
					<li class="title">自助游</li>
					<li><a href="#"> 服务说明 </a></li>
					<li><a href="#"> 出游提醒 </a></li>
				</ul>
			</div>

			<div>
				<ul>
					<li class="title">出游准备</li>
					<li><a href="#"> 相关证件 </a></li>
					<li><a href="#"> 交通路线 </a></li>
				</ul>
			</div>


			<div>
				<ul>
					<li class="title">其他事项</li>
					<li><a href="#"> 旅游保险 </a></li>
					<li><a href="#"> 退款 </a></li>
				</ul>
			</div>

		</div>
		<!-- navigate end-->

		<!-- footer start-->
		<div id="footer">
			<ul>
				<li><a target="_blank" href="#"> 关于我们 </a></li>
				<li>｜</li>
				<li style="list-style: none"><a target="_blank" href="#">
						友情链接 </a></li>
				<li>｜</li>
				<li style="list-style: none"><a target="_blank" href="#">
						推荐 </a></li>
				<li>｜</li>
				<li><a target="_blank" href="#"> 《服务协议》 </a></li>
				<li>｜</li>
				<li><a target="_blank" href="#"> 《隐私条款》 </a></li>
				<li>｜</li>
				<li><a target="_blank" href="#"> 《用户规则》 </a></li>
				<li>｜</li>
				<li><a target="_blank" href="#"> 帮助 </a></li>
			</ul>
			<div class="license">
				联系客服 000-000-000&nbsp;客服邮件：xxxxxx@ruanko.com
				<p>
					Copyright&nbsp;&copy;&nbsp;软酷卓越实验室&nbsp; <a
						href="http://www.ruanko.com" title="软酷网" target="_blank"> <strong>
							软酷网 </strong>
					</a> &nbsp;版权所有
				</p>
			</div>
		</div>
		<!-- footer end -->
	</div>
</body>
</html>