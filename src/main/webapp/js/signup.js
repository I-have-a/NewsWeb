var accountflog = false;
var passwordflog = false;
var passwordConfirmFlog = false;
var emailflog = false;

var account = document.getElementById("account")
var password = document.getElementById("password")
var password_1 = document.getElementById("password_1")
var email = document.getElementById("email")

account.onblur = function accountOnblur() {
    var reg = /^\w{8,13}$/;
    var userName = this.value
    if (reg.test(userName)) {
        document.getElementById("userName_err2").style.display = "none";
    } else {
        document.getElementById("userName_err2").style.display = "";
        accountflog = false
        return
    }
    var xhttp;
    xhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

    xhttp.open("GET", "http://localhost:8080/news_sys_war_exploded/selectUserServlet?userName=" + userName);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            if (this.responseText == "true") {
                document.getElementById("userName_err").style.display = ""
                accountflog = false
            } else {
                document.getElementById("userName_err").style.display = "none"
                accountflog = true
            }
            document.getElementById("emailE").value = this.responseText
        }
    };
    Confirm()
}

password.onblur = function passwordOnblur() {
    var reg = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9 _]{8,13}$/;
    if (reg.test(this.value)) {
        document.getElementById("password_err").style.display = "none"
        passwordflog = true
    } else {
        document.getElementById("password_err").style.display = ""
        passwordflog = false
    }
    Confirm()
}

password_1.onblur = function password_1Onblur() {
    if (this.value != password.value) {
        document.getElementById("password_1_err").style.display = ""
        passwordConfirmFlog = false
    } else {
        document.getElementById("password_1_err").style.display = "none"
        passwordConfirmFlog = true
    }
    Confirm()
}

email.onblur = function emailOnblur() {
    var reg = /^[1-9]([0-9]{4,10})@qq\.com$/;
    if (reg.test(this.value)) {
        document.getElementById("email_err").style.display = "none"
        emailflog = true
    } else {
        document.getElementById("email_err").style.display = ""
        emailflog = false
    }
    Confirm()
}

var getEmail = document.getElementById("getEmail")
getEmail.onclick = function () {
    var email = document.getElementById("email").value
    var xhttp;
    xhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    console.log("------------" + email + "-------------")
    xhttp.open("GET", "http://localhost:8080/news_sys_war_exploded/getEmailServlet?email=" + email);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText)
        }
    };
}

function Confirm() {
    if (accountflog && passwordflog && passwordConfirmFlog && emailflog) {
        document.getElementById("register").removeAttribute("disabled")
    } else {
        document.getElementById("register").setAttribute("disabled", "disabled")
    }
}