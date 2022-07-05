package tw.com.ourProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Employee;
import tw.com.ourProject.service.EmployeeService;

@RestController
public class AccountManageController {
	
	@Autowired
	public EmployeeService employeeService;

	@PostMapping("/account/getAllAccount")
	public List<Employee> getAllAccount(@RequestBody JSONObject data) {
		
		Integer apartId = data.getIntValue("apartId");
		String adm = data.getString("adm");
		if(apartId == 0 && adm.equals("all")) {
			return employeeService.selectAll();
		}else {
			if(apartId==0) {
			return employeeService.selectByConditionOfAdm(adm);
			}
			if(adm.equals("all")) {
			return employeeService.selectByConditionOfApart(apartId);
			}
			return employeeService.selectByConditions(apartId,adm);
		}
	}
	
	@PostMapping("/account/updateToEmployee")
	public void updateToEmployee(@RequestBody JSONObject data) {
		employeeService.updateUserInfoByIdFromAdmin(data);
		
	}
	
	@PostMapping("/account/insertToEmployee")
	public String insertToEmployee(@RequestBody JSONObject data) {
		return employeeService.insertUserInfoFromAdmin(data);
	}
	
	@PostMapping("/account/selectByEmpName")
	public JSONArray selectByEmpName(@RequestBody JSONObject data) {
		return employeeService.getUserInfoByEmpName(data);
	}
	
	@PostMapping("/account/changeAccountState")
	public void changeAccountState(@RequestBody JSONObject data) {
		employeeService.changeAccountState(data);
		
	}
	
}
