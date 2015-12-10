<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>旅游网 - 修改密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="css/reg.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="script/jquery-1.6.2.js">
	
</script>
<script type="text/javascript" src="script/jquery.validate.js">
	
</script>
<script type="text/javascript" src="script/validator.js">
	
</script>
</head>

<body>
	<!-- main start -->
	<div id="main">
		<!-- header start -->
		<%@ include file="/common_header.jspf"%>
		<!-- header end -->
		<!-- navmenu start -->
		<%@ include file="/common_navmenu.jspf"%>
		<!-- navmenu end -->
		<form id="registerform" method="post" name="registerform"
			action="UpdateUserServlet">
			<dl>
				<dt>修改密码</dt>
				<dd>
					<label id="label_username" class="labeltips"> <em
						style="font-size: 14px; color: red;";> * </em> 用户名：
					</label> &nbsp;&nbsp;&nbsp<input readonly="true" id="username"
						class="input-reg" type="text" name="name" value="${userBo.name}" />
				</dd>
				<br />
				<dd>
					<label id="label_password" class="labeltips"> <em
						style="font-size: 14px; color: red;";> * </em> 修改密码：
					</label> <input id="password" class="input-reg" type="password"
						name="password" autocomplete="off" />
				</dd>
				<dd id="tip_password" class="info">
					<span class="validatorMsg validatorInit">
						6-16位字符，可使用字母、数字或符号的组合 </span>
				</dd>
				<br />
				<dd>
					<label id="label_password2"> <em
						style="font-size: 14px; color: red;";> * </em> 确认密码：
					</label> <input id="password2" class="input-reg" type="password"
						name="password2" autocomplete="off" />
				</dd>
				<dd id="tip_password2" class="info">
					<span class="validatorMsg validatorInit"> 请再次输入登录密码，两次输入必须一致
					</span>
				</dd>
				<br />
				<p id="register">
					<input type="submit" class="reg-button" id="register" value="修改密码" />
				</p>
		</form>
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