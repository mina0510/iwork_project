package tw.com.ourProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.ourProject.model.Leaves;
import tw.com.ourProject.repository.LeavesRepo;

@Service
public class LeavesService {
	@Autowired
	public LeavesRepo leavesRepo;
	
	public List<Leaves> findLeaves() {
		return leavesRepo.findAll();
	}
}
