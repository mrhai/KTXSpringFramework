<%-- 
    Document   : quanlysinhvien
    Created on : Oct 28, 2014, 10:16:14 AM
    Author     : MrHai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" type="text/css"
	href="/HelloSpringMVCs/static/css/login.css" media="all" />
<script src="/HelloSpringMVCs/static/js/bill.js" type="text/javascript"></script>
</head>
<body onload="load()">
	<input id="id" type="hidden" value="${id}" />
	<jsp:include page="components/information.jsp" />
	<jsp:include page="components/banner.jsp" />
	<div id="center">
		<div id="title">Kế toán</div>

		<jsp:include page="components/adminnav.jsp" />
		<div id="content">
			<div id="tool">
				<table>
					<tr>
						<td><input name="bill" type="radio" id="create"
							onclick="billCare()" checked="checked" ><label for="create"> Tạo hóa đơn</label></td>
						<td><input name="bill" type="radio" id="checkout"
							onclick="billCare()" ><label for="checkout"> Xuất hóa đơn</label></td>


					</tr>
				</table>
			</div>
			<center>
				<font color="red"><p id="message">${message }</p></font>
			</center>
			<form action="bill.html" method="post" name="bill"
				onsubmit="return createValidate()">
				<table width="100%" hidden="true" id="createtb">
					<tr>
						<td colspan="2" align="center" class="titletable">Tạo hóa đơn</td>
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
						<td align="right">Số điện</td>
						<td><input type="text" name="sodien" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Số nước</td>
						<td><input type="text" name="sonuoc" class="submit"/></td>
					</tr>
					<tr>
						<td align="right">Tháng</td>
						<td><input type="text" name="thang" value="${date }" class="submit"/></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Tạo hóa đơn" /></td>
					</tr>
				</table>
			</form>

			<form>
				<table width="100%" hidden="true">
					<tr>
						<td colspan="2" align="center" class="titletable">Thay đổi
							giá</td>
					</tr>
					<tr>
						<td width="45%" align="right">Chọn loại</td>
						<td><select></select></td>
					</tr>
					<tr>
						<td align="right">Nhập giá</td>
						<td><input type="text" name="sodien" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Thay đổi" /></td>
					</tr>
				</table>
			</form>

			<form action="out.html" method="get" name="out">
				<table width="100%" hidden="true" id="checkouttb">
					<tr>
						<td colspan="2" align="center" class="titletable">Xuất hóa
							đơn</td>
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
						<td align="right">Hóa đơn tháng</td>
						<td><select name="thang" class="submit">
								<c:forEach begin="1" end="12" var="i">
									<option value="${i }">${i }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" class="submit"
							value="Xuất" /></td>
					</tr>
				</table>
			</form>

		</div>
	</div>
	<jsp:include page="components/footer.jsp" />

</body>
</html>
