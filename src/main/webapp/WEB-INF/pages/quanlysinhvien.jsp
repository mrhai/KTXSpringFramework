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
<script src="/HelloSpringMVCs/static/js/sv.js" type="text/javascript"></script>

</head>
<body onload="load()">
	<input type="hidden" id="id" value="${id}" />
	<jsp:include page="components/information.jsp" />
	<jsp:include page="components/banner.jsp" />
	<div id="center">
		<div id="title">Quản lý Sinh Viên</div>
		<jsp:include page="components/adminnav.jsp" />
		<div id="content">
			<table>
				<tr>
					<td align="center"><input type="radio" id="add"
						name="addorchange" onclick="addorchangeAction()" />Nhận sinh viên</td>
					<td align="center"><input type="radio" id="delete"
						name="addorchange" onclick="addorchangeAction()" />Xóa thông tin
						sinh viên</td>
					<td align="center"><input type="radio" id="change"
						name="addorchange" onclick="addorchangeAction()" />Đổi phòng</td>

					<td align="center"><input type="radio" id="studenlist"
						name="addorchange" onclick="addorchangeAction()" />Hồ sơ lưu trú
						viên</td>
					<td align="center"><input type="radio" id="arrears"
						name="addorchange" onclick="addorchangeAction()" />Nợ hóa đơn</td>
					<td align="center"><input type="radio" id="studenlist"
						name="addorchange" onclick="addorchangeAction()" />Khen thưởng/
						Kỉ luật</td>
				</tr>
			</table>
			<form action="add.html" method="post">
				<table id="addsv" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Thông tin sinh viên</td>
					</tr>

					<tr>

						<td align="right">Mã số Sinh Viên</td>
						<td><input id="mssv" type="text" name="mssv" /></td>
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
						<td align="right">Tên Sinh Viên</td>
						<td><input type="text" name="tensv" /></td>
					</tr>
					<tr>
						<td align="right">Ngày sinh</td>
						<td><input type="text" name="ngaysinh" /></td>
					</tr>
					<tr>
						<td align="right">Quê quán</td>
						<td><input type="text" name="que" /></td>
					</tr>
					<tr>
						<td align="right">Lớp</td>
						<td><input type="text" name="lop" /></td>
					</tr>
					<tr>
						<td align="right">Khoa</td>
						<td><input type="text" name="khoa" /></td>
					<tr />
					<tr>
						<td align="right">Số điện thoại</td>
						<td><input type="text" name="sdt" /></td>
					</tr>

					<tr>
						<td align="right">Ngày đi</td>
						<td><input type="text" name="ngaydi" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Ghi thông tin" /></td>

					</tr>
					<tr>
						<td colspan="2" align="right"></td>

					</tr>
				</table>
			</form>
			<form name="delete" method="post" action="deletesv.html"
				onsubmit="return confirm('Xác nhận xóa?')">
				<table id="deletesv" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Xóa thông tin sinh viên</td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn Sinh viên</td>
						<td width="50%"><select id="mssv" name="mssv"
							onchange="selectSV()">
								<option value="">---</option>
								<c:forEach var="sv" items="${mssv}">
									<option value="${sv}">${sv}</option>
								</c:forEach>

						</select></td>
					</tr>

					<tr>
						<td align="right">Sinh viên</td>
						<td><input id="mssvdelete" name="mssvdelete" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Xóa" /></td>
					</tr>
				</table>
			</form>
			<form name="changeroom" method="post" action="changeroom.html"
				onsubmit="return confirm('Xác nhận thay đổi phòng?')">
				<table id="changeroom" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Thay đổi phòng</td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn Sinh Viên</td>
						<td width="50%"><select name="mssv" onchange="selectSV()">
								<option value="">---</option>
								<c:forEach var="sv" items="${mssv}">
									<option value="${sv}">${sv}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="50%" align="right">Sinh viên</td>
						<td width="50%"><input type="text" name="mssvchang" /></td>
					</tr>
					<tr>
						<td align="right">Chọn phòng</td>
						<td><select name="roomchang">
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
					</tr>


					<tr>
						<td align="right">Chọn khu</td>
						<td><select name="regionchang">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="Thay đổi" /></td>
					</tr>
				</table>
			</form>

			<table width="100%" id="list" hidden="true">
				<form action="find.html" method="get">

					<tr>
						<td colspan="3" class="titletable" align="center">Tìm kiếm</td>
					</tr>
					<tr>
						<td align="right" width="45%">Mã sinh viên</td>
						<td><input type="text" name="mssv"></input></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input type="submit"
							value="Tìm"></input></td>
					</tr>

				</form>

				<tr class="titletable">
					<td width="40%" align="center" class="titletable">Mã sinh viên</td>
					<td width="30%" align="center" class="titletable">Tên sinh
						viên</td>
					<td width="30%" align="center" class="titletable"></td>
				</tr>

				<c:forEach var="student" items="${studentlist }">

					<c:choose>
						<c:when test="${student.leave > 0}">

							<tr>

								<td width="40%" align="center"><font color="red">${student.mssv }</font></td>
								<td width="30%" align="center">${student.tensv }</td>
								<td width="30%" align="center"><a
									href="view.html?mssv=${student.mssv}">Thông tin</a></td>
							</tr>
						</c:when>
						<c:when test="${student.leave == 0}">
							<tr>

								<td width="40%" align="center">${student.mssv }</td>
								<td width="30%" align="center">${student.tensv }</td>
								<td width="30%" align="center"><a
									href="view.html?mssv=${student.mssv}">Thông tin</a></td>
							</tr>
						</c:when>
					</c:choose>

				</c:forEach>
			</table>
			<table id="bill" width = "100%" hidden="true">
				<tr>
					<td class="titletable" align="center">Mã sinh viên</td>
					<td class="titletable" align="center">Mã phòng</td>
					<td class="titletable" align="center">Mã khu</td>
					<td class="titletable" align="center">Tiền còn thiếu (VND)</td>
				</tr>
				
					<c:forEach var="student" items="${bill }">
					<tr>
					<td align="center">${ student.mssv}</td>
					<td align="center">${ student.room.maphong}</td>
					<td align="center">${ student.room.roomRegion.makhu}</td>
					<td align="center">${ student.tienthieu}</td>
					</tr>
					</c:forEach>
				
			</table>

			<center>
				<font color="red">${message}</font>
			</center>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />

</body>
</html>
