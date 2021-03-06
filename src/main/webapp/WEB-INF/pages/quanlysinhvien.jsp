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
		<div id="title" >Quản lý Sinh Viên</div>
		<jsp:include page="components/adminnav.jsp" />
		<div id="content">
			<div id="tool">
				<table>
					<tr>
						<td align="center"><input type="radio" id="add"
							name="addorchange" onclick="addorchangeAction()"
							checked="checked"><label for="add">Nhận sinh viên</label></td>
						<td align="center"><input type="radio" id="delete"
							name="addorchange" onclick="addorchangeAction()" ><label for="delete">Xóa thông tin
							sinh viên</label></td>
						<td align="center"><input type="radio" id="change"
							name="addorchange" onclick="addorchangeAction()" ><label for="change">Đổi phòng</label></td>

						<td align="center"><input type="radio" id="studenlist"
							name="addorchange" onclick="addorchangeAction()" ><label for="studenlist">Hồ sơ lưu trú
							viên</label></td>
						<td align="center"><input type="radio" id="arrears"
							name="addorchange" onclick="addorchangeAction()" ><label for="arrears">Nợ hóa đơn</label></td>
						<td align="center"><input type="radio" id="khenthuong"
							name="addorchange" onclick="addorchangeAction()" ><label for="khenthuong">Khen thưởng/
							Kỉ luật</label></td>
					</tr>
				</table>
			</div>
			<center>
				<font color="red"><p id="message">${message}</p></font>
			</center>
			<form action="add.html" method="post" ENCTYPE='multipart/form-data'
				name="add" onsubmit="return addValidate()">
				<table id="addsv" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Thông tin sinh viên</td>
					</tr>

					<tr>

						<td align="right" width="45%">Mã số Sinh Viên</td>
						<td><input id="mssv" type="text" name="mssv" class="submit" /></td>
					</tr>
					<tr>
						<td align="right" width="45%">Mã phòng</td>
						<td><select name="roomnum" class="submit" >
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right" width="45%">Mã khu</td>
						<td><select name="roomregion" class="submit">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right" width="45%">Tên Sinh Viên</td>
						<td><input type="text" name="tensv" class="submit" /></td>
					</tr>
					<tr>
						<td align="right" width="45%">Ngày sinh</td>
						<td><input type="text" name="ngaysinh" class="submit" /></td>
					</tr>
					<tr>
						<td align="right" width="45%">Quê quán</td>
						<td><input type="text" name="que" class="submit" /></td>
					</tr>
					<tr>
						<td align="right" width="45%">Lớp</td>
						<td><input type="text" name="lop" class="submit" /></td>
					</tr>
					<tr>
						<td align="right" width="45%">Khoa</td>
						<td><select name="khoa" width="45%" class="submit">
								<option value="CNTT">Công nghệ thông tin</option>
								<option value="KT">Kinh tế</option>
								<option value="TS">Thủy sản</option>
								<option value="MT">Môi trường</option>
								<option value="NT">Nông học</option>
						</select></td>
					<tr />
					<tr>
						<td align="right" width="45%">Số điện thoại</td>
						<td><input type="text" name="sdt" class="submit"/></td>
					</tr>

					<tr>
						<td align="right" width="45%">Ngày đi</td>
						<td><input type="text" name="ngaydi" class="submit" /></td>
					</tr>
					<tr>
						<td align="right" width="45%">Hình đại diện</td>
						<td><input type="file" name="file"  /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Ghi thông tin" /></td>

					</tr>

				</table>
			</form>
			<form name="delete" method="post" action="deletesv.html"
				onsubmit="return deleteValidate()">
				<table id="deletesv" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Xóa thông tin sinh viên</td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn Sinh viên</td>
						<td width="50%"><select id="mssv" name="mssv" class="submit"
							onchange="selectSV()">
								<option value="">---</option>
								<c:forEach var="sv" items="${mssv}">
									<option value="${sv}">${sv}</option>
								</c:forEach>

						</select></td>
					</tr>

					<tr>
						<td align="right">Sinh viên</td>
						<td><input id="mssvdelete" name="mssvdelete" class="submit" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Xóa" /></td>
					</tr>
				</table>
			</form>
			<form name="changeroom" method="post" action="changeroom.html"
				onsubmit="return changeValidate()">
				<table id="changeroom" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Thay đổi phòng</td>
					</tr>
					<tr>
						<td width="50%" align="right">Chọn Sinh Viên</td>
						<td width="50%"><select name="mssv" onchange="selectSV()" class="submit">
								<option value="">---</option>
								<c:forEach var="sv" items="${mssv}">
									<option value="${sv}">${sv}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="50%" align="right">Sinh viên</td>
						<td width="50%"><input type="text" name="mssvchang" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Chọn phòng</td>
						<td><select name="roomchang" class="submit">
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
					</tr>


					<tr>
						<td align="right">Chọn khu</td>
						<td><select name="regionchang" class="submit">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Thay đổi" /></td>
					</tr>
				</table>
			</form>

			<table width="100%" id="list" hidden="true">
				<form name="find" action="find.html" method="get">

					<tr>
						<td colspan="3" class="titletable" align="center">Tìm kiếm</td>
					</tr>
					<tr>
						<td align="right" width="45%">Mã sinh viên</td>
						<td><input type="text" name="mssv" class="submit"></input></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="3" align="center"><input type="submit" class="submit"
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
			<form action="ktkl.html" method="post" name="ktkl"
				onsubmit="return ktklValidate()">
				<table id="khen" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Khen thưởng/ Kỉ luật</td>
					</tr>
					<tr>
						<td width="45%" align="right">Chọn mục</td>
						<td width="55%"><select name="mode" class="submit">
								<option value="">---</option>
								<option value="khen">Khen thưởng</option>
								<option value="kl">Kỉ luật</option>

						</select></td>
					</tr>

					<tr>
						<td width="45%" align="right">Mã sinh viên</td>
						<td width="55%"><input type="text" name="mssv" class="submit"></input></td>
					</tr>
					<tr valign="top">
						<td width="45%" align="right">Nội dung</td>
						<td width="55%"><textarea rows="5" cols="30" name="nd" class="submit"></textarea>
						</td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Lưu" /></td>
					</tr>
				</table>
			</form>
			<form action="timktkl" method="get">
				<table id="timktkl" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Danh sách kỉ luật khen thưởng</td>
					</tr>

					<tr>
						<td width="45%" align="right">Mã sinh viên</td>
						<td width="55%"><input type="text" name="mssv" class="submit"></input></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Tìm" /></td>
					</tr>

				</table>
			</form>
			<table id="listktkl" width="100%" hidden="true">
				<tr>
					<td width="20%" align="center" class="titletable">Mã số sinh
						viên</td>
					<td width="20%" align="center" class="titletable">Khen thưởng/
						Kỉ luật</td>
					<td width="60%" align="center" class="titletable">Nội dung</td>
				</tr>
				<c:forEach var="ktkl" items="${listktkl }">
					<tr>
						<td align="center">${ktkl.mssv }</td>
						<td align="center"><c:choose>
								<c:when test="${ktkl.mode == 'kt'}">Khen thưởng</c:when>
								<c:when test="${ktkl.mode == 'kl'}">Kỉ luật</c:when>
							</c:choose></td>
						<td align="left">${ktkl.noidung }</td>
					</tr>
				</c:forEach>
			</table>

			</table>

			<table id="bill" width="100%" hidden="true">
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


		</div>
	</div>
	<jsp:include page="components/footer.jsp" />

</body>
</html>
