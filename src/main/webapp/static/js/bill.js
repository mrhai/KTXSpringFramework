function billCare(){
    if (document.getElementById("create").checked) {
        document.getElementById("createtb").hidden = false;
        document.getElementById("checkouttb").hidden = true;
       
    } else if (document.getElementById("checkout").checked) {
    	document.getElementById("createtb").hidden = true;
        document.getElementById("checkouttb").hidden = false;

    }
}

function load(){
     var id = document.getElementById("id").value;
    if(id==="create"){
    	document.getElementById("create").checked = true;
    }else if(id === "checkout"){
        document.getElementById("checkout").checked = true;
    }
    billCare();
}
function createValidate(){
	var sd = document["bill"]["sodien"].value;
	var sn = document["bill"]["sonuoc"].value;
	
	if(sd === "" || sn === ""){
		if(sd === ""){document.getElementById("message").innerHTML = "Xin nhập số điện!"; return false;}
		if(sn === ""){document.getElementById("message").innerHTML = "Xin nhập số nước!"; return false;}
	}else{
		if(isNaN(sd)){document.getElementById("message").innerHTML = "Xin nhập số điện là số!"; return false;}
		if(isNaN(sn)){document.getElementById("message").innerHTML = "Xin nhập số nước là số!"; return false;}
	}
}