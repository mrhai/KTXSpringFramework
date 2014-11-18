

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--<link href="static/css/login.css" type="text/css" rel="stylesheet"/>-->
        <link rel="stylesheet" type="text/css" href="/HelloSpringMVCs/static/css/login.css" media="all" />
        <script src="/HelloSpringMVCs/static/js/checklogin.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="components/banner.jsp" />
        <div id="center">
            <div id="title">Đăng nhập</div>
            <form name="login" onsubmit="return checkField()" action="xulidangnhap.html" method="post" >
                <table width="1000">
                    <tr>
                        <td width="448" align="right"> Quyền</td>
                        <td width="155">
                            <select name = "rights">
                                <option value="admin">Admin</option>
                                <option value="sv">Sinh viên</option>
                                <option value="nv">Nhan viên</option>
                            </select>
                        </td>
                        <td width="381">&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="right">Tên đăng nhập</td>
                        <td><input type="text" name="username" /></td>
                        <td><label id="lblUsername"></label></td>
                    </tr>
                    <tr>
                        <td align="right">Mật khẩu</td>
                        <td><input type="password" name="password" /></td>
                        <td><label id="lblPassword"></label></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"><input type="submit" value="Đăng nhập"/></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"> <font color="red">${message}</font></td>
                    </tr>
                </table>
            </form>
        </div>
        <jsp:include page="components/footer.jsp" />      

    </body>
</html>
