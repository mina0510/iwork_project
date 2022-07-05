// 限定只能撈三筆資料
function displayPunchInfo(userPunchData) {
    if (userPunchData[0] == null) {
        var precord = $(`<tr>
                            <td colspan="2" value="200" id="noWork0">
                                    <h5>未有打卡記錄</h5>
                            </td>
                            </tr>`);
        precord.appendTo("#punchRecord");
    } else {
        $("#punchRecord").empty();
        if (userPunchData[0].status == "下班") {
            for (var i = 0; i < userPunchData.length; i += 2) {
                var precord = $(`<tr>
                                <td colspan="2">
                                    <h5>${userPunchData[i + 1].time.split(" ")[0]}</h5>
                                </td>
                            </tr>
                            <tr class="myTimeborder">
                                <td class="myTime">上班</td>
                                <td class="myTime" id="onWork${i}">${userPunchData[i + 1].time.split(" ")[1]}</td>
                            </tr>
                            <tr class="myTimeborder">
                                <td class="myTime" >下班</td>
                                <td class="myTime" value="200" id="noWork${i}">${userPunchData[i].time.split(" ")[1]}</td>
                            </tr>
                            <tr class="myTimeborder">
                              <td class="myTime" >打卡狀態</td>
                              <td class="myTime" id="state${i}"><span style="color:red;">${userPunchData[i].state}</span></td>
                            </tr>
                            <tr>
                                <td> <pre> </pre> </td>
                            </tr>`);
                precord.appendTo("#punchRecord");
            }
        } else {
            var precord = $(`<tr>
                            <td colspan="2">
                            <h5>${userPunchData[0].time.split(" ")[0]}</h5>
                            </td>
                        </tr>
                        <tr class="myTimeborder">
                            <td class="myTime">上班</td>
                            <td class="myTime" id="onWork0">${userPunchData[0].time.split(" ")[1]}</td>
                        </tr>
                        <tr class="myTimeborder">
                            <td class="myTime" >下班</td>
                            <td class="myTime" value="201" id="noWork0" >------</td>
                        </tr>
                        <tr class="myTimeborder">
                              <td class="myTime" >打卡狀態</td>
                              <td class="myTime" id="state0">${userPunchData[0].state}</td>
                        </tr>
                        <tr>
                             <td> <pre> </pre> </td>
                        </tr>`);
            precord.appendTo("#punchRecord");
            for (var i = 1; i < userPunchData.length - 1; i += 2) {
                var precord = $(`<tr>
                                <td colspan="2">
                                <h5>${userPunchData[i].time.split(" ")[0]}</h5>
                                </td>
                            </tr>
                            <tr class="myTimeborder">
                                <td class="myTime">上班</td>
                                <td class="myTime">${userPunchData[i + 1].time.split(" ")[1]}</td>
                            </tr>
                            <tr class="myTimeborder">
                                <td class="myTime">下班</td>
                                <td class="myTime">${userPunchData[i].time.split(" ")[1]}</td>
                            </tr>
                            <tr class="myTimeborder">
                              <td class="myTime" >打卡狀態</td>
                              <td class="myTime" id="state${i}">${userPunchData[i].state}</td>
                            </tr>
                            <tr>
                                <td> <pre> </pre> </td>
                            </tr>`);
                precord.appendTo("#punchRecord");
            }
        }
    }

}


function getCookie() {
    var cookie = document.cookie.split('=');
    return cookie[1].toString();
}

$(function () {
    var data = JSON.stringify({ "userToken": getCookie() });
    $.ajax({
        type: "post",
        url: "/punch/getInfo",
        data: data,
        contentType: 'application/json',
        dataType: 'json',
        success: function (data) {
            displayPunchInfo(data);
        }
    });
})


