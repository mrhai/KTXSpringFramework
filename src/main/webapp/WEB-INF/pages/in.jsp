<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" type="text/css"
	href="/HelloSpringMVCs/static/css/login.css" media="all" />
<script src="/HelloSpringMVCs/static/js/incr.js" type="text/javascript"></script>
</head>
<body onload="load()">
	<input type="hidden" id="id" value="${id}" />
	<jsp:include page="components/information.jsp" />
	<jsp:include page="components/banner.jsp" />
	<div id="center">
		<div id="title">In ấn</div>
		<jsp:include page="components/adminnav.jsp" />
		<div id="content">
			<div id="tool">
				<table>
					<tr>
						<td><input type="radio" id="sv" name="in" onclick="ins()"
							checked="checked"><label for="sv">Sinh viên</label></td>
						<td><input type="radio" id="nv" name="in" onclick="ins()"><label for="nv">Nhân viên</label></td>
						<td><input type="radio" id="emptyroom" name="in"
							onclick="ins()"><label for="emptyroom">Phong</label></td>
						<td><input type="radio" id="guest" name="in" onclick="ins()"><label for="guest">Khách</label></td>
						<td><input type="radio" id="device" name="in" onclick="ins()"><label for="device">Vật tư</label></td>
					</tr>
				</table>
			</div>
			<center>
				<font color="red"><p id="message">${message}</p></font>
			</center>
			<form action="insv.html" method="get">
				<table id="svtb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">In ấn sinh viên</td>
					</tr>
					<tr>
						<td width="45%" align="right">In theo</td>
						<td><select name="type" class="submit">
								<option value="dssv">DS sinh viên</option>
								<option value="dsnhd">DS nợ hóa đơn</option>
								<option value="ktkl">Khen thưởng/ Kỷ luật</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="In" /></td>
					</tr>
				</table>

			</form>

			<form action="innv.html" method="get">
				<table id="nvtb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">In ấn nhân viên</td>
					</tr>
					<tr>
						<td width="45%" align="right">In theo</td>
						<td><select name="type" class="submit">
								<option value="ttnv">Thông tin nhân viên</option>
								<option value="nvql">Nhân viên quản lý</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="In" /></td>
					</tr>
				</table>

			</form>

			<form action="innv.html" method="get">
				<table id="emptyroomtb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">In ấn phòng</td>
					</tr>
					<tr>
						<td width="45%" align="right">Chọn khu</td>
						<td><select name="regionchang" class="submit">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="In" /></td>
					</tr>
				</table>

			</form>

			<form action="innv.html" method="get">
				<table id="guesttb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">In ấn khách</td>
					</tr>
					<tr>
						<td width="45%" align="right">In theo</td>
						<td><select name="mode" class="submit">
								<option value="ds">Danh sách khách</option>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="In" /></td>
					</tr>
				</table>

			</form>

			<form action="invt.html" method="get">
				<table id="devicetb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">In ấn thiết bị</td>
					</tr>
					<tr>
						<td width="45%" align="right">Chọn phòng</td>
						<td><select name="maphong" class="submit">
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Chọn khu</td>
						<td><select name="makhu" class="submit">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="In" /></td>
					</tr>
				</table>

			</form>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />

</body>
</html>
