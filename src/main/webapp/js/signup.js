var accountflog = false;
var passwordflog = false;

var b = document.getElementById("account")
b.onblur = function test() {
    var reg = /^\w{8,13}$/;
    var userName = this.value
    if (reg.test(userName)){
        document.getElementById("userName_err1").style.display = "";
    }else {
        document.getElementById("userName_err1").style.display = "none";
        return
    }
    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xhttp.open("GET", "http://localhost:8080/news_sys_war_exploded/selectUserServlet?userName="+userName);
    xhttp.send();

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == "true"){
                document.getElementById("userName_err").style.display = ""
                accountflog = true
            }else {
                document.getElementById("userName_err").style.display = "none"
                accountflog = false
            }
        }
    };
    if (accountflog && passwordflog){
        document.getElementById("register").style.disabled = ""
    }
}

