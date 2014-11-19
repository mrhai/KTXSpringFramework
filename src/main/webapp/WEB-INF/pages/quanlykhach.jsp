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
        <script src="/HelloSpringMVCs/static/js/guest.js" type="text/javascript"></script>
    </head>
    <body onload="load()">
        <input id="id" type="hidden" value="${id}"/>
        <jsp:include page="components/information.jsp"/>
        <jsp:include page="components/banner.jsp" />
        <div id="center">
            <div id="title">Quản lý Khách</div>
            <jsp:include page="components/adminnav.jsp"/>
            <div id="content">
                <table>
                    <tr>
                        <td><input name="guest" type="radio" id="addguest"  onclick="guestcase()">Ghi nhận thông tin khách</input></td>
                        <td><input type="radio" id="checkguest" name="guest" onclick="guestcase()">Danh sách khách</input></td>
                        <td><input type="radio" id="deleteguest" name="guest" onclick="guestcase()">Xóa thông tin khách</input></td>
                    </tr>
                </table>

                <form name="addguesttb" action="add.html" method="post">
                    <table id="addguesttb" width="100%" hidden="true">
                        <input id="mvcSrc" name="mnvSrc" type="hidden" value="${makhach}"/>
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Ghi nhận thông tin khách ở</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Mã khách</td>
                            <td width="50%"><input type="text" name="makhach" /></td>
                        </tr>
                        <tr>
                            <td align="right">Tên khách</td>
                            <td><input type="text" name="tenkhach" /></td>
                        </tr>
                        <tr>
                            <td align="right">Số CMND</td>
                            <td><input type="text" name="cmnd" /></td>
                        </tr>
                        <tr>
                            <td align="right">Ngày ở</td>
                            <td><input type="text" name="ngayo" /></td>
                        </tr>
                        <tr>
                            <td align="right">Ngày đi</td>
                            <td><input type="text" name="ngaydi" /></td>
                        </tr>
                         <tr>
                            <td align="right">MSSV xác nhận</td>
                            <td>
                               <input type="text" name="mssv"/>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">Chọn nhanh</td>
                            <td>
                                <select id="selectmssv" name="selectmssv" onchange="SV()">
                                <option value="">---</option>
                                    <c:forEach var="sv" items="${mssv}">
                                        <option value="${sv}">${sv}</option>
                                    </c:forEach>
                                </select>

                            </td>
                        </tr>
                        
                        
                        
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Thêm"/></td>
                        </tr>
                    </table>
                </form>

                <form name="view" action="view.html" method="post">
                    <table id="checkguesttb" width="100%" hidden="true"  >
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Danh sách khách</td>
                        </tr>

                        <tr>
                            <td width="50%" align="right">Chọn SV</td>
                            <td width="50%">
                                <select id="mssvview" name="mssvview" onchange="SV()">
                                    <option value="">Tất cả</option>
                                    <c:forEach var="sv" items="${mssv}">
                                        <option value="${sv}">${sv}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">Mã SV</td>
                            <td><input type="text" name="mssv"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Xem"/></td>
                        </tr>
                    </table>
                </form>
                <form name="delete" action="delete.html", method="post" onsubmit="return confirm('Bạn có muốn xóa người này?')">
                    <table id="deleteguesttb" width="100%" hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Xóa khách</td>
                        </tr>
                        <tr>
                        <tr>
                            <td align="right">Xóa theo</td>
                            <td>
                                <select id="type" name="type" onchange="SV()">
                                	<option value="leave" selected="true">---</option>
                                    <option value="leave">Khách đã đi</option>
                                    <option value="only">Từng khách một</option>
                                </select>
                            </td>
                        </tr>
                        
                          <tr>
                        <td width="50%" align="right">Mã khách</td>
                        <td width="50%">
                            <input type="text" name="makhach" id="makhach" disabled="true"/>
                        </td>
                        </tr>
                        
                        <tr>
                        <td width="50%" align="right" >Chọn khách</td>
                        <td width="50%">
                            <select name="smakhach" id="smakhach" disabled="true" onchange="SV()" >
                             <option value="">---</option>
                                <c:forEach var="guest" items="${listGuest}">
                                    <option value="${guest.maso}">${guest.maso} - ${guest.tenkhach}</option>
                                </c:forEach>
                            </select>
                        </td>
                        </tr>

                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Xóa"/></td>
                        </tr>
                    </table>
                </form>
                <center><font color="red" >${message}</font></center>
                <table id="viewlist" align="center" hidden="true" width = "100%">
                    <tr bgcolor="#3CF">
                        <td>Mã khách</td>
                        <td>Tên khách</td>
                        <td>Số CMND</td>
                        <td>Ngày ở</td>
                        <td>Ngày đi</td>
                        <td>MSSV</td>
                    </tr>
                    <c:forEach var="list" items="${list}">
                        <tr>
                            <c:choose>
                                <c:when test="${list.leave > 0}">

                                    <td>${list.maso}</td>
                                    <td>${list.tenkhach}</td>
                                    <td>${list.cmnd}</td>
                                    <td>${list.ngayo}</td>
                                    <td> <font color="red">${list.ngaydi}</font></td>
                                    <td>${list.student.mssv}</td>   
                                </c:when>
                                <c:when test="${list.leave == 0}">
                                    <td>${list.maso}</td>
                                    <td>${list.tenkhach}</td>
                                    <td>${list.cmnd}</td>
                                    <td>${list.ngayo}</td>
                                    <td>${list.ngaydi}</td>
                                    <td>${list.student.mssv}</td>   
                                </c:when>
                            </c:choose>

                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <jsp:include page="components/footer.jsp" />      

    </body>
</html>
