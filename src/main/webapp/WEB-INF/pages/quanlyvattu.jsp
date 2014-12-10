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
					<!-- 						Nhập vật tư -->
					<td><input name="vt" type="radio" id="addvt"
						onclick="vtcase()" hidden="true"></input></td>

					<td><input type="radio" id="movevt" name="vt"
						onclick="vtcase()" checked="checked">Chuyển vật tư đến phòng</input></td>
					<td><input type="radio" id="repairvt" name="vt"
						onclick="vtcase()">Sữa chưa</input></td>
					<td><input type="radio" id="historyRepair" name="vt"
						onclick="vtcase()">Lịch sử sữa chưa</input></td>
					<td><input type="radio" id="fail" name="vt" onclick="vtcase()">Báo
						hỏng</input></td>
				</tr>
			</table>
<center>
				<font color="red"><p id = "message">${message}</p></font>
			</center>
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
									<option value="${vt.maso }">${vt.maso }-${vt.tenvattu }</option>
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

			<form name="move" action="chuyen.html" method="get" onsubmit="return moveValidate()">
				<table id="move" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Chuyển vật tư đến phòng</td>
					</tr>
					<tr>
						<td align="right">Mã phòng</td>
						<td><select name="roomnum">
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Mã khu</td>
						<td><select name="roomregion">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn vật tư</td>
						<td width="50%"><select name="vt">
								<c:forEach var="vt" items="${listVT }">
									<option value="${vt.maso }">${vt.maso }-${vt.tenvattu }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Số lượng</td>
						<td><input type="text" name="sl" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Chuyển" /></td>
					</tr>
				</table>
			</form>
			<form action="suachua.html" , method="get" name = "repair" onsubmit="return repairValidate()">
				<table id="repair" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Sữa chưa</td>
					</tr>
					<tr>
						<td align="right">Mã phòng</td>
						<td><select name="roomnum">
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Mã khu</td>
						<td><select name="roomregion">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn vật tư</td>
						<td width="50%"><select name="vt">
								<c:forEach var="vt" items="${listVT }">
									<option value="${vt.maso }">${vt.maso }-${vt.tenvattu }</option>
								</c:forEach>
						</select></td>
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
			<form action="lichsu.html" method="get">
				<table id="history" hidden="true" width="100%">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Lịch sử sữa chữa</td>
					</tr>
					<tr>
						<td width="50%" align="right">Mã phòng</td>
						<td width="50%"><select name="roomnum">
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="50%" align="right">Mã khu</td>
						<td width="50%"><select name="roomregion">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Xem" /></td>
					</tr>
				</table>
			</form>
			<form action="baohong.html" method="get">

				<table id="failtb" hidden="true" width="100%">
					<tr>
						<td colspan="3" align="center" bgcolor="#0099FF"
							class="titletable">Báo hỏng</td>
					</tr>
					<tr>
						<td align="right" width="50%">Mã phòng</td>
						<td><select name="roomnum">
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
						<td></td>
					</tr>
					<tr>
						<td align="right">Mã khu</td>
						<td><select name="roomregion">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input type="submit"
							value="Danh sách vật tư" /></td>
					</tr>
					<tr>
						<td align="right" class="titletable"  width="40%">Mã vật tư</td>
						<td align="right" class="titletable" width="30%">Tên vật tư</td>
						<td class="titletable" width="30%"></td>
					</tr>
					<c:forEach var="i" items="${device }">
						<tr>
							<td align="center">${i.maso }</td>
							<td align="center">${i.tenvattu }</td>
							<td align="center"><a href="baohong.html?mode=true&maphong=${maphong }&makhu=${makhu }&mavt=${i.maso }">Báo hỏng</a></td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<table width="100%" id="historyview" hidden="true">
				<tr>
					<td class="titletable" align="center">Mã phòng</td>
					<td class="titletable" align="center">Mã khu</td>
					<td class="titletable" align="center">Mã vật tư</td>
					<td class="titletable" align="center">Tên vật tư</td>
					<td class="titletable" align="center">Ngày sửa</td>
					<td class="titletable" align="center">Giá</td>
				</tr>
				<c:forEach var="i" items="${history }">
					<tr>
						<td align="center">${i.maphong }</td>
						<td align="center">${i.roomRegion.makhu }</td>
						<td align="center">${i.device.maso }</td>
						<td align="center">${i.device.tenvattu }</td>
						<td align="center">${i.device.ngaysua }</td>
						<td align="center">${i.device.gia }</td>
					</tr>
				</c:forEach>
			</table>

			

		</div>
	</div>
	<jsp:include page="components/footer.jsp" />

</body>
</html>
