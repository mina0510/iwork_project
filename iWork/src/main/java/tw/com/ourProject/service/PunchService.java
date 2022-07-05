package tw.com.ourProject.service;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Punch;
import tw.com.ourProject.repository.PunchRepo;

@Service
public class PunchService {

	@Autowired
	public PunchRepo punchRepo;
	
	public void savePunchInfo(String status,Date time,String person,String locationLat,String locationLng,String state) {
		Punch punchInfo = new Punch();
		punchInfo.setStatus(status);
		punchInfo.setTime(time);
		punchInfo.setPerson(person);
		punchInfo.setLocationLat(locationLat);
		punchInfo.setLocationLng(locationLng);
		punchInfo.setState(state);
		punchRepo.save(punchInfo);
		
	}
	
	public JSONArray getPunchInfo(String person) {
		List<Punch> infos =punchRepo.findTop10ByPersonOrderByTimeDesc(person);
		JSONArray arry = new JSONArray();
		for(Punch info : infos) {
			JSONObject obj = new JSONObject();
			obj.put("status", info.getStatus());
			obj.put("time", info.getTime().toInstant().atOffset(ZoneOffset.ofHours(+8)).format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
			obj.put("state", info.getState());
			arry.add(obj);
		}
		return arry ;
	}
	
}
