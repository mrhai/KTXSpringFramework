<%-- 
    Document   : quanlysinhvien
    Created on : Oct 28, 2014, 10:16:14 AM
    Author     : MrHai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="/HelloSpringMVCs/static/css/login.css" media="all" />
        <script src="/HelloSpringMVCs/static/js/room.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="components/information.jsp"/>
        <jsp:include page="components/banner.jsp" />
        <div id="center">
            <div id="title">Quản lý Phòng</div>
            <jsp:include page="components/adminnav.jsp"/>
            <div id="content">
                <table>
                    <tr>
                        <td><input type="radio" id="emptyroom" name="room" checked="true" onclick="roomcase()" >Phòng trống</input></td>
                        <td><input type="radio" id="addroom" name="room" onclick="roomcase()">Thêm phòng</input></td>
                        <td><input type="radio" id="addregion" name="room" onclick="roomcase()">Thêm khu nhà</input></td>
                        <!--                         xoa phong -->
                        <td><input type="radio" id="deleteroom" name="room" onclick="roomcase()" hidden="true"></input></td>
                        <!--                         Xóa khu nhà -->
                        <td><input type="radio" id="deleteregion" name="room" onclick="roomcase()" hidden="true"></input></td>
                        <td><input type="radio" id="changeemployee" name="room" onclick="roomcase()">DS phong</input></td>
                    </tr>
                </table>
                <form action="#" method="post">
                    <table id="emptyroomtb" width="100%">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Phòng trống</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Chọn khu</td>
                            <td width="50%">
                                <select></select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Xem"/></td>
                        </tr>
                    </table>

                </form>
                <form action="#" method="post">
                    <table id="addroomtb" width="100%" hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Thêm phòng</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Chọn khu</td>
                            <td width="50%"><select></select></td>
                        </tr>
                        <tr>
                            <td align="right">Mã phòng</td>
                            <td><input type="text" name="roomcode"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Thêm"/></td>
                        </tr>
                    </table>
                </form>
                <form  action="#" method="post">
                    <table id="deleteroomtb" width="100%" hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">Xóa phòng</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Chọn khu</td>
                            <td width="50%"><select></select></td>
                        </tr>
                        <tr>
                            <td align="right">Chọn phòng</td>
                            <td><select></select></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Xóa"/></td>
                        </tr>
                    </table>
                </form>
                <form action="#" method="post">
                    <table id="addregiontb" width="100%" hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable" >Thêm khu nhà</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Mã khu nhà</td>
                            <td width="50%"><input type="text" name="regioncode" /></td>
                        </tr>
                        <tr>
                            <td align="right">Tên khu nhà</td>
                            <td><input type="text" name="regionname" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Thêm"/></td>
                        </tr>
                    </table>
                </form>

                <form action="#" method="post">
                    <table id="deleteregiontb" width="100%" hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable" >Xóa khu nhà</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Mã khu nhà</td>
                            <td width="50%"><input type="text" name="regioncode"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Xóa"/></td>
                        </tr>
                    </table>
                </form>

                <form action="#" method="post">
                    <table id="changeemployeetb" width="100%"  hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable" >Danh sách phòng</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">Chọn khu nhà</td>
                            <td width="50%"><select></select></td>
                        </tr>
                       
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Xem"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <jsp:include page="components/footer.jsp" />      

    </body>
</html>
