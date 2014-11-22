function vtcase(){
    if (document.getElementById("addvt").checked) {
        document.getElementById("nhap").hidden = false;
        document.getElementById("move").hidden = true;
        document.getElementById("repair").hidden = true;
        document.getElementById("viewlist").hidden = true;
        document.getElementById("history").hidden = true;
    } else if (document.getElementById("movevt").checked) {
        document.getElementById("move").hidden = false;
        document.getElementById("repair").hidden = true;
        document.getElementById("nhap").hidden = true;
        document.getElementById("history").hidden = true;

    } else if(document.getElementById("repairvt").checked){
         document.getElementById("nhap").hidden = true;
        document.getElementById("move").hidden = true;
        document.getElementById("repair").hidden = false;
        document.getElementById("history").hidden = true;
    } 
    else if(document.getElementById("historyRepair").checked){
        document.getElementById("nhap").hidden = true;
       document.getElementById("move").hidden = true;
       document.getElementById("repair").hidden = true;
       document.getElementById("history").hidden = false;
       
   } 
}

function load(){
     var id = document.getElementById("id").value;
    if(id==="add"){
        document.getElementById("addvt").checked = true;
    }else if(id === "add"){
        document.getElementById("addguest").checked = true;
    }else if(id === "delete"){
        document.getElementById("deleteguest").checked = true;
    }
    vtcase();
}