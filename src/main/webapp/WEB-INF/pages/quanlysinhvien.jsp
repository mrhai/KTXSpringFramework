<%-- 
    Document   : quanlysinhvien
    Created on : Oct 28, 2014, 10:16:14 AM
    Author     : MrHai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/HelloSpringMVCs/static/css/login.css" media="all" />
        <script src="/HelloSpringMVCs/static/js/sv.js" type="text/javascript"></script>

    </head>
    <body onload="load()">
        <input type="hidden" id="id" value="${id}"/>
        <jsp:include page="components/information.jsp"/>
        <jsp:include page="components/banner.jsp" />
        <div id="center">
            <div id="title">Quản lý Sinh Viên</div>
            <jsp:include page="components/adminnav.jsp"/>
            <div id="content">
                <table>
                    <tr>
                        <td align="center"><input type="radio" id="add" name="addorchange" onclick="addorchangeAction()"/>Thêm SV</td>
                        <td  align="center"><input type="radio" id="delete" name="addorchange" onclick="addorchangeAction()"/>Xóa SV</td>
                        <td  align="center"><input type="radio" id="change" name="addorchange" onclick="addorchangeAction()"/>Đổi phòng</td>
                    </tr>
                </table>
                <form action="add.html" method="post">
                    <table id="addsv" width="100%" hidden="true">
                        <tr >
                            <td colspan="7" align="center" bgcolor="#0099FF" class="titletable" >Thêm Sinh Viên</td>
                        </tr>

                        <tr>

                            <td align="right">Mã số Sinh Viên</td>
                            <td><input id="mssv" type="text" name="mssv"  /></td>
                            <td align="right">Mã phòng</td>
                            <td>
                                <select name="roomnum">
                                    <c:forEach var="list" items="${roomnum}">
                                        <option value="${list}">${list}</option>
                                    </c:forEach>
                                </select>
                            </td>

                            <td align="right">Mã khu</td>
                            <td>
                                <select name="roomregion">
                                    <c:forEach var="list" items="${roomregion}">
                                        <option value="${list.makhu}">${list.tenkhu}</option>
                                    </c:forEach>
                                </select>
                            </td>

                        </tr>
                        <tr>
                            <td align="center">Tên Sinh Viên</td>
                            <td  align="center">Ngày sinh</td>
                            <td  align="center">Quê quán</td>
                            <td  align="center">Lớp</td>
                            <td  align="center">Khoa</td>
                            <td align="center">Số điện thoại</td>
                        </tr>
                        <tr>

                            <td><input type="text" name="tensv"/></td>
                            <td><input type="text" name="ngaysinh"/></td>
                            <td><input type="text" name="que"/></td>
                            <td><input type="text" name="lop"/></td>
                            <td><input type="text" name="khoa"/></td>
                            <td><input type="text" name="sdt"/></td>
                        </tr>
                        <tr>
                            <td colspan="7" align="center"><input type="submit" value="Thêm"/></td>

                        </tr>
                        <tr>
                            <td colspan="7" align="center"></td>

                        </tr>
                    </table>
                </form>
                <form name="delete" method="post" action="deletesv.html">
                    <table id="deletesv" width="100%" hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable" >Xóa Sinh Viên</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Chọn Sinh viên</td>
                            <td width="50%">
                                <select id="mssv" name="mssv" onchange="selectSV()">
                                    <c:forEach var="sv" items="${mssv}">
                                        <option value="${sv}">${sv}</option>
                                    </c:forEach>

                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td align="right">Sinh viên</td>
                            <td><input id="mssvdelete" name="mssvdelete"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Xóa"/></td>
                        </tr>
                    </table>
                </form>
                <form name="changeroom" method="post" action="changeroom.html">
                    <table id="changeroom" width="100%" hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable" >Thay đổi phòng</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Chọn Sinh Viên</td>
                            <td width="50%">
                                <select name="mssvchang">
                                    <c:forEach var="sv" items="${mssv}">
                                        <option value="${sv}">${sv}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="right">Chọn phòng</td>
                            <td><select name="roomchang">
                                    <c:forEach var="list" items="${roomnum}">
                                        <option value="${list}">${list}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <td align="right">Chọn khu</td>
                            <td><select name="regionchang">
                                    <c:forEach var="list" items="${roomregion}">
                                        <option value="${list.makhu}">${list.tenkhu}</option>
                                    </c:forEach>
                                </select></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Thay đổi"/></td>
                        </tr>
                    </table>
                </form>
                <center><font color="red">${message}</font></center>
            </div>
        </div>
        <jsp:include page="components/footer.jsp" />      

    </body>
</html>
