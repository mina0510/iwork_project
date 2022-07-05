// 紀錄
$(function () {
    $.ajax({
        url: "http://localhost:8080/emp/findAll",
        type: "GET",
        success: function (data) {
            var i = 1;
            $.each(data, function () {
                var phone1 = this.tel;
                var cell = this.cellphone1;
                var email1 = this.email;
                if (phone1 == null) {
                    phone1 = "";
                }
                if (cell == null) {
                    cell = "";
                }
                if (email1 == null) {
                    email1 = "";
                }
                var staffAll = $(`<tr class="staffTr">
                    <td class="tableStyle">${i}</td>
                    <td class="tableStyle">${this.aparts.apart}</td>
                    <td class="tableStyle" id="empNames">${this.empName}</td>
                    <td class="tableStyle">${phone1}</td>
                    <td class="tableStyle">${cell}</td>
                    <td class="tableStyle">${email1}</td>
                </tr>`);

                staffAll.appendTo("#staffRecord");
                i++;
            })
        }
    })
})


// 查詢
$("#staffSearchbtn").on("click", function () {
    // console.log("OK")
    $(".staffTr").empty();
    $.ajax({
        url: "http://localhost:8080/emp/findAll",
        type: "GET",
        success: function (data) {
            var i = 1;
            $.each(data, function () {
                var phone1 = this.tel;
                var cell = this.cellphone1;
                var email1 = this.email;
                if (phone1 == null) {
                    phone1 = "";
                }
                if (cell == null) {
                    cell = "";
                }
                if (email1 == null) {
                    email1 = "";
                }
                if (this.aparts.apartId == $("#aparts").val() || this.empName == $("#empNameIn").val() || ($("#aparts").val() == "" && $("#empNameIn").val() == "")) {
                    var staffAllS2 = $(`<tr class="staffTr">
                        <td class="tableStyle">${i}</td>
                        <td class="tableStyle">${this.aparts.apart}</td>
                        <td class="tableStyle">${this.empName}</td>
                        <td class="tableStyle">${phone1}</td>
                        <td class="tableStyle">${cell}</td>
                        <td class="tableStyle">${email1}</td>
                    </tr>`);

                    staffAllS2.appendTo("#staffRecord");
                    i++;
                }
            })

        }
    })
})

