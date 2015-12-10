<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>旅游网 - 会员免费注册</title>
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
								<form id="registerform" method="post" name="registerform"
									action="RegisterServlet">
									<dl>
										<dt>注册新用户</dt>
										<dd>
											<label id="label_username" class="labeltips"> <em
												style="font-size: 14px; color: red;";> * </em> 用户名：
											</label> <input id="username" class="input-reg" type="text"
												name="name" />
										</dd>
										<dd id="tip_username" class="info">
											<span class="validatorMsg validatorInit"> 请输入用户名 </span>
										</dd>
										<dd>
											<label id="label_password" class="labeltips"> <em
												style="font-size: 14px; color: red;";> * </em> 密码：
											</label> <input id="password" class="input-reg" type="password"
												name="password" autocomplete="off" />
										</dd>
										<dd id="tip_password" class="info">
											<span class="validatorMsg validatorInit">
												6-16位字符，可使用字母、数字或符号的组合 </span>
										</dd>
										<dd>
											<label id="label_password2"> <em
												style="font-size: 14px; color: red;";> * </em> 确认密码：
											</label> <input id="password2" class="input-reg" type="password"
												name="password2" autocomplete="off" />
										</dd>
										<dd id="tip_password2" class="info">
											<span class="validatorMsg validatorInit">
												请再次输入登录密码，两次输入必须一致 </span>
										</dd>
										<dd>
											<label id="label_mobile" class="labeltips"> <em
												style="font-size: 14px; color: red;";> * </em> 手机号：
											</label> <input id="mobile" class="input-reg" type="text"
												name="mobile" />
										</dd>
										<dd id="tip_mobile" class="info">
											<span class="validatorMsg validatorInit"> 请输入你的手机号码 </span>
										</dd>
										<dd>
											<label id="label_email" class="labeltips"> <em
												style="font-size: 14px; color: red;";> * </em> 邮箱：
											</label> <input id="email" class="input-reg" type="text" name="email" />
										</dd>
										<dd id="tip_email" class="info">
											<span class="validatorMsg validatorInit"> 请输入你的邮箱 </span>
										</dd>
									</dl>

									<p id="register">
										<input type="submit" class="reg-button" id="register"
											value="立即注册" />
									</p>
								</form>
							</div>
							<div class="reg-right">
								<dl>
									<dt class="font18">我已经注册，现在就</dt>
									<dd>
										<a class="reg-log" href="login.htm"> 登录 </a>
									</dd>
								</dl>
							</div>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- main end -->

		<%@ include file="/common_end.jspf"%>

	</div>
</body>

</html>