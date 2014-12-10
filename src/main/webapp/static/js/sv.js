function addorchangeAction() {

	if (document.getElementById("add").checked) {
		document.getElementById("addsv").hidden = false;
		document.getElementById("deletesv").hidden = true;
		document.getElementById("changeroom").hidden = true;
		document.getElementById("list").hidden = true;
		document.getElementById("bill").hidden = true;
		document.getElementById("khen").hidden = true;
		document.getElementById("timktkl").hidden = true;
		document.getElementById("listktkl").hidden = true;
	} else if (document.getElementById("delete").checked) {
		document.getElementById("addsv").hidden = true;
		document.getElementById("deletesv").hidden = false;
		document.getElementById("changeroom").hidden = true;
		document.getElementById("list").hidden = true;
		document.getElementById("bill").hidden = true;
		document.getElementById("khen").hidden = true;
		document.getElementById("timktkl").hidden = true;
		document.getElementById("listktkl").hidden = true;
	} else if (document.getElementById("change").checked) {
		document.getElementById("addsv").hidden = true;
		document.getElementById("deletesv").hidden = true;
		document.getElementById("changeroom").hidden = false;
		document.getElementById("list").hidden = true;
		document.getElementById("bill").hidden = true;
		document.getElementById("khen").hidden = true;
		document.getElementById("timktkl").hidden = true;
		document.getElementById("listktkl").hidden = true;
	} else if (document.getElementById("studenlist").checked) {
		document.getElementById("addsv").hidden = true;
		document.getElementById("deletesv").hidden = true;
		document.getElementById("changeroom").hidden = true;
		document.getElementById("list").hidden = false;
		document.getElementById("bill").hidden = true;
		document.getElementById("khen").hidden = true;
		document.getElementById("timktkl").hidden = true;
		document.getElementById("listktkl").hidden = true;
	} else if (document.getElementById("arrears").checked) {
		document.getElementById("addsv").hidden = true;
		document.getElementById("deletesv").hidden = true;
		document.getElementById("changeroom").hidden = true;
		document.getElementById("list").hidden = true;
		document.getElementById("bill").hidden = false;
		document.getElementById("khen").hidden = true;
		document.getElementById("timktkl").hidden = true;
		document.getElementById("listktkl").hidden = true;
	} else if (document.getElementById("khenthuong").checked) {
		document.getElementById("addsv").hidden = true;
		document.getElementById("deletesv").hidden = true;
		document.getElementById("changeroom").hidden = true;
		document.getElementById("list").hidden = true;
		document.getElementById("bill").hidden = true;
		document.getElementById("khen").hidden = false;
		document.getElementById("timktkl").hidden = false;
		document.getElementById("listktkl").hidden = false;

	}
}

function load() {
	var id = document.getElementById("id").value;
	if (id === "add") {
		document.getElementById("add").checked = true;
	} else if (id === "delete") {
		document.getElementById("delete").checked = true;
	} else if (id === "change") {
		document.getElementById("change").checked = true;
	} else if (id === "list") {
		document.getElementById("studenlist").checked = true;
	} else if (id === "ktkl") {
		document.getElementById("khenthuong").checked = true;
	}
	addorchangeAction();
}

function selectSV() {
	document.forms["delete"]["mssvdelete"].value = document.forms["delete"]["mssv"].value;
	document.forms["changeroom"]["mssvchang"].value = document.forms["changeroom"]["mssv"].value;
}

