
            function reset(){
                 document.getElementById("lblUsername").innerHTML = "";
                  document.getElementById("lblPassword").innerHTML = "";
            }
            function checkField() {
                reset();
                var user = document.forms["login"]["username"].value;
                var pass = document.forms["login"]["password"].value;
                 if(isNaN(user)){
                   document.getElementById("lblUsername").innerHTML = "<font color = 'red' >Vui lòng nhập tên đăng nhập là số!</font>";
                   return false;
               }
                if (user === "" || pass === "") {
                    if(user === "")
                     document.getElementById("lblUsername").innerHTML = "<font color = 'red' >Không được để trống!</font>";
                     if(pass === "")
                     document.getElementById("lblPassword").innerHTML = "<font color = 'red' >Không được để trống!</font>";
                    return false; 
                }
              
            }

