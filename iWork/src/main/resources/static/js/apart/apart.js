$(function () {
    $.ajax({
        url: "http://localhost:8080/apart/",
        type: "GET",
        success: function (data) {
            $.each(data, function () {
                var option = $("<option value=" + this.apartId + ">" + this.apart + "</option>");
                option.appendTo("#aparts");
            })
            
        }
    })
})
    

