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
<title>软酷诚服 - 搜索结果列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/searchList.css" type="text/css" rel="stylesheet" />
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
			<!-- list-left start -->
			<div id="list-left" style="float: left;">
				<div class="title">当前位置 >> 搜索旅游信息</div>
				<div class="productAll" style="float: left;">
					<div id="maininfo">
						<div id="title">全部旅游信息</div>
						<span> 找到相关旅游信息 234 款 &nbsp; <a
							href="SearchTourismServlet?page=${pageModel.pre}&size=${pageModel.size}&searchType=${searchType}&startprice=${startprice}&endprice=${endprice}&tourismtype=${tourismtype}"
							target="_self"> &nbsp; &lt;上一页 &nbsp; </a> <a
							href="SearchTourismServlet?page=${pageModel.next}&size=${pageModel.size}&searchType=${searchType}&startprice=${startprice}&endprice=${endprice}&tourismtype=${tourismtype}"
							target="_self"> &nbsp; 下一页&gt; &nbsp; </a> 共 789 页
						</span>
					</div> 

					<div id="searchcondition">
						<ul>
							<li><a href="SearchTourismServlet?page=1&size=${pageModel.size}&searchType=0"> 默认 </a></li>
							<li>｜</li>
							<a href="SearchTourismServlet?page=1&size=${pageModel.size}&searchType=4"> 销量 </a> 
							<li>｜</li> 
							<a target="_blank" href="#"> 好评 </a> 
							<li>｜</li>
							<li><a target="_blank" href="#"> 最新 </a></li>
							<li>｜</li>
							<li><a href="SearchTourismServlet?page=1&size=${pageModel.size}&searchType=1"> 价格 </a></li>
						</ul>    
						<div style="float: left; margin: 5px 0 0 0;">
							<form action="SearchTourismServlet?page=${pageModel.page}&size=${pageModel.size}&searchType=2" method="post">
								<input type="text" name="startprice" value=""
									style="width: 60px;" /> - <input type="text" name="endprice"
									value="" style="width: 60px;" /> <input type="submit"
									value="搜索" />
							</form> 
						</div> 
					</div>

				</div>
				<tr>
					<!-- list-left tbox -->
					<c:forEach var="touristList" items="${pageModel.list}">
						<div class="tbox">
							<div>
								<a href="TourismDetailServlet?tourismid=${touristList.tourism.ID }"> <img class="unit-pic"
									style="width: 227px; height: 151px;"
									src="<%=basePath %>${touristList.tourismImages[0].big}" />
								</a>
							</div>
							<div>${touristList.tourism.title }</div>

							<div class="saleprice">
								<div>￥${touristList.tourism.basePrice }起</div>
								<div class="satisfaction">满意度:95%</div>
							</div>
						</div>
					</c:forEach>
			</div>
			<!-- list-left end -->
			<!-- list-right start -->


			<!-- pagination start -->
			<div class="pagination" style="display: inline;">
				<div class="blackarea">&nbsp;</div>
				<div id="paginlink" style="float: left; margin-top: 5px;">
					<a class="active_a"
						href="SearchTourismServlet?page=${pageModel.pre}&size=${pageModel.size}&searchType=${searchType}&startprice=${startprice}&endprice=${endprice}&tourismtype=${tourismtype}"
						target="_self"> &lt;上一页 </a>



					<c:forEach begin="${pageModel.beginPageIndex}"
						end="${pageModel.endPageIndex}" var="num">
						<c:choose>
							<c:when test="${pageModel.page == num}">
								<span class="active_tnt_link"> ${num} </span>
							</c:when>
 
							<c:otherwise>
								<a class="active_a" href="SearchTourismServlet?page=${num}&size=${pageModel.size}&searchType=${searchType}&startprice=${startprice}&endprice=${endprice}&tourismtype=${tourismtype}" target="_self"> ${num} </a>
							</c:otherwise>

						</c:choose>
					</c:forEach>
 
					... <a class="active_a" href="SearchTourismServlet?page=${pageModel.totalPage}&size=${pageModel.size}&searchType=${searchType}&startprice=${startprice}&endprice=${endprice}&tourismtype=${tourismtype}" target="_self"> ${pageModel.totalPage} </a> <a
						class="active_a"
						href="SearchTourismServlet?page=${pageModel.next}&size=${pageModel.size}&searchType=${searchType}&startprice=${startprice}&endprice=${endprice}&tourismtype=${tourismtype}"
						target="_self"> 下一页&gt; </a>
				</div>
				<div class="pageinfo">共 789 页, 到第</div>
				<div class="pageform">
					<form action="SearchTourismServlet?size=${pageModel.size}&searchType=${searchType}&startprice=${startprice}&endprice=${endprice}&tourismtype=${tourismtype}" method="post">
						<input type="hidden" name="searchName" value="" /> <input
							type="text" name="page" value="1" style="width: 20px;" /> 页 <input
							type="submit" value="确定" /> 
					</form> 
				</div>
			</div>
			<!-- pagination end -->

			<!-- searchbox start -->
			<div class="searchbox">
				<form action="ToSearchTourismServlet?page=${pageModel.next}&size=${pageModel.size}&searchType=${searchType}&startprice=${startprice}&endprice=${endprice}" method="post">
					<input type="text" name="searchName" value="" class="searchinput" />
					<input type="submit" value="搜索" class="searchsubmit" />
				</form> 
			</div>
			<!-- searchbox end -->
		</div>
		<!-- main end -->
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