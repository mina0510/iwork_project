package tw.com.ourProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.service.EmployeeService;
import tw.com.ourProject.service.SecurityService;
import tw.com.ourProject.service.TokenService;
import tw.com.ourProject.utils.OnlineCounterUtil;

@RestController
public class SecurityController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	TokenService tokenService;
	@Autowired
	SecurityService securityService ;
	@Autowired
    private OnlineCounterUtil onlineCounterUtil;

	@PostMapping("/security/login")
	public JSONObject login(@RequestBody JSONObject inputdata) {

		//驗證帳號密碼 （成功：回傳token ;查無帳號：101 ;密碼錯誤：102 ; 帳號被關閉：103)
		JSONObject result = employeeService.userVerify(inputdata.getString("empId"), inputdata.getString("passwd"));
		return result;

	}
	
	@PostMapping("/security/verifyToken")
	public JSONObject verifyToken(@RequestBody JSONObject userToken) {
		JSONObject obj = new JSONObject();
		obj.put("state", employeeService.verifyToken(userToken.getString("userToken")));
		return obj;
		
	}

	@PostMapping("/security/countToken")
	public JSONObject countToken() {
		JSONObject obj = new JSONObject();
        Integer onlines = onlineCounterUtil.getOnlineCount();
//		obj.put("countRs",tokenService.countToken().toString());
		obj.put("countRs",onlines.toString());

		return obj;
	}
	
	@PostMapping("/security/logOut")
	public void logOut(@RequestBody JSONObject userToken) {
		tokenService.deleteToken(userToken.getString("userToken"));
	}
	
	@PostMapping("/security/checkAdm")
	public JSONObject checkAdm(@RequestBody JSONObject userToken) {
	
		return employeeService.checkAdm(userToken.getString("userToken"));
	}
	
	@PostMapping("/security/forgetPasswd")
	public void forgetPasswd(@RequestBody JSONObject data) {
		String empId = data.getString("empId");
		securityService.sendResetPasswdMail(empId);
	}
	
	@GetMapping("/security/resetPasswd")
	public String  resetPasswd(@RequestParam String empId) {
		securityService.resetPasswd(empId);
		return "更改成功!!!  密碼重設為:P@ssw0rd";
	}
}
