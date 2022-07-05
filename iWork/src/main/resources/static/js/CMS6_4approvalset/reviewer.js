// 第一審核人員
$("#aparts").change(function () {
    var selectaparts = $("#aparts").find("option:selected").val();
    if (selectaparts != 0) {
        $("#reviewerone").empty().append("<option></option>");
        $("#reviewertwo").empty().append("<option></option>");
        $.ajax({
            url: "http://localhost:8080/setAdm/",
            type: "GET",
            success: function (data) {
                $.each(data, function () {
                    if (selectaparts == this.aparts.apartId && (this.adm) == "admin") {
                        var option1 = $("<option value=" + this.empId + ">" + this.empName + "</option>");
                        option1.appendTo("#reviewerone");
                    }
                })
            }
        })
    } else {
        $("#reviewerone").empty().append("<option></option>");
        $("#reviewertwo").empty().append("<option></option>");
    }
})

// 第二審核人員
$("#aparts").change(function () {
    var selectaparts = $("#aparts").find("option:selected").val();
    if (selectaparts != 0) {
        $("#reviewerone").empty().append("<option></option>");
        $("#reviewertwo").empty().append("<option></option>");
        $.ajax({
            url: "http://localhost:8080/setAdm/",
            type: "GET",
            success: function (data) {
                $.each(data, function () {
                    if (this.aparts.apartId == "1" || this.aparts.apartId == "2") {
                        if ((this.adm) == "admin") {
                            var option2 = $("<option value=" + this.empId + ">" + this.empName + "</option>");
                            option2.appendTo("#reviewertwo");

                        }
                    }
                })
            }
        })
    } else {
        $("#reviewerone").empty().append("<option></option>");
        $("#reviewertwo").empty().append("<option></option>");
    }
})
