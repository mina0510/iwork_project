// 中間個人資訊
$(function () {
    var usertoken = JSON.stringify({ "userToken": getCookie() });
    $.ajax({
        type: "POST",
        url: "/emp/getUserInfo",
        data: usertoken,
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            userApart.innerText = data.apart;
            userNumber.innerText = data.empId;
            userName.innerText = data.empName;
            window.localStorage.setItem("myAddr",data.empAddr);
            document.getElementById('myNameImg').setAttribute('src', `${data.empPhoto}`);
        }
    })
})

function getCookie() {
    var cookie = document.cookie.split('=');
    return cookie[1].toString();
}

// 修改information(PUT) 
$(function () {    //先傳出值
    var usertoken = JSON.stringify({ "userToken": getCookie() });
    $.ajax({
        type: "POST",
        url: "/emp/getUserInfo",
        data: usertoken,
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            $("#inUserName").val(data.empName);
            $("#inUserPwd").val(data.empPwd);
            $("#inUserTel").val(data.empTel);
            $("#inUserPhone").val(data.empPhone);
            $("#inUserEmail").val(data.empEmail);
            $("#inUserAddr").val(data.empAddr);
        }
    })



})

function informationBtn() { //點下去後修改
    var usertoken = JSON.stringify({
        "userToken": getCookie(),
        "empName": $("#inUserName").val(),
        "empPwd": $("#inUserPwd").val(),
        "empTel": $("#inUserTel").val(),
        "empPhone": $("#inUserPhone").val(),
        "empEmail": $("#inUserEmail").val(),
        "empAddr": $("#inUserAddr").val(),
    });
    console.log(usertoken);
    $.ajax({
        type: "PUT",
        url: "/emp/updateUserInfo",
        data: usertoken,
        contentType: 'application/json',
        success: function () {
            alert("修改成功");
            window.location.href = './CMS_0.html';
        }
    });
}

function informationCancel() {
    var yes = confirm("確定要取消嗎?");
    if (yes) {
        window.location.href = './CMS_0.html';
    }
}

// 大頭照更換


function getCookie() {
    var cookie = document.cookie.split('=');
    return cookie[1].toString();
}



$(function () {
    var usertoken = JSON.stringify({ "userToken": getCookie() });
    $.ajax({
        type: "POST",
        url: "/security/checkAdm",
        data: usertoken,
        contentType: 'application/json',
        success: function (data) {
            //    console.log("HOOOOOOOOOOO")
            if (data.userAdm == "admin") {
                document.getElementById("AdminUse").style.visibility = "visible";
            } else {
                console.log("使用者")
            }
        }
    })
})


