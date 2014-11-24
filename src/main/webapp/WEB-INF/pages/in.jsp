<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" type="text/css"
	href="/HelloSpringMVCs/static/css/login.css" media="all" />
<script src="/HelloSpringMVCs/static/js/room.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="components/information.jsp" />
	<jsp:include page="components/banner.jsp" />
	<div id="center">
		<div id="title">In ấn</div>
		<jsp:include page="components/adminnav.jsp" />
		<div id="content">
			<table>
				<tr>
					<td><input type="radio" id="emptyroom" name="room"
						checked="true">Sinh viên</input></td>
						<td><input type="radio" id="emptyroom" name="room"
						checked="true">Nhân viên</input></td>
						<td><input type="radio" id="emptyroom" name="room"
						checked="true">Phong</input></td>
						<td><input type="radio" id="emptyroom" name="room"
						checked="true">Khách</input></td>
						<td><input type="radio" id="emptyroom" name="room"
						checked="true">Vật tư</input></td>
				</tr>
			</table>
			<form action="insv.html" method="get">
                    <table id="emptyroomtb" width="100%" hidden="true">
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">In ấn sinh viên</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">In theo</td>
                            <td width="50%">
                                <select name="type">
                                <option>---</option>
                                <option value="dssv">DS sinh viên</option>
                                <option value="dsnhd">DS nợ hóa đơn</option>
                                <option value="ktkl">Khen thưởng/ Kỷ luật</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="In"/></td>
                        </tr>
                    </table>

                </form>
                
                <form action="innv.html" method="get">
                    <table id="emptyroomtb" width="100%" >
                        <tr>
                            <td colspan="2" align="center" bgcolor="#0099FF" class="titletable">In ấn nhân viên</td>
                        </tr>
                        <tr>
                            <td width="50%" align="right">In theo</td>
                            <td width="50%">
                                <select name="type">
                                <option>---</option>
                                <option value="ttnv">Thông tin nhân viên</option>
                                <option value="nvql">Nhân viên quản lý</option>
                                </select>
                            </td>
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