function addValidate() {
	var err = "";
	var mssv = document.forms["add"]["mssv"].value;
	var tensv = document.forms["add"]["tensv"].value;
	var ngaysinh = document.forms["add"]["ngaysinh"].value;
	var que = document.forms["add"]["que"].value;
	var lop = document.forms["add"]["lop"].value;
	var sdt = document.forms["add"]["sdt"].value;
	var ngaydi = document.forms["add"]["ngaydi"].value;
	var file = document.forms["add"]["file"].value;
	if (mssv === "" || tensv === "" || ngaysinh === "" || que === ""
			|| lop === "" || sdt === "" || sdt === "" || ngaydi === ""
			|| file === "") {
		document.getElementById("message").innerHTML = "Vui lòng nhập đầy đủ các trường dữ liệu!";
		return false;
	} else if (ngaysinh != "") {
		if (ngaysinh.indexOf("-") == -1) {
			document.getElementById("message").innerHTML = "Sai đinh dạng ngày sinh, vui lòng nhập lại theo YYYY-MM-DD!";
			return false;
		}

		date1 = ngaysinh.split("-");
		year1 = parseInt(date1[0]);
		month1 = parseInt(date1[1]);
		day1 = parseInt(date1[2]);

		if (isNaN(day1) || isNaN(month1) || isNaN(year1)) {
			document.getElementById("message").innerHTML = "Dữ liệu 'ngày sinh' sinh không phải là số, xin thử lại!";
			return false;
		}

		date2 = new Date(year1 + "/" + month1 + "/" + day1);
		day2 = date2.getDate();
		month2 = date2.getMonth() + 1;
		year2 = date2.getFullYear();

		if (year2 != year1 || month2 != month1 || day2 != day1) {
			document.getElementById("message").innerHTML = "Sai đinh dạng 'ngày sinh', vui lòng nhập lại theo YYYY-MM-DD!";
			return false;
		}

	} else if (ngaydi != "") {
		if (ngaydi.indexOf("-") == -1) {
			document.getElementById("message").innerHTML = "Sai đinh dạng ngày sinh, vui lòng nhập lại theo YYYY-MM-DD!";
			return false;
		}

		date1 = ngaydi.split("-");
		year1 = parseInt(date1[0]);
		month1 = parseInt(date1[1]);
		day1 = parseInt(date1[2]);

		if (isNaN(day1) || isNaN(month1) || isNaN(year1)) {
			document.getElementById("message").innerHTML = "Dữ liệu 'ngày sinh' sinh không phải là số, xin thử lại!";
			return false;
		}

		date2 = new Date(year1 + "/" + month1 + "/" + day1);
		day2 = date2.getDate();
		month2 = date2.getMonth() + 1;
		year2 = date2.getFullYear();

		if (year2 != year1 || month2 != month1 || day2 != day1) {
			document.getElementById("message").innerHTML = "Sai đinh dạng 'ngày sinh', vui lòng nhập lại theo YYYY-MM-DD!";
			return false;
		}
	} else if (isNaN(mssv)) {
		document.getElementById("message").innerHTML = "Mã số sinh viên phải là số!";
		return false;
	}
}

function deleteValidate() {
	var mssv = document.forms["delete"]["mssvdelete"].value;
	if (mssv === "") {
		document.getElementById("message").innerHTML = "Vui lòng nhập mã số sinh viên!";
		return false;
	} else if (mssv != "") {
		if (isNaN(mssv)) {
			document.getElementById("message").innerHTML = "Mã số sinh viên phải là một số!";
			return false;
		}
	}
}

function changeValidate() {
	var mssv = document.forms["changeroom"]["mssvchang"].value;
	if (mssv === "") {
		document.getElementById("message").innerHTML = "Vui lòng nhập mã số sinh viên!";
		return false;
	} else if (mssv != "") {
		if (isNaN(mssv)) {
			document.getElementById("message").innerHTML = "Mã số sinh viên phải là một số!";
			return false;
		}
	}
}
function findValidate() {
	var mssv = document.forms["find"]["mssv"].value;
	if (mssv === "") {
		document.getElementById("message").innerHTML = "Vui lòng nhập mã số sinh viên!";
		return false;
	} else if (mssv != "") {
		if (isNaN(mssv)) {
			document.getElementById("message").innerHTML = "Mã số sinh viên phải là một số!";
			return false;
		}
	}
}

function ktklValidate(){
	var mssv = document.forms["ktkl"]["mssv"].value;
	var mode = document.forms["ktkl"]["mode"].value;
	var content = document.forms["ktkl"]["nd"].value;
	if(mssv === "" || mode === "" || content === ""){
		if(mode === ""){document.getElementById("message").innerHTML = "Xin nhập mã số sinh viên!";}
		else if(mssv === ""){document.getElementById("message").innerHTML = "Xin chọn mục tương ứng!";}
		else if(content === ""){document.getElementById("message").innerHTML = "Xin nhập nội dung!";}
		return false;
	}
	if(isNaN(mssv)){document.getElementById("message").innerHTML = "Xin nhập mã số sinh viên là một số!";return false;}
}