// CMS_2.出勤系統

// CMS_2Leaverecorded 查詢

function getCookie() {
    var cookie = document.cookie.split('=');
    return cookie[1].toString();
}

// 全部紀錄(頁面1)
$(function () {
    var usertoken = JSON.stringify({ "userToken": getCookie() });
    $.ajax({
        url: "http://localhost:8080/Attendance/findPersonData",
        type: "POST",
        data: usertoken,
        contentType: 'application/json',
        success: function (data) {
            var i = 1;
            $.each(data, function () {
                if (this.approvalId == 1) {
                    var attendancerecord1 = $(`<tr class="staffWord" id="${this.attendanceId}">
                           <td class="tableStyle">${this.attendanceId}</td>
                           <td class="tableStyle">${this.leaveType}</td>
                           <td class="tableStyle">${this.startDate}<br> ~ <br>${this.endDate}</td>
                           <td class="tableStyle">${this.hours}</td>
                           <td class="tableStyle">${this.approvalType}</td>
                           <td class="tableStyle">
                           <span class="icon-input-btn">
                                <i class="glyphicon glyphicon-pencil" id="glyphiconPencil"></i>
                                <input type="button" value="修改" class="function" onClick="leaveEdit(${i})">
                            </span>&nbsp;&nbsp;
                            <span class="icon-input-btn">
                                <i class="glyphicon glyphicon-trash" id="glyphiconTrash"></i>
                                <input type="button" value="刪除" class="function deleteB" onClick="attDel(${i})";>
                            </span>&nbsp;&nbsp;
                                <input type="button" onClick="apply(${i})" value="申請人審核" class="function">&nbsp;&nbsp;
                                <input type="button" onClick="reviewProcess(${i})" value="審核歷程" class="function review">
                           </td>
                       </tr>`);
                    attendancerecord1.appendTo("#attendanceTable1");
                } else {
                    var attendancerecord2 = $(`<tr class="staffWord" id="${this.attendanceId}">
                           <td class="tableStyle">${this.attendanceId}</td>
                           <td class="tableStyle">${this.leaveType}</td>
                           <td class="tableStyle">${this.startDate}<br> ~ <br>${this.endDate}</td>
                           <td class="tableStyle">${this.hours}</td>
                           <td class="tableStyle">${this.approvalType}</td>
                           <td class="tableStyle">
                                <input type="button" onClick="reviewProcess(${i})" value="審核歷程" class="function review">
                           </td>
                       </tr>`);
                    attendancerecord2.appendTo("#attendanceTable1");
                }
                i++;
            })
        }
    })
})



//搜尋類別紀錄(頁面1)
$("#leaveEditBtn1").on("click", function () {
    $(".staffWord").empty();
    var usertoken = JSON.stringify({ "userToken": getCookie() });
    $.ajax({
        url: "http://localhost:8080/Attendance/findPersonData",
        type: "POST",
        data: usertoken,
        contentType: 'application/json',
        success: function (data) {
            var i = 1;
            $.each(data, function () {
                if (this.leaveId == $("#leaves").val() || $("#leaves").val() == "") {
                    if (data.approvalId == 1) {
                        var attendancerecord3 = $(`<tr class="staffWord" id="${this.attendanceId}">
                               <td class="tableStyle">${this.attendanceId}</td>
                               <td class="tableStyle">${this.leaveType}</td>
                               <td class="tableStyle">${this.startDate}<br> ~ <br>${this.endDate}</td>
                               <td class="tableStyle">${this.hours}</td>
                               <td class="tableStyle">${this.approvalType}</td>
                               <td class="tableStyle">
                               <span class="icon-input-btn">
                                    <i class="glyphicon glyphicon-pencil" id="glyphiconPencil"></i>
                                    <input type="button" value="修改" class="function" onClick="leaveEdit(${i})">
                                </span>&nbsp;&nbsp;
                                <span class="icon-input-btn">
                                    <i class="glyphicon glyphicon-trash" id="glyphiconTrash"></i>
                                    <input type="button" value="刪除" class="function deleteB" onClick="attDel(${i})";>
                                </span>&nbsp;&nbsp;
                                    <input type="button" onClick="apply(${i})" value="申請人審核" class="function">&nbsp;&nbsp;
                                    <input type="button" onClick="reviewProcess(${i})" value="審核歷程" class="function review">
                               </td>
                           </tr>`);
                        attendancerecord3.appendTo("#attendanceTable1");
                    } else {
                        var attendancerecord4 = $(`<tr class="staffWord" id="${this.attendanceId}">
                               <td class="tableStyle">${this.attendanceId}</td>
                               <td class="tableStyle">${this.leaveType}</td>
                               <td class="tableStyle">${this.startDate}<br> ~ <br>${this.endDate}</td>
                               <td class="tableStyle">${this.hours}</td>
                               <td class="tableStyle">${this.approvalType}</td>
                               <td class="tableStyle">
                                    <input type="button" onClick="reviewProcess(${i})" value="審核歷程" class="function review">
                               </td>
                           </tr>`);
                        attendancerecord4.appendTo("#attendanceTable1");
                    }
                    i++;
                }
            })
        }
    })
})


