function guestcase(){
    if (document.getElementById("addguest").checked) {
        document.getElementById("addguesttb").hidden = false;
        document.getElementById("checkguesttb").hidden = true;
        document.getElementById("deleteguesttb").hidden = true;
        document.getElementById("viewlist").hidden = true;
    } else if (document.getElementById("checkguest").checked) {
          document.getElementById("addguesttb").hidden = true;
        document.getElementById("checkguesttb").hidden = false;
        document.getElementById("deleteguesttb").hidden = true;

    } else if(document.getElementById("deleteguest").checked){
         document.getElementById("addguesttb").hidden = true;
        document.getElementById("checkguesttb").hidden = true;
        document.getElementById("deleteguesttb").hidden = false;
         document.getElementById("viewlist").hidden = true;
    } 
}
function load(){
    document.forms["addguesttb"]["makhach"].value = document.forms["addguesttb"]["mnvSrc"].value;
     var id = document.getElementById("id").value;
    if(id==="view"){
        document.getElementById("checkguest").checked = true;
        document.getElementById("viewlist").hidden = false;
    }else if(id === "add"){
        document.getElementById("addguest").checked = true;
    }else if(id === "delete"){
        document.getElementById("deleteguest").checked = true;
    }
    guestcase();
}

function SV(){
    document.forms["view"]["mssv"].value = document.forms["view"]["mssvview"].value;
    document.forms["addguesttb"]["mssv"].value = document.forms["addguesttb"]["selectmssv"].value;
    document.forms["delete"]["makhach"].value = document.forms["delete"]["smakhach"].value;
    
   var type = document.getElementById("type").value;
   if(type === "only"){
       document.getElementById("makhach").disabled= false;
       document.getElementById("smakhach").disabled= false;
   }else{
        document.getElementById("makhach").disabled= true;
        document.getElementById("smakhach").disabled= true;
   }
   
   
}

function addValidate(){
	var makhach = document.forms["addguesttb"]["makhach"].value;
	var tenkhach = document.forms["addguesttb"]["tenkhach"].value;
	var cmnd = document.forms["addguesttb"]["cmnd"].value;
	var ngayo = document.forms["addguesttb"]["ngayo"].value;
	var ngaydi = document.forms["addguesttb"]["ngaydi"].value;
	var mssv = document.forms["addguesttb"]["mssv"].value;
	
	if(makhach === "" || tenkhach === "" || cmnd === "" || ngayo === "" || ngaydi === "" || mssv === ""){
		if(makhach === ""){document.getElementById("message").innerHTML = "Xin nhập mã khách";}
		else if(tenkhach === ""){document.getElementById("message").innerHTML = "Xin nhập tên khách";}
		else if(cmnd === ""){document.getElementById("message").innerHTML = "Xin nhập chứng minh nhân dân của khách";}
		else if(ngayo === ""){document.getElementById("message").innerHTML = "Xin nhập ngày ở";}
		else if(ngaydi === ""){document.getElementById("message").innerHTML = "Xin nhập ngày đi";}
		else {document.getElementById("message").innerHTML = "Xin nhập mã số sinh viên";}
		return false;
	}else if(ngaydi != ""){
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
	}else if(ngayo != ""){
		if (ngayo.indexOf("-") == -1) {
			document.getElementById("message").innerHTML = "Sai đinh dạng ngày sinh, vui lòng nhập lại theo YYYY-MM-DD!";
			return false;
		}

		date1 = ngayo.split("-");
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
	}else if(makhach != ""){
		if(isNaN(makhach)){document.getElementById("message").innerHTML = "Xin nhập mã khách là số"; return false;}
	}else if(cmnd != ""){
		if(isNaN(cmnd)){document.getElementById("message").innerHTML = "Xin nhập chứng minh nhân dân là số"; return false;}
	}else if(mssv != ""){
		if(isNaN(mssv)){document.getElementById("message").innerHTML = "Xin nhập mã số sinh viên là số"; return false;}
	}
}

function deleteValidate(){
	var msk = document.forms["delete"]["makhach"].value;
	if(msk === ""){document.getElementById("message").innerHTML = "Xin nhập mã khách"; return false;}
	if(isNaN(msk)){document.getElementById("message").innerHTML = "Xin nhập mã khách là số"; return false;}
	
}