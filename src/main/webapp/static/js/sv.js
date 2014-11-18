function addorchangeAction() {

       if(document.getElementById("add").checked){
           document.getElementById("addsv").hidden= false;
           document.getElementById("deletesv").hidden= true;
           document.getElementById("changeroom").hidden= true;
       }else if(document.getElementById("delete").checked){
          document.getElementById("addsv").hidden= true;
           document.getElementById("deletesv").hidden= false;
           document.getElementById("changeroom").hidden= true;
       }else if(document.getElementById("change").checked){
           document.getElementById("addsv").hidden= true;
           document.getElementById("deletesv").hidden= true;
           document.getElementById("changeroom").hidden= false;
       }
}

function load(){
      var id = document.getElementById("id").value;
      if(id === "add"){
          document.getElementById("add").checked = true;
      }else if(id === "delete"){
          document.getElementById("delete").checked = true;
      }else if(id === "change"){
          document.getElementById("change").checked = true;
      }
      addorchangeAction();
}

function selectSV(){
    document.forms["delete"]["mssvdelete"].value = document.forms["delete"]["mssv"].value;
}

