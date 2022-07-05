package tw.com.ourProject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.ourProject.model.Apart;
import tw.com.ourProject.service.ApartService;

@RestController
public class ApartController {
	@Autowired
	public ApartService apartService;
	
	@GetMapping("/apart")
	public List<Apart> findApart() {
		List<Apart> aparts = apartService.findApart();
		return aparts;
	}
	
	
}
