// CMS_6_2管理系統，公告

// 查詢
//如果大於下架日期則不抓資料出來
$(function () {
    $.ajax({
        url: "http://localhost:8080/showAnnouncement/",
        type: "GET",
        success: function (data) {
            var i = 1;
            $.each(data, function () {
                var NowDate = new Date();
                var yy = NowDate.getFullYear();
                var mm = ('0' + (NowDate.getMonth() + 1)).slice(-2);
                var dd = ('0' + NowDate.getDate()).slice(-2);
                var today = yy + '-' + mm + '-' + dd;
                if (today < this.removed) {
                    var announcementRecord = $(`<tr class="staffWord" id="${this.announceId}">
                        <td class="tableStyle">`+ i + `</td>
                        <td class="tableStyle" style="text-align: left;">`+ this.title + `</td>
                        <td class="tableStyle" style="text-align: left;">`+ this.content + `</td>
                        <td class="tableStyle" style="text-align: left;"><span id="att${this.announceId}"></span></td>
                        <td class="tableStyle">`+ this.employees.empName + `</td>
                        <td class="tableStyle">`+ this.updateDate + `</td>
                        <td class="tableStyle">`+ this.removed + `</td>
                        <td class="tableStyle">
                            <p class="icon-input-btn mt-3">
                                <i class="glyphicon glyphicon-pencil"></i>
                                <input type="button" value="修改" class="function" onClick="announcementEdit(${i})">
                            </p>&nbsp;&nbsp;
                            <p class="icon-input-btn">
                                <i class="glyphicon glyphicon-trash"></i>
                                <input type="button" value="刪除" class="function deleteB" onClick="annDel(${i})">
                            </p>&nbsp;&nbsp;
                        </td>
                    </tr>`);
                    announcementRecord.appendTo("#announcementTable");
                    i++;
                }
            })
            $.ajax({
                url: "http://localhost:8080/Attach/find/",
                type: "GET",
                success: function (data) {
                    console.log(data);
                    for(i = 0 ; i < data.length ;i++){
                        // data[i].announceId
                        var aImg = (data[i].attName).split("h/")[1];
                        var attachimg = $(`<a href="${data[i].attName}"><span>${aImg}</span></a><br>`);
                        attachimg.appendTo(`#att${data[i].announcements.announceId}`);
                    }
                }
            })
        
        }

    })
})

// 修改
function announcementEdit(i) {
    window.localStorage.setItem("annend", document.getElementsByTagName("tr")[i].getAttribute('id'));
    window.location.href = "./CMS_6_2_3.html";
}



// 刪除
function annDel(i) {
    console.log("OK");
    var annDelSet = JSON.stringify([{
        announceId: document.getElementsByTagName("tr")[i].getAttribute('id')
    }]);
    console.log(annDelSet);

    $.ajax({
        url: "http://localhost:8080/Announcement/delete/",
        type: "DELETE",
        data: annDelSet,
        contentType: "application/json",
        success: function () {
             console.log("OK")
            if (confirm("刪除成功")) {
                window.location.href = './CMS_6_2_1.html';
            }
        }
    })
}

