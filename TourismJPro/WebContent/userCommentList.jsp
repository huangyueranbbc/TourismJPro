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
<title>用户中心 >> 用户评论列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/listStyle.css" type="text/css" rel="stylesheet" />

</head>

<body>
	<!-- header start -->
	<%@ include file="/common_header.jspf"%>
	<!-- header end -->
	<!-- navmenu start -->
	<%@ include file="/common_navmenu.jspf"%>
	<!-- navmenu end -->
	<div class="listStyle">
		<div class="title">
			<span> 用户中心 >> 用户评论列表 </span>
		</div>


		<table style="text-align:center;">
			<tr> 
				<th width="8%">评论主题</th>
				<th width="6%">满意度</th>
				<th width="30%">评论内容</th> 
				<th width="10%">评论时间</th>
			</tr> 
			<c:forEach var="commentList" items="${pageModel.list}">
				<tr>  
					<td>${commentList.commentTitle}</td> 
					<td>
						<c:if test="${commentList.comment.satisfaction == 3}">很满意</c:if>
						<c:if test="${commentList.comment.satisfaction == 2}">一般</c:if>
						<c:if test="${commentList.comment.satisfaction == 0}">不满意</c:if>
					</td>  
					<td>${commentList.comment.content}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${commentList.comment.createDate}" type="both"/></td>
				</tr>   
 

			</c:forEach>
			<tr>
				<td colspan="9">&nbsp;</td>
			</tr>

			<tr>
				<td colspan="9" class="pageinfo" align="right">
					<div>  
						现在是第 ${pageModel.page} 页，共 ${pageModel.totalPage} 页
							${pageModel.totalCount} 条记录 &nbsp;&nbsp; <a
							href="GetCommentList?page=1&size=${pageModel.size}">首页</a> <a
							href="GetCommentList?page=${pageModel.pre}&size=${pageModel.size}">上一页</a>
						<a
							href="GetCommentList?page=${pageModel.next}&size=${pageModel.size}">下一页</a>
						<a
							href="GetCommentList?page=${pageModel.totalPage}&size=${pageModel.size}">末页</a>
					</div>
				</td>
			</tr>
		</table>
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