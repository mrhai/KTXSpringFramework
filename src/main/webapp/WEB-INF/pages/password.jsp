

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
            <div id="title">Đổi mật khẩu</div>
            <jsp:include page="components/adminnav.jsp" />
            <form name="login" onsubmit="return checkField()" action="password.html" method="post" >
            <input type="hidden" name = "username" value="${username }">
                <table width="100%">
                    
                    <tr>
                        <td  width="45%" align="right">Mật khẩu mới</td>
                        <td><input type="text" name="newpw" /></td>
                        <td><label id="lblUsername"></label></td>
                    </tr>
                    <tr>
                        <td  width="45%" align="right">Xác nhận</td>
                        <td><input type="password" name="confirm" /></td>
                        <td><label id="lblPassword"></label></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"><input type="submit" value="Đổi mật khẩu"/></td>
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
