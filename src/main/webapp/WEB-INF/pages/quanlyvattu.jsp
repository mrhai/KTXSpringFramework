<%-- 
    Document   : quanlysinhvien
    Created on : Oct 28, 2014, 10:16:14 AM
    Author     : MrHai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" type="text/css"
	href="/HelloSpringMVCs/static/css/login.css" media="all" />
<script src="/HelloSpringMVCs/static/js/vattu.js" type="text/javascript"></script>
</head>
<body onload="load()">
	<input id="id" type="hidden" value="${id}" />
	<jsp:include page="components/information.jsp" />
	<jsp:include page="components/banner.jsp" />
	<div id="center">
		<div id="title">Quản lý vật tư</div>
		<jsp:include page="components/adminnav.jsp" />
		<div id="content">
			<table>
				<tr>
					<td><input name="vt" type="radio" id="addvt"
						onclick="vtcase()">Nhập vật tư</input></td>
					<td><input type="radio" id="movevt" name="vt"
						onclick="vtcase()">Chuyển vật tư đến phòng</input></td>
					<td><input type="radio" id="repairvt" name="vt"
						onclick="vtcase()">Sữa chưa</input></td>
				</tr>
			</table>

			<form name="nhap" action="nhap.html" method="get">
				<table id="nhap" width="100%" hidden="true">

					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Nhập vật tư</td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn vật tư</td>
						<td width="50%"><select name="vt">
								<c:forEach var="vt" items="${listVT }">
									<option value="${vt.maso }">${vt.maso }- ${vt.tenvattu }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Số lượng</td>
						<td><input type="text" name="sl" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Nhập kho" /></td>
					</tr>
				</table>
			</form>

			<form name="move" action="view.html" method="post">
				<table id="move" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Chuyển vật tư đến phòng</td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn khu</td>
						<td width="50%"><select></select></td>
					</tr>
					<tr>
						<td align="right">Chọn phòng</td>
						<td><select></select></td>
					</tr>
					<tr>
						<td align="right">Chọn vật tư</td>
						<td><select></select></td>
					</tr>
					<tr>
						<td align="right">Số lượng</td>
						<td><input type="text" name="mssv" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Chuyển" /></td>
					</tr>
				</table>
			</form>
			<form action="delete.html" , method="post">
				<table id="repair" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Xữa chưa</td>
					</tr>
					<tr>

						<td align="right">Chọn khu</td>
						<td><select></select></td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn phòng</td>
						<td width="50%"><select></select></td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn Vật tư</td>
						<td width="50%"><select></select></td>
					</tr>
					<tr>
						<td width="50%" align="right">Giá sửa</td>
						<td width="50%"><input type="text" name="gia" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Lưu" /></td>
					</tr>
				</table>
			</form>
			<center>
				<font color="red">${message}</font>
			</center>

		</div>
	</div>
	<jsp:include page="components/footer.jsp" />

</body>
</html>
