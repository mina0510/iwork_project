package tw.com.ourProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.ourProject.model.Employee;
import tw.com.ourProject.repository.EmployeeRepo;
import tw.com.ourProject.utils.MailUtil;

@Service
public class SecurityService {

	@Autowired
	public MailUtil email;
	@Autowired
	public EmployeeRepo employeeRepo;
	
	public void sendResetPasswdMail(String empId) {
		String userEmail = employeeRepo.findByEmpId(empId).getEmail();
		
		var sub = "i上班忘記密碼驗證信";
		var text = "請點選以下網址以重設密碼：http://localhost:8080/security/resetPasswd?action=get&empId="+empId;
		email.mailOnlyText(userEmail, sub, text);
	}
	
	public void resetPasswd(String empId) {
		Employee emp = employeeRepo.findByEmpId(empId);
		emp.setPasswd("P@ssw0rd");
		employeeRepo.save(emp);

	}
}
