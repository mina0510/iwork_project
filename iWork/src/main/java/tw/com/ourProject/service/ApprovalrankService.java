package tw.com.ourProject.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.ourProject.model.Approval;
import tw.com.ourProject.model.Approvalrank;
import tw.com.ourProject.model.Attendance;
import tw.com.ourProject.model.Employee;
import tw.com.ourProject.model.Leaves;
import tw.com.ourProject.repository.ApprovalrankRepo;
import tw.com.ourProject.repository.EmployeeRepo;

@Service
public class ApprovalrankService {
	@Autowired
	public ApprovalrankRepo approvalrankRepo;
	
	@Autowired
	public EmployeeRepo employeeRepo;
	
	public Employee empname = new Employee();
	
	public List<Approvalrank> findRank() {
		return approvalrankRepo.findAll();
	}
	
	public void saveApproval1(Attendance attendanceid, Date approvaldate) {
		Approvalrank approvalrankInfo = new Approvalrank();
		approvalrankInfo.setAttendances(attendanceid);
		approvalrankInfo.setAttendanceDate(approvaldate);
		approvalrankRepo.save(approvalrankInfo);
	}
	
	public void saveApproval2(Attendance attendanceid, String empid, Date approvaldate1) {
		Approvalrank approvalrankInfo1 = approvalrankRepo.findByAttendances(attendanceid);
		empname = employeeRepo.findByEmpId(empid);
		approvalrankInfo1.setApprover1(empname.getEmpName());
		approvalrankInfo1.setDateApproved1(approvaldate1);
		approvalrankRepo.save(approvalrankInfo1);
	}
	
	public void saveApproval3(Attendance attendanceid, String empid, Date approvaldate2) {
		Approvalrank approvalrankInfo2 = approvalrankRepo.findByAttendances(attendanceid);
		empname = employeeRepo.findByEmpId(empid);
		approvalrankInfo2.setApprover2(empname.getEmpName());
		approvalrankInfo2.setDateApproved2(approvaldate2);
		approvalrankRepo.save(approvalrankInfo2);
	}

}