// 出勤新增(頁面2)
$("#sendAnnouncement").on("click", function () {
    let evestart = moment($('#startDates').val()).format('YYYY-MM-DD HH:mm:ss');
    let eveend = moment($('#endDates').val()).format('YYYY-MM-DD HH:mm:ss');
    let nowdate = moment(new Date).format('YYYY-MM-DD HH:mm:ss');
    var usertoken = JSON.stringify([{
        "userToken": getCookie(),
        "leaveId": $("#leaves").find("option:selected").val(),
        "contentText": $("#contentText").val(),
        "startDate": evestart,
        "endDate": eveend,
        "hours": $("#hours").val(),
        "approvalId": "1",
        "createDate": nowdate
    }]);
    $.ajax({
        url: "http://localhost:8080/Attendance/insert/",
        type: "POST",
        data: usertoken,
        contentType: "application/json",
        success: function () {
            if (confirm("確認完成")) {
                window.location.href = "./CMS_2Leaverecorded.html";
            }
        }
    })
})



// 出勤修改(頁面4)
function leaveEdit(i) {
    window.localStorage.setItem("attend", document.getElementsByTagName("tr")[i].getAttribute('id'));
    window.location.href = "./CMS_2_4.html";
}







// 出勤刪除(頁面1)
function attDel(i) {
    var attDelSet = JSON.stringify([{
        attendanceId: document.getElementsByTagName("tr")[i].getAttribute('id')
    }]);
    $.ajax({
        url: "http://localhost:8080/Attendance/delete/",
        type: "DELETE",
        data: attDelSet,
        contentType: "application/json",
        success: function () {
            if (confirm("刪除成功")) {
                window.location.href = './CMS_2Leaverecorded.html';
            }
        }
    })
}



// 申請人審核按鈕  (按下後 1.頁面1表格狀態會改變)
function apply(i) {

    var attpeople1 = JSON.stringify([{
        "userToken": getCookie(),
        attendanceId: document.getElementsByTagName("tr")[i].getAttribute('id'),
        approvalId: "3"
    }]);
    $.ajax({
        url: "http://localhost:8080/Attendance/updateapproval1/",
        type: "PUT",
        data: attpeople1,
        contentType: "application/json",
        success: function () {
            if (confirm("申請成功")) {
                window.location.href = './CMS_2Leaverecorded.html';
            }
        }
    })
    let nowdate = moment(new Date).format('YYYY-MM-DD HH:mm:ss');
    var attpeople2 = JSON.stringify([{
        attendanceId: document.getElementsByTagName("tr")[i].getAttribute('id'),
        attendanceDate: nowdate
    }]);
    $.ajax({
        url: "http://localhost:8080/Approval/newrank/",
        type: "POST",
        data: attpeople2,
        contentType: "application/json",
        success: function () {
            console.log("OK");
        }
    })
}


