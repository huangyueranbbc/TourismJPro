<%@page import="com.ruanko.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>旅游网 - 用户中心页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/userCenter.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="script/jquery-1.6.2.js">
	
</script>
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
			<div class="memberCenter">
				<div class="tip">您当前的位置：旅游网首页 >> 用户中心</div>

				<!-- 左侧菜单栏页面 -->
				<div id="aside">
					<dl>
						<dt>订单中心</dt>
						<dd>
							<a href="GetUserOrderListServlet"
								onclick="$('#content').load('trackOrder.htm')"> 我的订单 </a>
						</dd>
						<dd>
							<a href="GetBillListServlet"> 我的账单 </a>
						</dd>
					</dl>
					<dl>
						<dt>评论管理</dt>
						<dd>
							<a href="GetUserCommentableList"> 我要评价 </a>
						</dd> 
						<dd>
							<a href="GetCommentList"> 查看评价 </a>
						</dd>  

					</dl>
					<dl>
						<dt>账户中心</dt>
						<dd>
							<a href="ToPersonServlet"
								onclick="$('#content').load('person.htm')"> 我的资料 </a>
						</dd>
					</dl>
				</div>
				<!-- 右侧功能页面 -->
				<div id="content">
					<div class="welcome"></div>
				</div>
				
			</div>
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