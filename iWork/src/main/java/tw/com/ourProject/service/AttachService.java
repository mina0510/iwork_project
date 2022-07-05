package tw.com.ourProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.com.ourProject.model.Announcement;
import tw.com.ourProject.model.Attach;
import tw.com.ourProject.repository.AnnouncementRepo;
import tw.com.ourProject.repository.AttachRepo;
import tw.com.ourProject.utils.JWTUtil;
import tw.com.ourProject.utils.UploadFileUtil;

@Service
public class AttachService {
	
	@Autowired
	public AnnouncementRepo announcementReop;
	@Autowired
	public AttachRepo attachRepo;
	@Autowired
	public UploadFileUtil upload;
	@Autowired
	public JWTUtil jwt;

	Announcement announcement = new Announcement();
	
	
	public void addAttach(MultipartFile[] multipartFile,String annId,String userToken) {

		String empId = jwt.getInfoFromJwtToken(userToken,"empId");
		announcement = announcementReop.findById(Integer.parseInt(annId)).get();
		for(int i = 0 ; i < multipartFile.length ; i++) {
			Attach attach = new Attach();
			attach.setAnnouncements(announcement);
			attach.setAttName(upload.uploadAttach(multipartFile[i]));
			attach.setUpdatePerson(empId);
			attach.setCreatePerson(empId);
			attachRepo.save(attach);
		}	
	}
	
	public List<Attach> findAttach(){
		return attachRepo.findAll();
	}

}