// 審核歷程按鈕  (彈跳視窗)/Approval/showrank
function reviewProcess(i) {
    var rankId = document.getElementsByTagName("tr")[i].getAttribute('id');
    $.ajax({
        url: "http://localhost:8080/Approval/showrank/",
        type: "GET",
        success: function (data) {
            $("#attendanceDate").text("");
            $("#approver1").text("");
            $("#dateApproved1").text("");
            $("#approver2").text("");
            $("#dateApproved2").text("");
            for (var i = 0; i < data.length; i++) {
                if (rankId == data[i].attendances.attendanceId) {
                    attendanceDate.innerText = data[i].attendanceDate;
                    approver1.innerText = data[i].approver1;
                    dateApproved1.innerText = data[i].dateApproved1;
                    approver2.innerText = data[i].approver2;
                    dateApproved2.innerText = data[i].dateApproved2;
                }
            }
            reviewdialog.showModal();
        }
    })
}


//審核記錄查詢 
$(function () {
    var usertoken1 = JSON.stringify({
        "userToken": getCookie()
    });
    var i = 1;
    $.ajax({     //第一層審核人員
        url: "http://localhost:8080/Attendance/appprovallist1",
        type: "POST",
        data: usertoken1,
        contentType: "application/json",
        success: function (data) {
            $.each(data, function () {

                var attendancerecord3 = $(` 
            <tr class="staffWord" id="${this.attendanceId}">
                <td class="tableStyle">${this.attendanceId}</td>
                <td class="tableStyle">${this.apart}</td>
                <td class="tableStyle">${this.empId}</td>
                <td class="tableStyle">${this.empName}</td>
                <td class="tableStyle">${this.leaveType}</td>
                <td class="tableStyle">${this.startDate}<br> ~ <br>${this.endDate}</td>
                <td class="tableStyle">${this.hours}</td>
                <td class="tableStyle"><span>${this.approvalType}</span></td>
                <td class="tableStyle">
                    <input type="button" value="審核通過" class="function mt-3" onClick="passBtn(${i})">&nbsp;&nbsp;
                    <input type="button" value="審核未通過" class="function mt-3" onClick="failBtn(${i})">&nbsp;&nbsp;
                </td>
            </tr>
                `)
                attendancerecord3.appendTo("#staffTable");
                i++;
            })
        }
    })


    $.ajax({     //第二層審核人員
        url: "http://localhost:8080/Attendance/appprovallist2/",
        type: "POST",
        data: usertoken1,
        contentType: "application/json",
        success: function (data) {
            $.each(data, function () {

                var attendancerecord4 = $(` 
            <tr class="staffWord" id="${this.attendanceId}">
                <td class="tableStyle">${this.attendanceId}</td>
                <td class="tableStyle">${this.apart}</td>
                <td class="tableStyle">${this.empId}</td>
                <td class="tableStyle">${this.empName}</td>
                <td class="tableStyle">${this.leaveType}</td>
                <td class="tableStyle">${this.startDate}<br> ~ <br>${this.endDate}</td>
                <td class="tableStyle">${this.hours}</td>
                <td class="tableStyle"><span>${this.approvalType}</span></td>
                <td class="tableStyle">
                    <input type="button" value="審核通過" class="function mt-3" onClick="passBtn(${i})">&nbsp;&nbsp;
                    <input type="button" value="審核未通過" class="function mt-3" onClick="failBtn(${i})">&nbsp;&nbsp;
                </td>
            </tr>
                `)
                attendancerecord4.appendTo("#staffTable");
                i++;
            })
        }
    })
})



