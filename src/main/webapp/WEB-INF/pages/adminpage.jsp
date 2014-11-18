<%-- 
    Document   : adminpage
    Created on : Oct 28, 2014, 9:26:17 AM
    Author     : MrHai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" type="text/css" href="/HelloSpringMVCs/static/css/login.css" media="all" />
    </head>
    <body>
        <jsp:include page="components/information.jsp"/>
    <jsp:include page="components/banner.jsp" />
        <div id="center">
          <div id="title">Bạn đang ở phân mục dành cho quản trị viên</div>
          <jsp:include page="components/adminnav.jsp"/>
          <div id="content"></div>
    </div>
        <jsp:include page="components/footer.jsp" />      

    </body>
</html>
