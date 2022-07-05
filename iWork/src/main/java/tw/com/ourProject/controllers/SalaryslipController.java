package tw.com.ourProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Salaryslip;
import tw.com.ourProject.service.SalaryslipService;

@RestController
public class SalaryslipController {

	@Autowired
	public SalaryslipService slipSevrice;
	
	@PostMapping("/testPdf")
	public void slipSevrice (@RequestBody JSONObject data){
		slipSevrice.convertPdf(data.getString("Token"), data.getString("Email"));
	}
	
	
}
