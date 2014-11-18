<%-- 
    Document   : quanlysinhvien
    Created on : Oct 28, 2014, 10:16:14 AM
    Author     : MrHai
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
       <link rel="stylesheet" type="text/css" href="/HelloSpringMVCs/static/css/login.css" media="all" />
        <script src="/HelloSpringMVCs/static/js/employee.js" type="text/javascript"></script>
    </head>
    <body onload="load()">
        <input type="hidden" id="id" value="${id}"/>
        <jsp:include page="components/information.jsp"/>
    <jsp:include page="components/banner.jsp" />
        <div id="center">
          <div id="title">Quản lý Nhân Viên</div>
          <jsp:include page="components/adminnav.jsp"/>
          <div id="content">
          <table>
          <tr>
              <td><input type="radio" id="addemployee" name="employee" onclick="employeecase()">Thêm nhân viên</input></td>
            <td><input type="radio" id="deleteemployee" name="employee" onclick="employeecase()">Xóa nhân viên</input></td>
            <td><input type="radio" id="changesalary" name="employee" onclick="employeecase()">Lương</input></td>
          </tr>
          </table>
              <form name="add" action="add.html" method="post">
                  <input id="mvcSrc" name="mnvSrc" type="hidden" value="${manv}"/>
                  <table id="addemptb" width="100%" hidden="true">
              <tr>
                <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Thêm nhân viên</td>
              </tr>
              <tr>
                  <td width="51%" align="right">Mã nhân viên</td>
                <td width="49%">
                    <input id="mnv" type="text" name="mnv"/>
                </td>
              </tr>
              <tr>
                <td align="right">Tên nhân viên</td>
                <td><input type="text" name="tennv"/></td>
              </tr>
              <tr>
                <td align="right">Ngay sinh</td>
                <td><input type="text" name="ngaysinh"/></td>
              </tr>
              <tr>
                <td align="right">diachi</td>
                <td><input type="text" name="diachi"/></td>
              </tr>
              <tr>
                <td align="right">chức vụ</td>
                <td>
                <select name="chucvu">
                    <c:forEach var="cv" items="${chucvu}">
                        <option value="${cv.ma}">${cv.ten}</option>
                    </c:forEach>
                </select>
                </td>
              </tr>
              
              <tr>
                <td colspan="2" align="center"><input type="submit" value="Thêm"/></td>
              </tr>
            </table>
          </form>
          <form action="delete.html" method="post">
              <table id="deleteemptb" width="100%" hidden="true">
              <tr>
                <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Xóa nhân viên</td>
              </tr>
              <tr>
                <td width="51%" align="right">Chọn nhân viên</td>
                <td width="49%">
                    
                    <select name="mnv">
                        <c:forEach var="mnv" items="${mnv}">
                            <option value="${mnv}">${mnv}</option>
                        </c:forEach>
                    </select>
                </td>
              </tr>
              <tr>
                  <td colspan="2" align="center"><input type="submit" value="Xóa"/></td>
              </tr>
            </table>
          </form>
          <form action="changesalary.html" method="post">
            <table id="changesalarytb" width="100%" hidden="true">
              <tr>
                <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Thay đổi lương</td>
              </tr>
              <tr>
                <td width="51%" align="right">Chọn nhân viên</td>
                <td width="49%">
                     <select name="mnv">
                        <c:forEach var="mnv" items="${mnv}">
                            <option value="${mnv}">${mnv}</option>
                        </c:forEach>
                    </select>
                </td>
              </tr>
              <tr>
                <td align="right">Nhập lương</td>
                <td><input type="text" name="luong"/></td>
              </tr>
              <tr>
                  <td colspan="2" align="center"><input type="submit" value="Thay đổi"/></td>
              </tr>
            </table>
          </form>
            <center><font color = "red">${message}</font></center>
          </div>
    </div>
        <jsp:include page="components/footer.jsp" />      

    </body>
</html>
