package tw.com.ourProject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Apart;
import tw.com.ourProject.model.Approvalset;
import tw.com.ourProject.model.Employee;
import tw.com.ourProject.repository.ApartRepo;
import tw.com.ourProject.repository.ApprovalsetRepo;
import tw.com.ourProject.repository.EmployeeRepo;

@Service
@Transactional
public class ApprovalsetService {
	@Autowired
	public ApprovalsetRepo approvalsetRepo;
	
	@Autowired
	public EmployeeRepo employeeRepo;
	
	@Autowired
	public ApartRepo apartRepo;
	
	
	public List<Employee> findemp(){
		return employeeRepo.findAll();
	}
	
	public List<Approvalset> findApprovalset(){
		return approvalsetRepo.findAll();
	}
	
	public void saveApprovalset(Apart apartid, Employee firstapproval, Employee secondapproval) {
		Approvalset approvalInfo = new Approvalset();
		approvalInfo.setAparts(apartid);
		approvalInfo.setEmployees(firstapproval);
		approvalInfo.setEmployee(secondapproval);
		approvalsetRepo.save(approvalInfo);
		
	}
	
	public void deleteApprovalset(Integer approvalsetid) {
		approvalsetRepo.deleteById(approvalsetid);
	}
	
	public void updateApprovalset(Integer approvalsetid, Employee firstapproval, Employee secondapproval) {
		Approvalset updateSet = approvalsetRepo.findByApprovalSetId(approvalsetid);
		updateSet.setEmployees(firstapproval);
		updateSet.setEmployee(secondapproval);
		approvalsetRepo.save(updateSet);
	}
	
	public JSONObject findsetData(Integer approvalsetData) {
		Approvalset findsad = approvalsetRepo.findByApprovalSetId(approvalsetData);
		JSONObject obj = new JSONObject();
		obj.put("approvalId", findsad.getApprovalSetId());
		obj.put("apartId", findsad.getAparts().getApartId());
		obj.put("apart", findsad.getAparts().getApart());
		obj.put("firstApprovalId", findsad.getEmployees().getEmpId());
		obj.put("firstApprovalName", findsad.getEmployees().getEmpName());
		obj.put("secondApprovalId", findsad.getEmployee().getEmpId());
		obj.put("secondApprovalName", findsad.getEmployee().getEmpName());
		return obj;
	}
}
