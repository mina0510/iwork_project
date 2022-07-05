package tw.com.ourProject.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import tw.com.ourProject.model.Announcement;
import tw.com.ourProject.model.Approvalset;
import tw.com.ourProject.model.Attach;
import tw.com.ourProject.model.Dish;
import tw.com.ourProject.model.Employee;
import tw.com.ourProject.repository.AnnouncementRepo;
import tw.com.ourProject.repository.AttachRepo;
import tw.com.ourProject.repository.EmployeeRepo;
import tw.com.ourProject.utils.JWTUtil;

@Service
@Transactional
public class AnnouncementService {
	@Autowired
	public AnnouncementRepo announcementRepo;
	@Autowired
	public JWTUtil jwt;
	@Autowired
	public EmployeeRepo employeeRepo;
	@Autowired
	public AttachRepo attachRepo;
	
	Employee employee = new Employee();
	
	Announcement announcement = new Announcement();
	
	public List<Announcement> findAnnouncement(){
		return announcementRepo.findAll();
	}
	
	public JSONObject addAnn(JSONObject data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject obj =  new JSONObject();
		Announcement ann = new Announcement();
		try {
		ann.setType(data.getString("type"));
		ann.setUploadDate(format.parse(data.getString("uploadDate")));
		ann.setEmployees(employeeRepo.findByEmpId(jwt.getInfoFromJwtToken(data.getString("userToken"), "empId")));
		ann.setRemoved(format.parse(data.getString("removed")));
		ann.setTitle(data.getString("title"));
		ann.setContent(data.getString("content"));
		ann.setCreatePerson(jwt.getInfoFromJwtToken(data.getString("userToken"), "empId"));
		ann.setUpdatePerson(jwt.getInfoFromJwtToken(data.getString("userToken"), "empId"));
		announcementRepo.save(ann);
		obj.put("annId", ann.getAnnounceId());
		System.out.println(ann.getAnnounceId());
		return obj;

		}catch(Exception e) {
			obj.put("annId", null);
			return obj;
		}
		
	}
	
	public JSONArray findsetData(Integer data) {
		Announcement findanment = announcementRepo.findByAnnounceId(data);
		List<Attach> attaches = attachRepo.findByAnnouncements(findanment);
		JSONArray arrayannment = new JSONArray();
		JSONObject attchname = new JSONObject();
		attchname.put("announceId", findanment.getAnnounceId());
		attchname.put("uploadDate", findanment.getUploadDate());
		attchname.put("title", findanment.getTitle());
		attchname.put("content", findanment.getContent());
		arrayannment.add(attchname);
		if (attaches.size() != 0) {
			for (int i = 0; i<attaches.size(); i++) {
				JSONObject obj = new JSONObject();
				obj.put("attName", attaches.get(i).getAttName());
				arrayannment.add(obj);
			}
		}else{
			JSONObject obj = new JSONObject();
			obj.put("attName", "");
			arrayannment.add(obj);
		}
		return arrayannment;
	}
	
	public void deleteAnnouncement(Integer announcementid) {
		announcementRepo.deleteById(announcementid);
	}

}
