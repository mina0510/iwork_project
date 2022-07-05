package tw.com.ourProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import tw.com.ourProject.websocket.MsgTemplate;
import tw.com.ourProject.websocket.WebSocketSessions;

import java.util.Date;

@Controller
public class ChatController {

	@Autowired
	private WebSocketSessions sessions;
	
	

	@MessageMapping("/CMS_0interface")
	@SendTo(MsgTemplate.BROADCAST_DESTINATION)
	public OutputMessage send(final SimpMessageHeaderAccessor accessor,final Message message) throws Exception {
		final String time = new Date().toString();
		
		// 印出預設的sessionId
        System.out.println("session:" + accessor.getSessionId() + ", " + message.toString());
		return new OutputMessage(time, message);
	}

}