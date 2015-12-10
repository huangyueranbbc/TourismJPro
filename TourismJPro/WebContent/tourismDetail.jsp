<%@page import="com.ruanko.model.Comment"%>
<%@page import="com.ruanko.model.UserBo"%>
<%@page import="com.ruanko.model.User"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<title>旅游网 - 旅游信息页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/default.css" type="text/css" rel="stylesheet" />
<link href="css/productDetail.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="script/kindeditor-min.js"></script>
<script type="text/javascript" src="script/zh_CN.js"></script>
<script type="text/javascript" src="script/jquery-1.6.2.js"></script>
<script type="text/javascript" src="script/comment.js"></script>
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

			<!-- 上部分商品图片及选购区 -->
			<div class="product-main">
				<div class="title">当前位置 >> 旅游信息 >>${tourismInfo.tourism.title}</div>
				<div class="left-small">
					<ul>
						<c:forEach var="touristList" items="${tourismInfo.tourismImages}">
							<li><a href="#"> <img class="icon" height="70"
									width="90" src="<%=basePath %>${touristList.big}" />
							</a></li>
						</c:forEach>
					</ul>
				</div>

				<div class="left-big">
					<div>
						<a href="#"> <img id="icon"
							src="<%=basePath %>${tourismInfo.tourismImages[0].big}"
							height="370px" width="410px" />
						</a>
					</div>
				</div>


				<div class="right-info">

					<div class="saleprice">价格：￥${tourismInfo.tourism.basePrice} 起</div>

					<div class="keyInfo">
						<div
							style="width: 46%; float: left; border-right: 1px solid #ddd;">
							满意度${satisfactionPercent }</div>
						<div style="float: right;">已有${commentCount }评价</div>
					</div>
					<!-- 购买信息 -->
					<div class="purchase">

						<%
							if (session.getAttribute("userBo") == null) {
								out.print("	<form action='ToLoginServlet' method='post'>");
							} else {
						%>
						<form action="PlaceOrderServlet" method="post">
							<%
								}
							%>



							<div class="purchaseoption">
								出游日期： <select name="tourismtimeRuleId">
									<c:forEach var="tourismRuleList"
										items="${tourismInfo.tourismRules}">
										<option value="${tourismRuleList.ID}">${tourismRuleList.tourismTime}</option>
									</c:forEach> 
								</select>
							</div>
							<div class="purchaseoption">
								出游人数： <select id="" name="tourismnum">
									<c:forEach begin="${tourismInfo.tourism.minNumber}"
										end="${tourismInfo.tourism.maxNumber}" var="num">
										<option value="${num}">${num}</option>
									</c:forEach>
								</select>
							</div>

							<div>
								<input type="submit" name="submit" value="立刻预定"
									class="purchase-button"> </input>
							</div>
						</form>
					</div>
					<div class=""></div>
					<div class=""></div>
					<div class=""></div>
				</div>
			</div>

			<!-- 下部分商品详情区 -->
			<div class="product-info">

				<!-- product-deltail start -->
				<div class="product-deltail">
					<div class="prdduct-relation">
						<div style="background-color: #0aa344;">旅游信息</div>
					</div>
					<div class="description">${tourismInfo.tourism.description}</div>

					<div class="attribute">

						<div class="comments">
							<div class="comment">
								<!-- 发表评论 表单 -->
								<c:if test="${commentPower == true}">
									<div class="re-text">
										<div style="background-color: #0aa344;">
											&nbsp;&nbsp;&nbsp;发表评论</div>
									</div>
									<form action="AddCommentServlet" method="post">
										<!-- 评价 -->
										<select name="satisfaction">
											<option value="3">满意(3分)</option>
											<option value="2">一般(2分)</option>
											<option value="0">不满意(0分)</option>
										</select>
										<div id="reply" style="float: left;">

											<textarea name="content" id="content">
										 
                                        </textarea>
											<input id="product_id" type="hidden" name="tourism_id"
												value="${tourismInfo.tourism.ID}" /> <input id="cinput"
												type="submit" value="评论" onclick="publish()" />
										</div>
									</form>
									<%
										session.removeAttribute("commentPower");
									%>
								</c:if>
								<c:if test="${commentPower == false}"></c:if>

								<c:forEach var="comment" items="${comments}">
									<div id="reply-list">
										<div class="name">
											<div class="re-text1" style="">${comment.userName}</div>
											<div style="color: red;">
												<c:if test="${comment.satisfaction == 3}">很满意</c:if>
												<c:if test="${comment.satisfaction == 2}">一般</c:if>
												<c:if test="${comment.satisfaction == 0}">不满意</c:if>
											</div>
										</div>
										<div class="content2">${comment.content}</div>
										<div class="time">
											评论时间：
											<fmt:formatDate pattern="yyyy-MM-dd"
												value="${comment.createDate}" type="both" />
										</div>
									</div>
								</c:forEach>

							</div>
						</div>

					</div>
					<!-- product-deltail end -->
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
		</div>
	</div>
</body>

</html>