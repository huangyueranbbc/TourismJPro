<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>旅游网 - 管理中心登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/reg.css" type="text/css" rel="stylesheet" />
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
			<div id="cols">
				<div class="index_zbg">
					<div class="reg-main">
						<div class="reg-center">
							<div class="reg-left">
								<form id="loginform" name="loginform" action="LoginServlet"
									method="post">

									<dl>
										<dt>
											用户登录 <br />
										</dt>
										<dd id="showtips" style="display: none"></dd>
										<dd class="">
											<label id="label_username" class="labeltips"
												style="width: 140px; display: block;"> 用户名： </label> <input
												id="username" class="input-reg" type="text" name="name" />
										</dd>
										<dd class="">
											<label id="label_password" class="labeltips"
												style="display: block;"> 密码： </label> <input id="password"
												class="input-reg" type="password" maxlength="12" value=""
												autocomplete="off" name="password" />
										</dd>
									</dl>

									<p class="my-login">
										<input id="login_btn" class="reg-button" type="submit"
											value="登录" />
									<div style="margin-top: 50px;">
										<a href="#"></a>
									</div>

								</form>
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
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