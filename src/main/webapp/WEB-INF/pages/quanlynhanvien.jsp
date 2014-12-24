<%-- 
    Document   : quanlysinhvien
    Created on : Oct 28, 2014, 10:16:14 AM
    Author     : MrHai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" type="text/css"
	href="/HelloSpringMVCs/static/css/login.css" media="all" />
<script src="/HelloSpringMVCs/static/js/employee.js"
	type="text/javascript"></script>
</head>
<body onload="load()">
	<input type="hidden" id="id" value="${id}" />
	<jsp:include page="components/information.jsp" />
	<jsp:include page="components/banner.jsp" />
	<div id="center">
		<div id="title">Quản lý Nhân Viên</div>
		<jsp:include page="components/adminnav.jsp" />
		<div id="content">
			<div id="tool">
				<table>
					<tr>
						<td><input type="radio" id="addemployee" name="employee"
							onclick="employeecase()" checked="checked"><label for="addemployee">Nhận nhân viên</label></td>
						<td><input type="radio" id="deleteemployee" name="employee"
							onclick="employeecase()"><label for="deleteemployee">Xóa nhân viên</label></td>
						<td><input type="radio" id="changesalary" name="employee"
							onclick="employeecase()"><label for="changesalary">Lương</label></td>
						<td><input type="radio" id="employee" name="employee"
							onclick="employeecase()"><label for="employee">Cán bộ quản lý phòng</label></td>
						<td><input type="radio" id="employeelist" name="employee"
							onclick="employeecase()"><label for="employeelist">DS nhân viên quản lý</label></td>
					</tr>
				</table>
			</div>
			<center>
				<font color="red"><p id="message">${message}</p></font>
			</center>
			<form name="add" action="add.html" method="post"
				onsubmit="return addValidate()">
				<input id="mvcSrc" name="mnvSrc" type="hidden" value="${manv}" />
				<table id="addemptb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Thông tin nhân viên</td>
					</tr>
					<tr>
						<td width="45%" align="right">Mã nhân viên</td>
						<td><input id="mnv" type="text" name="mnv" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Tên nhân viên</td>
						<td><input type="text" name="tennv" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Ngay sinh</td>
						<td><input type="text" name="ngaysinh" class="submit" /></td>
					</tr>
					<tr>
						<td align="right">diachi</td>
						<td><input type="text" name="diachi" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">chức vụ</td>
						<td><select name="chucvu" class="submit">
								<c:forEach var="cv" items="${chucvu}">
									<option value="${cv.ma}">${cv.ten}</option>
								</c:forEach>
						</select></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Thêm" /></td>
					</tr>
				</table>
			</form>
			<form name="delete" action="delete.html" method="post"
				onsubmit="return deleteValidate()">
				<table id="deleteemptb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Xóa nhân viên</td>
					</tr>
					<tr>
						<td width="45%" align="right">Mã nhân viên</td>
						<td><input type="text" name="mnv" class="submit"/></td>
					</tr>

					<tr>
						<td align="right">Chọn nhanh</td>
						<td><select name="smnv" onchange="select()" class="submit">
								<option value="">---</option>
								<c:forEach var="mnv" items="${mnv}">
									<option value="${mnv.manv}">${mnv.manv}-${mnv.tennv}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Xóa" /></td>
					</tr>
				</table>
			</form>
			<form action="changesalary.html" method="post">
				<table id="changesalarytb" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Thay đổi lương</td>
					</tr>
					<tr>
						<td width="45%" align="right">Chọn nhân viên</td>
						<td><select name="mnv" class="submit">
								<option value="">---</option>
								<c:forEach var="mnv" items="${mnv}">
									<option value="${mnv.manv}">${mnv.manv}-${mnv.tennv}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Nhập lương</td>
						<td><input type="text" name="luong" class="submit"/></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Thay đổi" /></td>
					</tr>
				</table>
			</form>
			<form action="room.html" method="get" name="room"
				onsubmit="return roomValidate()">
				<table id="room" width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" bgcolor="#0099FF"
							class="titletable">Cán bộ quản lý phòng</td>
					</tr>

					<tr>
						<td width="45%" align="right">Mã phòng</td>
						<td><select name="roomnum" class="submit">
								<c:forEach var="list" items="${roomnum}">
									<option value="${list}">${list}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">Mã khu</td>
						<td><select name="roomregion" class="submit">
								<c:forEach var="list" items="${roomregion}">
									<option value="${list.makhu}">${list.tenkhu}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td width="45%" align="right">Nhân viên</td>
						<td><select name="mnv" class="submit">
								<option value="">---</option>
								<c:forEach var="mnv" items="${mnv}">
									<option value="${mnv.manv}">${mnv.manv}-${mnv.tennv}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Thay đổi" /></td>
					</tr>
				</table>
			</form>
			<table id="listmanager" width="100%" hidden="true">
				<tr>
					<td class="titletable">Mã nhân viên</td>
					<td class="titletable">Tên nhân viên</td>
					<td class="titletable">Mã phòng</td>
					<td class="titletable">Tên phòng</td>
				</tr>
				<c:forEach var="i" items="${listManager }">
					<tr>
						<td>${i.employee.manv }</td>
						<td>${i.employee.tennv }</td>
						<td>${i.maphong }</td>
						<td>${i.roomRegion.makhu }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />

</body>
</html>
