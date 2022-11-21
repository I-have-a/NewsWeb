let accountflog = false;//账号判定
let passwordflog = false;//密码判定
let passwordConfirmFlog = false;//密码对比判定
let emailflog = false;//邮箱判定

let account = document.getElementById("account");
let password = document.getElementById("password");
let password_1 = document.getElementById("password_1");
let email = document.getElementById("email");

account.onblur = function accountOnblur() {
    let reg = /^\w{8,13}$/;
    let userName = this.value;
    if (reg.test(userName)) {
        document.getElementById("userName_err2").style.display = "none";
    } else {
        document.getElementById("userName_err2").style.display = "";
        accountflog = false
        return
    }
    /*
        ajax数据
     */
    let xhttp;
    xhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

    //发起请求
    xhttp.open("GET", "http://localhost:8080/news_sys_war_exploded/User/SelectUser?userName=" + userName);
    xhttp.send();

    //响应请求
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (this.responseText === "true") {
                document.getElementById("userName_err").style.display = ""
                accountflog = false
            } else {
                document.getElementById("userName_err").style.display = "none"
                accountflog = true
            }
            document.getElementById("emailE").value = this.responseText
        }
    };
    //判定一次表单信息
    new Confirm()
}

password.onblur = function passwordOnblur() {
    let reg = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9 _]{8,13}$/;
    if (reg.test(this.value)) {
        document.getElementById("password_err").style.display = "none"
        passwordflog = true
    } else {
        document.getElementById("password_err").style.display = ""
        passwordflog = false
    }
    new Confirm()
}

password_1.onblur = function password_1Onblur() {
    if (this.value !== password.value) {
        document.getElementById("password_1_err").style.display = ""
        passwordConfirmFlog = false
    } else {
        document.getElementById("password_1_err").style.display = "none"
        passwordConfirmFlog = true
    }
    new Confirm()
}

email.onblur = function emailOnblur() {
    let reg = /^[1-9]([0-9]{4,10})@qq\.com$/;
    if (reg.test(this.value)) {
        document.getElementById("email_err").style.display = "none"
    } else {
        document.getElementById("email_err").style.display = ""
        emailflog = false
        return
    }
    let xhttp;
    xhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

    xhttp.open("GET", "http://localhost:8080/news_sys_war_exploded/User/SelectEmail?email=" + this.value);
    xhttp.send();

    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (this.responseText === "true") {
                document.getElementById("email_err1").style.display = ""
                emailflog = false
            } else {
                document.getElementById("email_err1").style.display = "none"
                emailflog = true
            }
        }
    };
    new Confirm()
}

let getEmail = document.getElementById("getEmail");
/*
    获取验证码
 */
getEmail.onclick = function () {
    let email = document.getElementById("email").value;
    let xhttp;
    xhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    xhttp.open("GET", "http://localhost:8080/news_sys_war_exploded/User/GetEmail?email=" + email);
    xhttp.send();
}

/**
 * 表单总判定
 */
function Confirm() {
    if (accountflog && passwordflog && passwordConfirmFlog && emailflog) {
        document.getElementById("register").removeAttribute("disabled")
    } else {
        document.getElementById("register").setAttribute("disabled", "disabled")
    }
}