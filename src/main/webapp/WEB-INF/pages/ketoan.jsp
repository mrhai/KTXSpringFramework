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
    </head>
    <body>
        <jsp:include page="components/information.jsp"/>
    <jsp:include page="components/banner.jsp" />
        <div id="center">
          <div id="title">Kế toán</div>
          
          <jsp:include page="components/adminnav.jsp"/>
          <div id="content">
              <table >
                    <tr>
                        <td><input name="guest" type="radio" id="addguest"  onclick="guestcase()">
                        Tạo hóa đơn</td>
                        <td><input type="radio" id="checkguest" name="guest" onclick="guestcase()">
                        Thay đổi giá</td>
                        <td><input type="radio" id="deleteguest" name="guest" onclick="guestcase()">
                        In ấn</td>
                    </tr>
            </table>
                
                <form>
                    <table width="100%" hidden="true">
                    <tr>
                      <td colspan="2" align="center" class="titletable">Tạo hóa đơn</td>
                    </tr>
                    <tr>
                      <td width="50%" align="right">Chọn phòng</td>
                      <td width="50%"><select></select></td>
                    </tr>
                    <tr>
                      <td align="right">Chọn khu</td>
                      <td><select></select></td>
                    </tr>
                    <tr>
                      <td align="right">Số điện</td>
                      <td><input type="text" name="sodien"/></td>
                    </tr>
                    <tr>
                      <td align="right">Số nước</td>
                      <td><input type="text" name="sonuoc"/></td>
                    </tr>
                    <tr>
                      <td align="right">Tháng</td>
                      <td><input type="text" name="thang"/></td>
                    </tr>
                     <tr>
                      <td colspan="2" align="center"><input type="submit" value="Tạo hóa đơn"/></td>
                    </tr>
                  </table>
                </form>
                
                 <form>
                     <table width="100%" hidden="true">
                    <tr>
                      <td colspan="2" align="center" class="titletable">Thay đổi giá</td>
                    </tr>
                    <tr>
                      <td width="50%" align="right">Chọn loại</td>
                      <td width="50%"><select></select></td>
                    </tr>
                    <tr>
                      <td align="right">Nhập giá</td>
                      <td><input type="text" name="sodien"/></td>
                    </tr>
                     <tr>
                      <td colspan="2" align="center"><input type="submit" value="Thay đổi"/></td>
                    </tr>
                  </table>
                </form>
                
                 <form>
                     <table width="100%" >
                    <tr>
                      <td colspan="2" align="center" class="titletable">In ấn</td>
                    </tr>
                    <tr>
                      <td width="50%" align="right">Chọn mục cần in</td>
                      <td width="50%"><select></select></td>
                    </tr>
                     <tr>
                      <td colspan="2" align="center"><input type="submit" value="In"/></td>
                    </tr>
                  </table>
                </form>
          </div>
    </div>
        <jsp:include page="components/footer.jsp" />      

    </body>
</html>
