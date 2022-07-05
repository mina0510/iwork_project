// 出勤維護系統--層級設定
// 查詢
var approvalList = [];
$(function () {
	$.ajax({
		url: "http://localhost:8080/Approvalset/find/",
		type: "GET",
		success: function (data) {
			var i = 1;
			$.each(data, function () {
				var approvalsetrecord =
					$(`<tr class="staffWord" id="${this.approvalSetId}">
						<td class="tableStyle"> ${i} </td>
                    	<td class="tableStyle"> ${this.aparts.apart}</td>
                    	<td class="tableStyle">${this.employees.empName} </td>
                    	<td class="tableStyle"> ${this.employee.empName} </td>
                    	<td class="tableStyle">
                        	<span class="icon-input-btn">
                            	<i class="glyphicon glyphicon-pencil"></i>
                            	<input type="button" value="修改" class="function editOpen" onClick="editbtn( ${i})"; />
                        	</span>&nbsp;&nbsp;
                        	<span class="icon-input-btn">
                            	<i class="glyphicon glyphicon-trash"></i>
                            	<input type="button" value="刪除" class="function" onClick="deletebtn( ${i})"/>
                        	</span>&nbsp;&nbsp;
                    	</td>
                	</tr>`);
					approvalsetrecord.appendTo("#leavelRecord");
					approvalList[i - 1] = this.aparts.apartId;
				i++;
			})
		}
	})
})



// 新增
$("#sendbtn").on("click", function () {
	if ($("#aparts").find("option:selected").val() == "" || $("#reviewerone").find("option:selected").val() == "" || $("#reviewertwo").find("option:selected").val() == "") {
		confirm("欄位不可為空白");
	} else {
		var j = false;
		for (var i = 0; i < approvalList.length; i++) {
			if ($("#aparts").find("option:selected").val() == approvalList[i]) {
				j = true;
				break;
			}
		}
		if (j == false) {
			var insertSet = JSON.stringify([{
				apartId: $("#aparts").find("option:selected").val(),
				fisrtApproval: $("#reviewerone").find("option:selected").val(),
				secondApproval: $("#reviewertwo").find("option:selected").val()
			}]);
			$.ajax({
				url: "http://localhost:8080/Approvalset/save/",
				type: "POST",
				data: insertSet,
				contentType: "application/json",
				success: function () {
					if (confirm("確認完成")) {
						parent.document.getElementById("changeApart").style.visibility = "hidden";
						parent.location.reload();
					}
				}
			})
		} else {
			if (confirm("資料已存在")) {
				parent.changeApart.close();
			}
		}
	}
})