// 審核通過按鈕
function passBtn(i) {
    var spanA = document.getElementsByTagName("tr")[i].getAttribute('id');
    var spanB = $(`#${spanA} span`).text();
    if (spanB == "部門主管審核中") {
        var passBtn1 = JSON.stringify([{
            "userToken": getCookie(),
            attendanceId: spanA,
            approvalId: "6"
        }]);
        $.ajax({
            url: "http://localhost:8080/Attendance/updateapproval2",
            type: "PUT",
            data: passBtn1,
            contentType: "application/json",
            success: function () {
                if (confirm("審核成功")) {
                    window.location.href = './CMS_2_3.html';
                }
            }
        })
        let nowdate = moment(new Date).format('YYYY-MM-DD HH:mm:ss');
        var attpeople3 = JSON.stringify([{
            "userToken": getCookie(),
            attendanceId: spanA,
            dateApproved1: nowdate
        }]);
        $.ajax({
            url: "http://localhost:8080/Approval/updateApproval2",
            type: "PUT",
            data: attpeople3,
            contentType: "application/json",
            success: function () {
                console.log("OK");
            }
        })
    }
    if (spanB == "會計審核中") {
        var passBtn2 = JSON.stringify([{
            "userToken": getCookie(),
            attendanceId: spanA,
            approvalId: "7"
        }]);
        $.ajax({
            url: "http://localhost:8080/Attendance/updateapproval3",
            type: "PUT",
            data: passBtn2,
            contentType: "application/json",
            success: function () {
                if (confirm("審核成功")) {
                    window.location.href = './CMS_2_3.html';
                }
            }
        })
        let nowdate = moment(new Date).format('YYYY-MM-DD HH:mm:ss');
        var attpeople5 = JSON.stringify([{
            "userToken": getCookie(),
            attendanceId: spanA,
            dateApproved2: nowdate
        }]);
        $.ajax({
            url: "http://localhost:8080/Approval/updateApproval3",
            type: "PUT",
            data: attpeople5,
            contentType: "application/json",
            success: function () {
                console.log("OK");
            }
        })
    }
}



//審核未通過按鈕
function failBtn(i) {
    var spanA = document.getElementsByTagName("tr")[i].getAttribute('id');
    var spanB = $(`#${spanA} span`).text();
    if (spanB == "部門主管審核中") {
        var failBtn1 = JSON.stringify([{
            "userToken": getCookie(),
            attendanceId: spanA,
            approvalId: "5"
        }]);
        $.ajax({
            url: "http://localhost:8080/Attendance/updateapproval2/",
            type: "PUT",
            data: failBtn1,
            contentType: "application/json",
            success: function () {
                if (confirm("審核不通過")) {
                    window.location.href = './CMS_2_3.html';
                }
            }
        })
        let nowdate = moment(new Date).format('YYYY-MM-DD HH:mm:ss');
        var attpeople4 = JSON.stringify([{
            "userToken": getCookie(),
            attendanceId: spanA,
            dateApproved1: nowdate
        }]);
        $.ajax({
            url: "http://localhost:8080/Approval/updateApproval2/",
            type: "PUT",
            data: attpeople4,
            contentType: "application/json",
            success: function () {
                console.log("OK");
            }
        })
    }
    if (spanB == "會計審核中") {
        var failBtn2 = JSON.stringify([{
            "userToken": getCookie(),
            attendanceId: spanA,
            approvalId: "8"
        }]);
        $.ajax({
            url: "http://localhost:8080/Attendance/updateapproval3/",
            type: "PUT",
            data: failBtn2,
            contentType: "application/json",
            success: function () {
                if (confirm("審核不通過")) {
                    window.location.href = './CMS_2_3.html';
                }
            }
        })
        let nowdate = moment(new Date).format('YYYY-MM-DD HH:mm:ss');
        var attpeople6 = JSON.stringify([{
            "userToken": getCookie(),
            attendanceId: spanA,
            dateApproved2: nowdate
        }]);
        $.ajax({
            url: "http://localhost:8080/Approval/updateApproval3/",
            type: "PUT",
            data: attpeople6,
            contentType: "application/json",
            success: function () {
                console.log("OK");
            }
        })
    }
}

