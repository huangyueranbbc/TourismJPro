<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>旅游网 - 首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
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
			<div id="main-list">
				<div class="title">
					<div class="category">跟团游</div>
					<div class="subcat">
						<ul>
							<li></li>
							<li><a href="#"> </a></li>

						</ul>
					</div>
				</div>
				 
				<!-- 跟团游 -->
				<c:forEach var="tourisms" items="${tourisms0 }">
				<div class="tbox"> 
					<div> 
						<a href="TourismDetailServlet?tourismid=${tourisms.tourism.ID }"> <img class="unit-pic"
							style="width: 227px; height: 151px;" src="<%=basePath %>${tourisms.tourismImages[0].big}" />
						</a>
					</div> 
					<div>${tourisms.tourism.title }</div>
 	
					<div class="saleprice">
						<div>￥${tourisms.tourism.basePrice }起</div>
						<div class="satisfaction">满意度:${tourisms.satisfactionPercent }</div>
					</div> 
				</div>  
				</c:forEach>
								
				
				
			</div>

			<div id="main-list">
				<div class="title">
					<div class="category">自驾游</div>
					<div class="subcat">
						<ul>
							<li></li>
							<li><a href="#"> </a></li>

						</ul>
					</div>
				</div>
				<!-- 自助游 --> 
				<c:forEach var="tourisms" items="${tourisms1 }">
				<div class="tbox"> 
					<div> 
						<a href="TourismDetailServlet?tourismid=${tourisms.tourism.ID }"> <img class="unit-pic"
							style="width: 227px; height: 151px;" src="<%=basePath %>${tourisms.tourismImages[0].big}" />
						</a>
					</div> 
					<div>${tourisms.tourism.title }</div>
 	
					<div class="saleprice">
						<div>￥${tourisms.tourism.basePrice }起</div>
						<div class="satisfaction">满意度:${tourisms.satisfactionPercent }</div>
					</div> 
				</div>  
				</c:forEach>
				
			</div>

			<div id="main-list">
				<div class="title">
					<div class="category">热门旅游</div>
					<div class="subcat">
						<ul>
							<li></li>
							<li><a href="#"> </a></li>

						</ul>
					</div>
				</div>
				<!-- 热门游 -->  
				<c:forEach var="tourisms" items="${tourismsHot }">
				<div class="tbox"> 
					<div> 
						<a href="TourismDetailServlet?tourismid=${tourisms.tourism.ID }"> <img class="unit-pic"
							style="width: 227px; height: 151px;" src="<%=basePath %>${tourisms.tourismImages[0].big}" />
						</a>
					</div> 
					<div>${tourisms.tourism.title }</div>
 	
					<div class="saleprice">
						<div>￥${tourisms.tourism.basePrice }起</div>
						<div class="satisfaction">满意度:${tourisms.satisfactionPercent }</div>
					</div> 
				</div>  
				</c:forEach>
				
			</div>
		</div>
		<!-- main end-->

		<%@ include file="/common_end.jspf"%>

	</div>
</body>

</html>