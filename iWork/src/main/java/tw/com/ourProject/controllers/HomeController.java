package tw.com.ourProject.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class HomeController {

	

	@RequestMapping(value = "CMS_0interface", method = RequestMethod.GET)
	public String getChat(Model model) {
		return "CMS_0interface";
	}

}