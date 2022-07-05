// // CMS0_公告

// CMS0_公告  查詢
$(function () {
    $.ajax({
        url: "http://localhost:8080/showAnnouncement/",
        type: "GET",
        success: function (data) {
            var i = 0;
            $.each(data, function () {
                var NowDate = new Date();
                var yy = NowDate.getFullYear();
                var mm = ('0' + (NowDate.getMonth() + 1)).slice(-2);
                var dd = ('0' + NowDate.getDate()).slice(-2);
                var today = yy + '-' + mm + '-' + dd;
                if (today < this.removed && today >= this.uploadDate) {
                    var announcementRecord = $(`<li id="${this.announceId}" class="notice" onclick="openbtn(${i})">${this.title}</li>`);
                    // $("#mainAnnouncement").after(announcementRecord);
                    announcementRecord.appendTo("#mainAnnouncement");
                    i++;
                }
            })
        }
    })
})

// CMS0_公告第二層彈跳視窗
function openbtn(i) {
    $("#annexTr span").text("");
    console.log("OK");
    var openNumber = JSON.stringify({
        announceId: document.getElementsByTagName("li")[i].getAttribute('id')
    });
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/Announcement/content/",
        data: openNumber,
        contentType: "application/json",
        success: function (data) {
            titleText1.innerText = data[0].title;
            startTime.innerText = (data[0].uploadDate).split('T')[0];
            contentText.innerText = data[0].content;
            if (data[1].attName != "") {
                for (var i = 1; i <= (data.length - 1); i++) {
                    var aaa = $(
                        `<a href="${data[i].attName}"><span>${(data[i].attName).split("h/")[1]}</span></a><br>`);
                    aaa.appendTo("#annexTr");
                }
            } else {
                $("#annexTr span").text("");
            }
            noticedialog.showModal();
        }
    })
}
