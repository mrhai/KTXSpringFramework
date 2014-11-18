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
   var type = document.getElementById("type").value;
   if(type === "only"){
       document.getElementById("makhach").disabled= false;
   }else{
        document.getElementById("makhach").disabled= true;
   }
}