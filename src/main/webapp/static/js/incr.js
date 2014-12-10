function ins(){
    if (document.getElementById("sv").checked) {
        document.getElementById("svtb").hidden = false;
        document.getElementById("nvtb").hidden = true;
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("guesttb").hidden = true;
        document.getElementById("devicetb").hidden = true;
    } else if (document.getElementById("nv").checked) {
    	document.getElementById("svtb").hidden = true;
        document.getElementById("nvtb").hidden = false;
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("guesttb").hidden = true;
        document.getElementById("devicetb").hidden = true;
    }else if(document.getElementById("emptyroom").checked){
    	document.getElementById("svtb").hidden = true;
        document.getElementById("nvtb").hidden = true;
        document.getElementById("emptyroomtb").hidden = false;
        document.getElementById("guesttb").hidden = true;
        document.getElementById("devicetb").hidden = true;
    }else if(document.getElementById("guest").checked){
    	document.getElementById("svtb").hidden = true;
        document.getElementById("nvtb").hidden = true;
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("guesttb").hidden = false;
        document.getElementById("devicetb").hidden = true;
    }
    else if(document.getElementById("device").checked){
    	document.getElementById("svtb").hidden = true;
        document.getElementById("nvtb").hidden = true;
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("guesttb").hidden = true;
        document.getElementById("devicetb").hidden = false;
        
       
    }
}

function load(){
     var id = document.getElementById("id").value;
    if(id==="vt"){
    	document.getElementById("device").checked = true;
    }else if(id === "checkout"){
    	
    }
    ins();
}