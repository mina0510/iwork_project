$(function(){
    $.ajax({
        url:"http://localhost:8080/leaves/",
        type:"GET",
        success:function(data){
            $.each(data,function(){
                var option = $("<option value="+this.leaveId+">"+this.leaveType+"</option>");
                option.appendTo("#leaves");
            })
        }
    })
})