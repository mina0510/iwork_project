package tw.com.ourProject.controllers;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.service.PunchService;
import tw.com.ourProject.utils.JWTUtil;

@RestController
public class PunchController {

	
	@Autowired
	public PunchService punchService;
	@Autowired
	JWTUtil jwtUtil;
	
	 
	@PostMapping("/punch/saveInfo")
	public void savePunchInfo(@RequestBody JSONArray punchData) {
		System.out.println("------------------------------------------");
		System.out.println(punchData);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
		JSONObject infoObj = punchData.getJSONObject(0);
		System.out.println("存入的打卡資訊："+infoObj.toJSONString());
		System.out.println("存入的打卡人："+jwtUtil.getInfoFromJwtToken(infoObj.getString("person"), "empId"));
		punchService.savePunchInfo(infoObj.getString("status"),
									format.parse(infoObj.getString("time")),
									jwtUtil.getInfoFromJwtToken(infoObj.getString("person"), "empId"),
									infoObj.getString("locationLat"),
									infoObj.getString("locationLng"),
									infoObj.getString("state")
									);
		
	    }catch(Exception e) {
	    	
	    }
	}
	
	@PostMapping("/punch/getInfo")
	public JSONArray getPunchInfo(@RequestBody JSONObject data) {
		return  punchService.getPunchInfo(jwtUtil.getInfoFromJwtToken(data.getString("userToken"),"empId"));
		
	}
}
