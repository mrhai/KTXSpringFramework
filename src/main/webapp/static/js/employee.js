function employeecase() {
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
