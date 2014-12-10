function vtcase(){
    if (document.getElementById("addvt").checked) {
        document.getElementById("nhap").hidden = false;
        document.getElementById("move").hidden = true;
        document.getElementById("repair").hidden = true;
        document.getElementById("viewlist").hidden = true;
        document.getElementById("history").hidden = true;
        document.getElementById("historyview").hidden = true;
        document.getElementById("failtb").hidden = true;
    } else if (document.getElementById("movevt").checked) {
        document.getElementById("move").hidden = false;
        document.getElementById("repair").hidden = true;
        document.getElementById("nhap").hidden = true;
        document.getElementById("history").hidden = true;
        document.getElementById("historyview").hidden = true;
        document.getElementById("failtb").hidden = true;

    } else if(document.getElementById("repairvt").checked){
         document.getElementById("nhap").hidden = true;
        document.getElementById("move").hidden = true;
        document.getElementById("repair").hidden = false;
        document.getElementById("history").hidden = true;
        document.getElementById("historyview").hidden = true;
        document.getElementById("failtb").hidden = true;
    } 
    else if(document.getElementById("historyRepair").checked){
        document.getElementById("nhap").hidden = true;
       document.getElementById("move").hidden = true;
       document.getElementById("repair").hidden = true;
       document.getElementById("history").hidden = false;
       document.getElementById("historyview").hidden = false;
       document.getElementById("failtb").hidden = true;
   } 
    else if(document.getElementById("fail").checked){
        document.getElementById("nhap").hidden = true;
       document.getElementById("move").hidden = true;
       document.getElementById("repair").hidden = true;
       document.getElementById("history").hidden = true;
       document.getElementById("historyview").hidden = true;
       document.getElementById("failtb").hidden = false;
   } 
}

function load(){
     var id = document.getElementById("id").value;
    if(id==="add"){
        document.getElementById("addvt").checked = true;
    }else if(id === "repair"){
        document.getElementById("repairvt").checked = true;
    }else if(id === "history"){
        document.getElementById("historyRepair").checked = true;
    }else if(id === "hong"){
    	document.getElementById("fail").checked = true;
    }
    vtcase();
}

function moveValidate(){
	var sl = document.forms["move"]["sl"].value;
	if(sl === ""){document.getElementById("message").innerHTML = "Xin nhập số lượng!";return false;}
	if(isNaN(sl)){document.getElementById("message").innerHTML = "Xin nhập số lượng là số!";return false;}
}

function repairValidate(){
	var price = document.forms["repair"]["gia"].value;
	if(price === ""){document.getElementById("message").innerHTML = "Xin nhập giá sữa chữa!";return false;}
	if(isNaN(price)){document.getElementById("message").innerHTML = "Xin nhập giá là số";return false;}
}