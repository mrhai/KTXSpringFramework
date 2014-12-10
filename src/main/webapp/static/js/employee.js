function employeecase() {
	if (document.getElementById("id").value === "") {
		document.getElementById("message").innerHTML = "";
	}
	if (document.getElementById("addemployee").checked) {
		document.getElementById("addemptb").hidden = false;
		document.getElementById("deleteemptb").hidden = true;
		document.getElementById("changesalarytb").hidden = true;
		document.getElementById("room").hidden = true;
		document.getElementById("listmanager").hidden = true;
	} else if (document.getElementById("deleteemployee").checked) {
		document.getElementById("addemptb").hidden = true;
		document.getElementById("deleteemptb").hidden = false;
		document.getElementById("changesalarytb").hidden = true;
		document.getElementById("room").hidden = true;
		document.getElementById("listmanager").hidden = true;
	} else if (document.getElementById("changesalary").checked) {
		document.getElementById("addemptb").hidden = true;
		document.getElementById("deleteemptb").hidden = true;
		document.getElementById("changesalarytb").hidden = false;
		document.getElementById("room").hidden = true;
		document.getElementById("listmanager").hidden = true;
	} else if (document.getElementById("employee").checked) {
		document.getElementById("addemptb").hidden = true;
		document.getElementById("deleteemptb").hidden = true;
		document.getElementById("changesalarytb").hidden = true;
		document.getElementById("room").hidden = false;
		document.getElementById("listmanager").hidden = true;
	} else if (document.getElementById("employeelist").checked) {
		document.getElementById("addemptb").hidden = true;
		document.getElementById("deleteemptb").hidden = true;
		document.getElementById("changesalarytb").hidden = true;
		document.getElementById("room").hidden = true;
		document.getElementById("listmanager").hidden = false;

	}
	document.getElementById("id").value = "";

}

function load() {
	document.forms["add"]["mnv"].value = document.forms["add"]["mnvSrc"].value;
	var id = document.getElementById("id").value;
	if (id === "change") {
		document.getElementById("changesalary").checked = true;
	} else if (id === "add") {
		document.getElementById("addemployee").checked = true;
	} else if (id === "delete") {
		document.getElementById("deleteemployee").checked = true;
	} else if (id === "room") {
		document.getElementById("employee").checked = true;
	}
	employeecase();
}

function select() {
	document.forms["delete"]["mnv"].value = document.forms["delete"]["smnv"].value;
}

function addValidate() {
	var msnv = document.forms["add"]["mnv"].value;
	var tennv = document.forms["add"]["tennv"].value;
	var ngaysinh = document.forms["add"]["ngaysinh"].value;
	var diachi = document.forms["add"]["diachi"].value;

	if (msnv === "" || tennv === "" || ngaysinh === "" || diachi === "") {
		if (msnv === "") {
			document.getElementById("message").innerHTML = "Xin nhập mã số nhân viên";
		} else if (tennv === "") {
			document.getElementById("message").innerHTML = "Xin nhập tên nhân viên";
		} else if (ngaysinh === "") {
			document.getElementById("message").innerHTML = "Xin nhập ngày sinh nhân viên";
		} else {
			document.getElementById("message").innerHTML = "Xin nhập địa chỉ nhân viên";
		}
		return false;
	}

	if (isNaN(msnv)) {
		document.getElementById("message").innerHTML = "Mã số nhân viên phải là một số!";
		return false;
	}
	if (ngaysinh != "") {
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
	}
}

function deleteValidate() {
	var msnv = document.forms["delete"]["mnv"].value;

	if (msnv === "") {
		document.getElementById("message").innerHTML = "Xin nhập mã số nhân viên";
		return false;
	} else {
		if (isNaN(msnv)) {
			document.getElementById("message").innerHTML = "Mã số nhân viên phải là một số!";
			return false;
		}
	}
}

function roomValidate() {
	var nv = document.forms["room"]["mnv"].value;
	if (nv === "") {
		document.getElementById("message").innerHTML = "Xin chọn mã nhân viên!";
		return false;
	}

}