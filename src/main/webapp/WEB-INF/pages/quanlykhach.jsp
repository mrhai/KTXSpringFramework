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
<script src="/HelloSpringMVCs/static/js/guest.js" type="text/javascript"></script>
</head>
<body onload="load()">
	<input id="id" type="hidden" value="${id}" />
	<jsp:include page="components/information.jsp" />
	<jsp:include page="components/banner.jsp" />
	<div id="center">
		<div id="title">Quản lý Khách</div>
		<jsp:include page="components/adminnav.jsp" />
		<div id="content">
			<div id="tool">
				<table>
					<tr>
						<td><input name="guest" type="radio" id="addguest"
							onclick="guestcase()" checked="checked"><label for="addguest">Ghi nhận thông
							tin khách</label></td>
						<td><input type="radio" id="checkguest" name="guest"
							onclick="guestcase()"><label for="checkguest">Danh sách khách</label></td>
						<td><input type="radio" id="deleteguest" name="guest"
							onclick="guestcase()"><label for="deleteguest">Xóa thông tin khách</label></td>
					</tr>
				</table>
			</div>
			<center>
				<font color="red"><p id="message">${message}</p></font>
			</center>
			<form name="addguesttb" action="add.html" method="post"
				onsubmit="return addValidate()">
				<table id="addguesttb" width="100%" hidden="true">
					<input id="mvcSrc" name="mnvSrc" type="hidden" value="${makhach}" />
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Ghi nhận thông tin khách ở</td>
					</tr>
					<tr>
						<td width="45%" align="right">Mã khách</td>
						<td><input type="text" name="makhach" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Tên khách</td>
						<td><input type="text" name="tenkhach" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Số CMND</td>
						<td><input type="text" name="cmnd" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Ngày ở</td>
						<td><input type="text" name="ngayo" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Ngày đi</td>
						<td><input type="text" name="ngaydi" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">MSSV xác nhận</td>
						<td><input type="text" name="mssv" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Chọn nhanh</td>
						<td><select id="selectmssv" name="selectmssv" onchange="SV()" class="submit">
								<option value="">---</option>
								<c:forEach var="sv" items="${mssv}">
									<option value="${sv}">${sv}</option>
								</c:forEach>
						</select></td>
					</tr>



					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Thêm" /></td>
					</tr>
				</table>
			</form>

			<form name="view" action="view.html" method="post">
				<table id="checkguesttb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Danh sách khách</td>
					</tr>

					<tr>
						<td width="45%" align="right">Chọn SV</td>
						<td><select id="mssvview" name="mssvview" class="submit"
							onchange="SV()">
								<option value="">Tất cả</option>
								<c:forEach var="sv" items="${mssv}">
									<option value="${sv}">${sv}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Mã SV</td>
						<td><input type="text" name="mssv" class="submit"/></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Xem" /></td>
					</tr>
				</table>
			</form>
			<form name="delete" action="delete.html" , method="post"
				onsubmit="return deleteValidate()">
				<table id="deleteguesttb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Xóa khách</td>
					</tr>
					<tr>
					<tr>
						<td align="right">Xóa theo</td>
						<td><select id="type" name="type" onchange="SV()" class="submit">
								<option value="leave">Khách đã đi</option>
								<option value="only">Từng khách một</option>
						</select></td>
					</tr>

					<tr>
						<td width="45%" align="right">Mã khách</td>
						<td><input type="text" name="makhach" class="submit"
							id="makhach" disabled="true" /></td>
					</tr>

					<tr>
						<td width="50%" align="right">Chọn khách</td>
						<td width="50%"><select name="smakhach" id="smakhach" class="submit"
							disabled="true" onchange="SV()">
								<option value="">---</option>
								<c:forEach var="guest" items="${listGuest}">
									<option value="${guest.maso}">${guest.maso}-
										${guest.tenkhach}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Xóa" /></td>
					</tr>
				</table>
			</form>

			<table id="viewlist" align="center" hidden="true" width="100%">
				<tr bgcolor="#3CF">
					<td>Mã khách</td>
					<td>Tên khách</td>
					<td>Số CMND</td>
					<td>Ngày ở</td>
					<td>Ngày đi</td>
					<td>MSSV</td>
				</tr>
				<c:forEach var="list" items="${list}">
					<tr>
						<c:choose>
							<c:when test="${list.leave > 0}">

								<td>${list.maso}</td>
								<td>${list.tenkhach}</td>
								<td>${list.cmnd}</td>
								<td>${list.ngayo}</td>
								<td><font color="red">${list.ngaydi}</font></td>
								<td>${list.student.mssv}</td>
							</c:when>
							<c:when test="${list.leave == 0}">
								<td>${list.maso}</td>
								<td>${list.tenkhach}</td>
								<td>${list.cmnd}</td>
								<td>${list.ngayo}</td>
								<td>${list.ngaydi}</td>
								<td>${list.student.mssv}</td>
							</c:when>
						</c:choose>

					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />


</body>
</html>
