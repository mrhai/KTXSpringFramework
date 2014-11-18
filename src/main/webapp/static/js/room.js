function roomcase() {
   
    if (document.getElementById("emptyroom").checked) {
        document.getElementById("emptyroomtb").hidden = false;
        document.getElementById("addroomtb").hidden = true;
        document.getElementById("deleteroomtb").hidden = true;
        document.getElementById("addregiontb").hidden = true;
        document.getElementById("deleteregiontb").hidden = true;
        document.getElementById("changeemployeetb").hidden = true;
    } else if (document.getElementById("addroom").checked) {
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("addroomtb").hidden = false;
        document.getElementById("deleteroomtb").hidden = true;
        document.getElementById("addregiontb").hidden = true;
        document.getElementById("deleteregiontb").hidden = true;
        document.getElementById("changeemployeetb").hidden = true;
    } else if (document.getElementById("deleteroom").checked) {
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("addroomtb").hidden = true;
        document.getElementById("deleteroomtb").hidden = false;
        document.getElementById("addregiontb").hidden = true;
        document.getElementById("deleteregiontb").hidden = true;
        document.getElementById("changeemployeetb").hidden = true;
    } else if (document.getElementById("addregion").checked) {
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("addroomtb").hidden = true;
        document.getElementById("deleteroomtb").hidden = true;
        document.getElementById("addregiontb").hidden = false;
        document.getElementById("deleteregiontb").hidden = true;
        document.getElementById("changeemployeetb").hidden = true;
    } else if (document.getElementById("deleteregion").checked) {
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("addroomtb").hidden = true;
        document.getElementById("deleteroomtb").hidden = true;
        document.getElementById("addregiontb").hidden = true;
        document.getElementById("deleteregiontb").hidden = false;
        document.getElementById("changeemployeetb").hidden = true;
    } else {
        document.getElementById("emptyroomtb").hidden = true;
        document.getElementById("addroomtb").hidden = true;
        document.getElementById("deleteroomtb").hidden = true;
        document.getElementById("addregiontb").hidden = true;
        document.getElementById("deleteregiontb").hidden = true;
        document.getElementById("changeemployeetb").hidden = false;
    }
}


