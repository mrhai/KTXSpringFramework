function addorchangeAction() {

       if(document.getElementById("add").checked){
           document.getElementById("addsv").hidden= false;
           document.getElementById("deletesv").hidden= true;
           document.getElementById("changeroom").hidden= true;
           document.getElementById("list").hidden= true;
           document.getElementById("bill").hidden= true;
       }else if(document.getElementById("delete").checked){
          document.getElementById("addsv").hidden= true;
           document.getElementById("deletesv").hidden= false;
           document.getElementById("changeroom").hidden= true;
           document.getElementById("list").hidden= true;
           document.getElementById("bill").hidden= true;
       }else if(document.getElementById("change").checked){
           document.getElementById("addsv").hidden= true;
           document.getElementById("deletesv").hidden= true;
           document.getElementById("changeroom").hidden= false;
           document.getElementById("list").hidden= true;
           document.getElementById("bill").hidden= true;
       }else if(document.getElementById("studenlist").checked){
    	   document.getElementById("addsv").hidden= true;
           document.getElementById("deletesv").hidden= true;
           document.getElementById("changeroom").hidden= true;
           document.getElementById("list").hidden= false;
           document.getElementById("bill").hidden= true;
       }else if(document.getElementById("arrears").checked){
    	   document.getElementById("addsv").hidden= true;
           document.getElementById("deletesv").hidden= true;
           document.getElementById("changeroom").hidden= true;
           document.getElementById("list").hidden= true;
           document.getElementById("bill").hidden= false;
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
      }else if(id === "list"){
    	  document.getElementById("studenlist").checked = true;
      }
      addorchangeAction();
}

function selectSV(){
    document.forms["delete"]["mssvdelete"].value = document.forms["delete"]["mssv"].value;
    document.forms["changeroom"]["mssvchang"].value = document.forms["changeroom"]["mssv"].value;
}

