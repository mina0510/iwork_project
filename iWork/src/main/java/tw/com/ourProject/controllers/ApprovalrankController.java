package tw.com.ourProject.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;

import tw.com.ourProject.model.Approvalrank;
import tw.com.ourProject.model.Attendance;
import tw.com.ourProject.model.Employee;
import tw.com.ourProject.service.ApprovalrankService;
import tw.com.ourProject.utils.JWTUtil;

@RestController
public class ApprovalrankController {
	@Autowired
	public ApprovalrankService approvalrankService;
	
	@Autowired
	public Attendance attendanceid;
	
	@Autowired
	public JWTUtil jwt;

	@GetMapping("/Approval/showrank")
	public List<Approvalrank> findrank() {
		List<Approvalrank> showrank = approvalrankService.findRank();
		return showrank;
	}

	@PostMapping("/Approval/newrank")
	public void addApproval(@RequestBody JSONArray attendanceInfo) {
		try {
			attendanceid.setAttendanceId(attendanceInfo.getJSONObject(0).get("attendanceId").toString());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date obj3 = format.parse(attendanceInfo.getJSONObject(0).get("attendanceDate").toString());

			approvalrankService.saveApproval1(attendanceid, obj3);

		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}
	
	
	@PutMapping("/Approval/updateApproval2")
	public void addApproval2(@RequestBody JSONArray info) {
		try {
			attendanceid.setAttendanceId(info.getJSONObject(0).get("attendanceId").toString());
			String obj2 = jwt.getInfoFromJwtToken(info.getJSONObject(0).getString("userToken"), "empId");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date obj3 = format.parse(info.getJSONObject(0).get("dateApproved1").toString());

			approvalrankService.saveApproval2(attendanceid, obj2, obj3);

		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}
	
	@PutMapping("/Approval/updateApproval3")
	public void addApproval3(@RequestBody JSONArray info) {
		try {
			attendanceid.setAttendanceId(info.getJSONObject(0).get("attendanceId").toString());
			String obj2 = jwt.getInfoFromJwtToken(info.getJSONObject(0).getString("userToken"), "empId");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date obj3 = format.parse(info.getJSONObject(0).get("dateApproved2").toString());

			approvalrankService.saveApproval3(attendanceid, obj2, obj3);

		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}
}
