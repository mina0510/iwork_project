package tw.com.ourProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ourProject.model.Leaves;
import tw.com.ourProject.service.LeavesService;

@RestController
public class LeavesController {
	@Autowired
	public LeavesService leavesService;
	
	@GetMapping("/leaves")
	public List<Leaves> findLeaves(){
		List<Leaves> leaves = leavesService.findLeaves();
		return leaves;
	}
}
