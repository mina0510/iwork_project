package tw.com.ourProject.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Approval;
import tw.com.ourProject.model.Attendance;
import tw.com.ourProject.model.Employee;
import tw.com.ourProject.model.Leaves;
import tw.com.ourProject.service.AttendanceService;
import tw.com.ourProject.utils.JWTUtil;

@RestController
public class AttendanceController {
	@Autowired
	public AttendanceService attendanceService;
	
	@Autowired
	public JWTUtil jwt;
	
	@GetMapping("/Attendance/show")
	public List<Attendance> findattendance(){
		List<Attendance> attendance = attendanceService.showAttendance();
		return attendance;
	}
	
	@Autowired
	public Leaves leaveid;
	@Autowired
	public Approval approvalid;
	
	public Employee emp1 = new Employee();
	public Employee emp2 = new Employee();
	@PostMapping("/Attendance/insert")
	public void addAttendance(@RequestBody JSONArray attendanceInfo) {
		
		try {
			String obj1 = jwt.getInfoFromJwtToken(attendanceInfo.getJSONObject(0).getString("userToken"), "empId");
			emp1.setEmpId(obj1);
			leaveid.setLeaveId(Integer.parseInt(attendanceInfo.getJSONObject(0).get("leaveId").toString())); 
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String obj2 = attendanceInfo.getJSONObject(0).get("contentText").toString();
			Date obj3 = format.parse(attendanceInfo.getJSONObject(0).get("startDate").toString());
			Date obj4 = format.parse(attendanceInfo.getJSONObject(0).get("endDate").toString());
			Integer obj5 = (Integer.parseInt(attendanceInfo.getJSONObject(0).get("hours").toString()));
			approvalid.setApprovalId(Integer.parseInt(attendanceInfo.getJSONObject(0).get("approvalId").toString()));
			Date obj7 = format.parse(attendanceInfo.getJSONObject(0).get("createDate").toString());
			
			attendanceService.saveAttendance(emp1, leaveid, obj2, obj3, obj4, obj5, approvalid, obj7, emp1, emp1);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
	}
	
	//回傳ID搜尋其他資料並回傳給前端
	@PostMapping("/Attendance/findPersonData")
	public JSONArray responseData(@RequestBody JSONObject info) {
		emp1.setEmpId(jwt.getInfoFromJwtToken(info.getString("userToken"), "empId"));
		return attendanceService.findsetData(emp1);
	}
		
	@DeleteMapping("/Attendance/delete")
	public void delattendance(@RequestBody JSONArray info) {
		String obj1 = info.getJSONObject(0).get("attendanceId").toString();
		attendanceService.deleteAttendance(obj1);
	}
	
	@PutMapping("/Attendance/update")
	public void updateAttendance(@RequestBody JSONArray info) {
		System.out.println(info);
		try {
			String obj1 = info.getJSONObject(0).get("attendanceId").toString();
			String obj2 = jwt.getInfoFromJwtToken(info.getJSONObject(0).getString("userToken"), "empId");
			emp1.setEmpId(obj2);
			leaveid.setLeaveId(Integer.parseInt(info.getJSONObject(0).get("leaveId").toString()));
			String obj3 = info.getJSONObject(0).get("contentText").toString();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date obj4 = format.parse(info.getJSONObject(0).get("startDate").toString());
			Date obj5 = format.parse(info.getJSONObject(0).get("endDate").toString());
			Integer obj6 = (Integer.parseInt(info.getJSONObject(0).get("hours").toString()));
			approvalid.setApprovalId(Integer.parseInt(info.getJSONObject(0).get("approvalId").toString()));
			
			attendanceService.updateattendance(obj1, leaveid, obj3, obj4, obj5, obj6, approvalid, emp1);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
	}
	
	@PutMapping("/Attendance/updateapproval1")
	public void updateApproval1(@RequestBody JSONArray info) {
		try {
			String obj1 = info.getJSONObject(0).get("attendanceId").toString();
			String obj2 = jwt.getInfoFromJwtToken(info.getJSONObject(0).getString("userToken"), "empId");
			emp1.setEmpId(obj2);
			approvalid.setApprovalId(Integer.parseInt(info.getJSONObject(0).get("approvalId").toString()));
			
			attendanceService.updateapproval1(obj1, approvalid, emp1);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
	}
	
	@PostMapping("/Attendance/updateview")
	public JSONObject updateviewAttendance(@RequestBody JSONObject info) {
		return attendanceService.UpdateViewAttendance(info.get("attendanceId").toString());
	}
	
	@PostMapping("/Attendance/appprovallist1")
	public JSONArray findApproval1attendance(@RequestBody JSONObject info) {
		return attendanceService.findApproval1attendance(jwt.getInfoFromJwtToken(info.getString("userToken"), "empId"));
	}
	
	@PutMapping("/Attendance/updateapproval2")
	public void updateApproval2(@RequestBody JSONArray info) {
		try {
			String obj1 = info.getJSONObject(0).get("attendanceId").toString();
			String obj2 = jwt.getInfoFromJwtToken(info.getJSONObject(0).getString("userToken"), "empId");
			emp1.setEmpId(obj2);
			approvalid.setApprovalId(Integer.parseInt(info.getJSONObject(0).get("approvalId").toString()));
			
			attendanceService.updateapproval2(obj1, approvalid, emp1);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
	}
	
	@PostMapping("/Attendance/appprovallist2")
	public JSONArray findApproval2attendance(@RequestBody JSONObject info) {
		return attendanceService.findApproval2attendance(jwt.getInfoFromJwtToken(info.getString("userToken"), "empId"));
	}
	
	@PutMapping("/Attendance/updateapproval3")
	public void updateApproval3(@RequestBody JSONArray info) {
		try {
			String obj1 = info.getJSONObject(0).get("attendanceId").toString();
			String obj2 = jwt.getInfoFromJwtToken(info.getJSONObject(0).getString("userToken"), "empId");
			emp1.setEmpId(obj2);
			approvalid.setApprovalId(Integer.parseInt(info.getJSONObject(0).get("approvalId").toString()));
			
			attendanceService.updateapproval3(obj1, approvalid, emp1);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
	}
}
