<%-- 
    Document   : adminpage
    Created on : Oct 28, 2014, 9:26:17 AM
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
</head>
<body>
	<jsp:include page="components/information.jsp" />
	<jsp:include page="components/banner.jsp" />
	<div id="center">
		<div id="title">Danh sách sinh viên trong phòng</div>
		<jsp:include page="components/studentnav.jsp" />
		<div id="content">
			<div id="tool">
				<table width="100%" id="historyview">
					<tr>
						<td class="titletable" align="center">MSSV</td>
						<td class="titletable" align="center">Họ tên</td>

					</tr>
					<c:forEach var="i" items="${list }">
						<tr>
							<td align="center">${i.mssv }</td>
							<td align="center">${i.tensv }</td>

						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="components/footer.jsp" />

</body>
</html>
