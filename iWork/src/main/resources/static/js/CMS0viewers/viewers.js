// 瀏覽人數
$(function(){
    $.ajax({
        url:"/security/countToken",
        type:"POST",
        data:"void",
        success:function(data){
            parent.countpeople.innerText = data.countRs; 
        }
    })
})
