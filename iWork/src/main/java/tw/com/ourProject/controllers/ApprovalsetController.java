package tw.com.ourProject.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Apart;
import tw.com.ourProject.model.Approvalset;
import tw.com.ourProject.model.Employee;
import tw.com.ourProject.service.ApprovalsetService;

@RestController
@Transactional
public class ApprovalsetController {
	@Autowired
	public ApprovalsetService approvalsetService;
	
	@GetMapping("/setAdm")
	public List<Employee> findEmp(){
		List<Employee> empAdm = approvalsetService.findemp();
		return empAdm;
	}

	@GetMapping("/Approvalset/find")
	public List<Approvalset> findApprovalset(){
		List<Approvalset> approvalsets = approvalsetService.findApprovalset();
		return approvalsets;
	}
	
	@Autowired
	public Apart apid;
	public Employee emp1 = new Employee();
	public Employee emp2 = new Employee();
	
	@PostMapping("/Approvalset/save")
	public void insertApprovalset(@RequestBody JSONArray approvalInfo) {
		String obj1 = approvalInfo.getJSONObject(0).get("apartId").toString();
		apid.setApartId(Integer.parseInt(obj1));
		String obj2 = approvalInfo.getJSONObject(0).get("fisrtApproval").toString();
		String obj3 = approvalInfo.getJSONObject(0).get("secondApproval").toString();
		
		emp1.setEmpId(obj2);
		emp2.setEmpId(obj3);

		approvalsetService.saveApprovalset(apid , emp1 , emp2);
	}
	
	@DeleteMapping("/Approvalset/delete")
	public void delApprovalset(@RequestBody JSONArray approvalInfo) {
		Integer obj1 = Integer.parseInt(approvalInfo.getJSONObject(0).get("approvalSetId").toString());
		approvalsetService.deleteApprovalset(obj1);
	}
	
	@PutMapping("/Approvalset/update")
	public void updateApprovalset(@RequestBody JSONArray approvalInfo) {
		Integer obj1 = Integer.parseInt(approvalInfo.getJSONObject(0).get("approvalSetId").toString());
		String obj3 = approvalInfo.getJSONObject(0).get("fisrtApproval").toString();
		String obj4 = approvalInfo.getJSONObject(0).get("secondApproval").toString();
		
		emp1.setEmpId(obj3);
		emp2.setEmpId(obj4);

		approvalsetService.updateApprovalset(obj1 , emp1 , emp2);
	}
	
	//回傳ID搜尋其他資料並回傳給前端
	@PostMapping("/Approval/dataresponse")
	public JSONObject responseData(@RequestBody JSONObject approvalInfo) {
		return approvalsetService.findsetData(Integer.parseInt(approvalInfo.get("approvalSetId").toString()));
		
	}
	
}
