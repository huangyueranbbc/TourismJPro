<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<title>新增旅游信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/createProduct.css" type="text/css" rel="stylesheet" />

</head>

<body>

	<div class="createProduct">
		<div class="title">
			<span> 管理中心 >> 添加旅游信息 </span>
		</div>
		<form method="post" action="AddToursimServlet">
			<!-- 基本信息 -->
			<div class="information">
				<div class="subtitle">
					<span> 基本信息 </span>
				</div>
				<table>
					<tr>
						<td class="btitle">景点主题：</td>
						<td><input type="text" name="title" id="title" /><font
							color="red" size="1">&nbsp;&nbsp;*</font></td>
						<td class="black">&nbsp;</td>
						<td class="btitle">类型：</td>
						<td><select name="type" id="type">
								<option value="0">跟团游</option>
								<option value="1" selected="">自助游</option>
						</select><font color="red" size="1">&nbsp;&nbsp;*</font></td>
					</tr>
					<tr>
						<td class="btitle">出发城市：</td>
						<td><input type="text" name="city" id="city" /><font
							color="red" size="1">&nbsp;&nbsp;*</font></td>
						<td class="black">&nbsp;</td>
						<td class="btitle">旅游天数：</td>
						<td><input type="text" name="days" id="days" /><font
							color="red" size="1">&nbsp;&nbsp;*(数字)</font></td>
					</tr>
					<tr>
						<td class="btitle">基本价格：</td>
						<td><input type="text" name="basePrice" id="basePrice" /><font
							color="red" size="1">&nbsp;&nbsp;*(数字)</font></td>
						<td class="black">&nbsp;</td>

					</tr>
					<tr>
						<td class="btitle">人数下限：</td>
						<td><input type="text" name="minNumber" id="minNumber" /><font
							color="red" size="1">&nbsp;&nbsp;*(数字)</font></td>
						<td class="black">&nbsp;</td>
						<td class="btitle">人数上限：</td>
						<td><input type="text" name="maxNumber" id="maxNumber" /><font
							color="red" size="1">&nbsp;&nbsp;*(数字)</font></td>
					</tr>


				</table>
			</div>

			<!-- 旅游规格 -->
			<div class="information">
				<div class="subtitle">
					<span> 旅游规则 </span>
				</div>
				<div class="spec">
					<table>
						<tr>
							<td style="width: 250px; text-align: left;">出游时间<font
								color="red" size="2">*正确的格式：yyyy-mm-dd</font></td>
							<td style="width: 50px; text-align: left;">价格<font
								color="red" size="1">&nbsp;&nbsp;*</font></td>
							<td style="width: 100px; text-align: center;">优惠<font
								color="red" size="1">&nbsp;&nbsp;*</font></td>
							<td style="width: 100px; text-align: right;">备注<font
								color="red" size="1">&nbsp;&nbsp;*</font></td>
						</tr>
						<tr>
							<td><input type="text" name="tourismTime"
								style="width: 200px;" /></td>
							<td><input type="text" name="price" style="width: 100px;" /></td>
							<td><input type="text" name="discount" style="width: 100px;" /></td>
							<td><input type="text" name="remark" style="width: 200px;" /></td>
						</tr>
						<tr>
							<td><input type="text" name="tourismTime" value=""
								style="width: 200px;" /></td>
							<td><input type="text" name="price" style="width: 100px;" /></td>
							<td><input type="text" name="discount" id="discount1"
								style="width: 100px;" /></td>
							<td><input type="text" name="remark" style="width: 200px;" /></td>
						</tr>
						<tr>
							<td><input type="text" name="tourismTime" value=""
								style="width: 200px;" /></td>
							<td><input type="text" name="price" style="width: 100px;" /></td>
							<td><input type="text" name="discount" style="width: 100px;" /></td>
							<td><input type="text" name="remark" style="width: 200px;" /></td>
						</tr>
						<tr>
							<td>预订截止时间(出游时间前&nbsp;</td>
							<td><input type="text" name="deadline" value="3" readonly /></td>
							<td>&nbsp;天)</td>
						</tr>
					</table>


				</div>


			</div>

			<!-- 详细描述 -->
			<div class="information">
				<div class="subtitle">
					<span> 旅游说明 </span>
				</div>
				<div class="detail">
					<textarea name="description" style="width: 800px; height: 200px;"
						id="description"></textarea>
				</div>
			</div>
			<div
				style="font-size: 18px; color: green; width: 700px; text-align: center;">

				<span>${message}</span>
			</div>
			<!-- 按钮 -->
			<div class="information">
				<div id="button">
					<input type="submit" value="提交" class="submitbutton"
						onclick="return check()" /> <input type="reset" value="重置" />
				</div>
			</div>
		</form>
		<!-- main end -->
	</div>
</body>

</html>