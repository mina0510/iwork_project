package tw.com.ourProject.service;

import java.io.File;
import java.util.List;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.com.ourProject.model.Dish;
import tw.com.ourProject.model.DishExcelBean;
import tw.com.ourProject.repository.DishRepo;
import tw.com.ourProject.utils.DishExcelUtils;
import tw.com.ourProject.utils.JWTUtil;

@Service
public class DishService {
	@Autowired
	public DishRepo repo;
	@Autowired
	public JWTUtil jwt;

	public String parsefile(MultipartFile file,String tok){
		//現在的token是寫死的
//		tok = jwt.getInfoFromJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJlbXBJZCI6Iml3b3JrYjAwMDAyIiwicGFzc3dkIjoicm9vdCIsImFkbSI6ImFkbWluIiwiZXhwIjoxOTg5MjYwNDU1fQ.fJxhXxen7XY8qdJi-VSgQ3Ru9JH1ksMqwGVknfJXV5l7PQBakb4GbYxL2lQHjhbmzyX4GOaDZL_TW-MwTLWGuQ", "empId");
		
		List<DishExcelBean> list = null;
        try {
            list = DishExcelUtils.excelToShopIdList(file.getInputStream());
            if (list == null || list.size() <= 0) {
                return "導入的數據為空";
            }
            //excel的數據存到資料庫
            System.out.println(list);
            try {
            	for (DishExcelBean excel : list) {
                    System.out.println(excel.toString());
                    
                    if(repo.findByDishItemAndRestaurantid(excel.getDishnum(),
                    		Integer.parseInt(excel.getRestnum())).isEmpty()) {
                    	Dish dish = new Dish();
                        dish.setDishItem(excel.getDishnum());
                        dish.setDishPhoto(excel.getDishphoto());
                        dish.setDishPrice(excel.getPrice());
                        dish.setRestaurantid(Integer.parseInt(excel.getRestnum()));
//                      dish.setCreatePerson(tok);
                        repo.save(dish);
                    }
                    
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "保存成功";
	}
}
