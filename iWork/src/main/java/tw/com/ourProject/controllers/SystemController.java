package tw.com.ourProject.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.websocket.MsgTemplate;

import java.util.Date;

@RestController
@RequestMapping(value = "/system", method = RequestMethod.POST)
public class SystemController {

	@Autowired
	private MsgTemplate template;

	
//	@RequestMapping("/broadcast")
//	public String getSystemInfo(@RequestBody JSONObject data) {
//		String text = data.getString("text");
//		template.broadcast(text);
//		return text;
//	}
	
	@RequestMapping("/broadcast")
	public OutputMessage broadcast(@RequestBody Message message ) {
		OutputMessage outputMessage = new OutputMessage(new Date().toString(), message);
		template.broadcast(outputMessage);
		return outputMessage;
	}

	@RequestMapping("/send/{user}")
	public OutputMessage broadcast(@PathVariable("user") String user, @RequestBody Message message) {
		OutputMessage outputMessage = new OutputMessage(new Date().toString(), message);
		template.sendMsgToUser(user, outputMessage);
		return outputMessage;
	}

}