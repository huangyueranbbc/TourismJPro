<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:useBean id="MD5" scope="request" class="beartool.MD5"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>旅游服务网 - 支付页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/payment.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<div id="wrapper"> 
		<!-- header start -->
		<%@ include file="/common_header.jspf"%>
		<!-- header end -->
		<!-- navmenu start -->
		<%@ include file="/common_navmenu.jspf"%>
		<!-- navmenu end -->

		<!-- main start -->
		<div id="main">
			<!-- processbar -->

			<form action="PaymentServlet" method="post">
				<!-- 订单 --> 
				<div class="orderinfo">
					<div class="tip">订单已提交成功，请您尽快完成付款！</div>
					<table>
						<tr class="title" style="height: 20px;">
							<th width="12%">订单号</th>
							<th width="40%">旅游主题</th>
							<th>需支付金额</th>
							<th>支付方式</th>

						</tr>
						<tr>
							<td style="text-align: left;"><a href="#"> ${order.orderId}
							</a></td>
							<td style="text-align: left;"><a href="#"> 
							<c:choose>
								<c:when test="${order_tourism.type} == 0}">
									跟团游
								</c:when>
								<c:otherwise>
									自助游
								</c:otherwise>
							</c:choose>
							
							</a>
								&nbsp;,&nbsp; <a href="#"> ${order_tourism.title} </a> </br></td>
							<td>￥${order.amount}</td>
							<td>在线支付</td>

						</tr>
					</table>
				</div>
				<div class="dopayment">
					<table>
						<tr>
							<td class="atitle">账号：</td>
							<td colspan="2"><input id="name" class="input-account"
								type="text" name="name" /></td>
						</tr>
						<tr>
							<td class="atitle">密码：</td>
							<td colspan="2"><input id="password" class="input-account"
								type="password" name="password" /></td>
						</tr>
						<tr>
							<td colspan="3"> 
								<div class="calculation">您共需要为订单支付:</div>
								<div class="totalpriceneeded">￥${order.amount}</div>
								<div class="paybutton">
									<input type="submit" name="submit" value="确认付款"
										class="pay-button" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!-- 通过隐藏域传递订单信息 -->
				<input type="hidden" name="order_id" value="${order.orderId}" style="" /> 
				<input	type="hidden" name="pay_account" value="${order.amount}" style="" /> 
				<input type="hidden" name="" value="" style="" />
				<input type="hidden" name="" value="" style="" /> 
				<input type="hidden" name="" value="" style="" />
			</form>
		</div> 
		<!-- main end-->
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