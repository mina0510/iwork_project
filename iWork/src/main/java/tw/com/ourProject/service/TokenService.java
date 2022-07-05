package tw.com.ourProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.ourProject.model.Temptoken;
import tw.com.ourProject.repository.TempTokenRepo;
@Transactional
@Service
public class TokenService {
	
	@Autowired
	public TempTokenRepo tempTokenRepo;
	
	public Temptoken tempToken = new Temptoken();
	
	public Long countToken() {
		Long rs = tempTokenRepo.count();
		return rs;
	}
	
	public void deleteToken(String userToken) {
		tempTokenRepo.deleteByTokenValue(userToken);
	}
	
}
