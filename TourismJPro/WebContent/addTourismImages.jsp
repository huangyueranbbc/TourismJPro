<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>新增旅游信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/createProduct.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="script/jquery-1.6.2.js"></script>

</head>

<body>

	<div class="createProduct">
		<div class="title">
			<span> 管理中心 >> 添加旅游信息图片</span>
		</div>
		<form action="AddTourismImagesServlet" method="post"
			enctype="MULTIPART/FORM-DATA" id="addForm">


			<input type="hidden" name="id" value="${tourismId}">

			<!-- 旅游图片 -->
			<div class="information">
				<div class="subtitle">
					<span> 旅游图片 </span>
				</div>
				<div class="images">
					<span><font color="red" size="2">&nbsp;&nbsp;*上传图片最大限：4M</font></span>
					<span> 图片: <input type="file" name="image1" />
					</span> <span> 图片: <input type="file" name="image2" />
					</span> <span> 图片: <input type="file" name="image3" />
					</span> <span> <input type="button" value="+ 新增图片" />
					</span>
				</div>
			</div>

			<div
				style="font-size: 18px; color: green; width: 700px; text-align: center;">
				<span>${message}</span>
			</div>
			<!-- 按钮 -->
			<div class="information">
				<div id="button">
					<input type="submit" value="提交" class="submitbutton" /> <input
						type="reset" value="重置" />
				</div>
			</div>
		</form>
		<!-- main end -->
	</div>
</body>

</html>