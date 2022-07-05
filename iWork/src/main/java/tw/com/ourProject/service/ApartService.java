package tw.com.ourProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.ourProject.model.Apart;
import tw.com.ourProject.repository.ApartRepo;

@Service
public class ApartService {
	@Autowired
	public ApartRepo apartRepo;
	
	public List<Apart> findApart(){
		return apartRepo.findAll();
	}
}
