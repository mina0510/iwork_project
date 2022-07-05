
$(document).ready(veritfyToken());

setInterval(veritfyToken, 10 * 60 * 1000);

function veritfyToken() {
  if (document.cookie == "") {
    alert("請先登入");
    parent.location.href = "/";
  };
  var userToken = JSON.stringify({
    "userToken": getCookie()
  });
  $.ajax({
    type: "post",
    url: "/security/verifyToken",
    data: userToken,
    contentType: 'application/json',
    success: function (data) {
      if (data.state == '201') {
        alert("登入時間過長，已自動登出");
        deleteCookie();
        parent.location.href = "/";
      }
    }
  })
}

function logOut() {
  if (confirm("你確定登出嗎？")) {
    var userToken = JSON.stringify({
      "userToken": getCookie()
    });
    $.ajax({
      type: "post",
      url: "/security/logOut",
      contentType: 'application/json',
      data: userToken,
      success: function () {
        deleteCookie();
        window.location.href = "/";
      }
    });
  }
}

function deleteCookie() {
  console.log("delete");
  var cookies = document.cookie.split(";");
  for (var i = 0; i < cookies.length; i++) {
    var cookie = cookies[i];
    var eqPos = cookie.indexOf("=");
    var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
    document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
  }
}

function getCookie() {
  var cookie = document.cookie.split('=');
  return cookie[1].toString();
}



