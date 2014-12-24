

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
            <form name="login" onsubmit="return checkField()" action="${pageContext.request.contextPath}/user/xulidangnhap.html" method="post" >
                <table width="100%">
                    
                    <tr>
                        <td  width="45%" align="right">Tên đăng nhập</td>
                        <td><input type="text" name="username" class="submit" /><label id="lblUsername"></td>
                        <td></label></td>
                    </tr>
                    <tr>
                        <td  width="45%" align="right">Mật khẩu</td>
                        <td><input type="password" name="password" class="submit" /><label id="lblPassword"></label></td>
                        <td></td>
                    </tr>
                    <tr>
                    <td width="45%"></td>
                        <td colspan="2" align="left"><input type="submit" value="Đăng nhập" class="submit"/></td>
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
