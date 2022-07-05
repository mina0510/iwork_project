package tw.com.ourProject.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RestController
public class TestController {

	@PostMapping("/mytest/array")
	public void myTest1(@RequestBody JSONArray data) {
		System.out.println(data.get(1));
	}
	
	@PostMapping("/mytest/obj")
	public void myTest2(@RequestBody JSONObject data) {
		System.out.println(data.get("test"));
	}
	
	@PostMapping("/mytest/str")
	public void myTest2(@RequestBody String data) {
		System.out.println(data);
	}
}
