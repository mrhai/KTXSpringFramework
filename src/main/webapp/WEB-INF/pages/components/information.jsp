
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="information">
    <ul>
    <li>Tài khoản: ${username} </li>
    <li><a href="#">Đổi mật khẩu</a></li>
    <li><a href="${pageContext.request.contextPath}/user/logout.html">Đăng xuất</a></li>
    </ul>
    </div>