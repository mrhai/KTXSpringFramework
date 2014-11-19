<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Thông tin sinh viên</title>
<link rel="stylesheet" type="text/css"
	href="/HelloSpringMVCs/static/css/login.css" media="all" />
</head>
<body>
<h1>Thông tin sinh viên</h1>
<table width="100%">
<tr >
<th class="titletable">Mã số sinh viên</th>
<th class="titletable">Họ tên</th>
<th class="titletable">Ngày sinh</th>
<th class="titletable">Quê quán</th>
<th class="titletable">Lớp</th>
<th class="titletable">Khoa</th>
<th class="titletable">Số điện thoại</th>
</tr>
<tr>
<c:forEach var="st" items="${student }">
<td align="center">${st.mssv }</td>
<td align="center">${st.tensv }</td>
<td align="center">${st.ngaysinh }</td>
<td align="center">${st.quequan }</td>
<td align="center">${st.lop }</td>
<td align="center">${st.khoa }</td>
<td align="center">${st.sdt }</td>
</c:forEach>
</tr>
</table>
</body>
</html>