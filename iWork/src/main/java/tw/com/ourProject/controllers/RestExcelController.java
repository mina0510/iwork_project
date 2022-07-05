package tw.com.ourProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;

/**
 * 2022-06-15  把餐廳excel數據存到mysql #這一個版本的token還是不能用，先註解掉
 */
@Controller
public class RestExcelController {
	@Autowired
	public tw.com.ourProject.service.RestaurantService restservice;
	
	@GetMapping("/restindex")
	public String index() {
		return "restindex";
	}

	@RequestMapping(value = "/uploadExcel", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseBody
    public String uploadExcel(@RequestParam("fileUpload") MultipartFile file) {
        String name = file.getOriginalFilename();
        if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
            return "文件格式錯誤";
        }
        return restservice.parseExcel(file);
	}
}