package tw.com.ourProject.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Order;
import tw.com.ourProject.service.OrderSystemService;
import tw.com.ourProject.utils.JWTUtil;

@RestController
public class OrderSystemController {
	@Autowired
	public OrderSystemService orderSystemService;
	@Autowired
	public JWTUtil jwt;
	
	@PostMapping("/orderSys/getMenuByDate") //陣列的第一筆object是restaurantName
	public JSONArray getMenuByDate(@RequestBody JSONObject data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
		Date date = format.parse(data.getString("setDate"));
		return orderSystemService.findMenuByDate(date);
		}catch(Exception e) {
			System.out.println(e.toString());
			return JSON.parseArray("[{state : "+ e.toString()+"}]");
		}
	}
	
	@PostMapping("/orderSys/saveToDb")
	public void saveToDb(@RequestBody JSONArray data) {
		String empId = jwt.getInfoFromJwtToken(data.getJSONObject(0).getString("userToken"), "empId");
		data.remove(0);
		orderSystemService.saveToDb(empId,data);
	}
	
	@PostMapping("/orderSys/getOrder")
	public JSONArray getOrders(@RequestBody JSONObject data) {
		String empId = jwt.getInfoFromJwtToken(data.getString("userToken"), "empId");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		Date startDate = format.parse(data.getString("startDate"));
		Date endDate = format.parse(data.getString("endDate"));
		JSONArray orders= orderSystemService.getOrderInCar(empId,startDate,endDate);
		return orders;
		}catch(Exception e) {
			JSONArray error = JSON.parseArray("[{state : "+ e.toString()+"}]");
			return error;
		}
	}
	
	@DeleteMapping("/orderSys/deleteOrderInCar")
	public void deleteOrderInCarById(@RequestBody JSONObject data) {
		orderSystemService.deleteOrderInCar(Integer.parseInt(data.getString("orderId")));
	}
	
	@PostMapping("/orderSys/changeOrderType")
	public void changeOrderType(@RequestBody JSONArray data) {
		orderSystemService.changeOrderType(data);
	}
	
	@PostMapping("/orderSys/getHistoryOrder")
	public JSONArray getHistoryOrder(@RequestBody JSONObject data) {
		String empId = jwt.getInfoFromJwtToken(data.getString("userToken"), "empId");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		Date startDate = format.parse(data.getString("startDate"));
		Date endDate = format.parse(data.getString("endDate"));
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data.getString("date"));
		JSONArray orders= orderSystemService.getHistoryOrder(empId,startDate,endDate,date);
		return orders;
		}catch(Exception e) {
			JSONArray error = JSON.parseArray("[{state : "+ e.toString()+"}]");
			return error;
		}
	}
	
	@PostMapping("/orderSys/getAllOrdersByDate")
	public JSONArray getAllOrdersByDate(@RequestBody JSONObject data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		Date startDate = format.parse(data.getString("startDate"));
		Date endDate = format.parse(data.getString("endDate"));
		System.out.println(startDate +"   "+endDate);
		JSONArray orders = orderSystemService.getAllOrdersByDate(startDate,endDate);
		return orders;
		}catch(Exception e) {
			JSONArray error = JSON.parseArray("[{state : "+ e.toString()+"}]");
			return error;
		}
	}
	
	@PostMapping("/orderSys/getAllOrdersByDateOrderByItem")
	public JSONArray getAllOrdersByDateOrderByItem(@RequestBody JSONObject data) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		Date startDate = format.parse(data.getString("startDate"));
		Date endDate = format.parse(data.getString("endDate"));
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(data.getString("date"));
		return orderSystemService.getAllOrdersByDateOrderByItem(startDate ,endDate,date);
		}catch(Exception e) {
			JSONArray error = JSON.parseArray("[{state : "+ e.toString()+"}]");
			return error;
		}
	}
	
	@PostMapping("/orderSys/getRestaurants")
	public JSONArray getRestaurants() {
		return orderSystemService.getRestaurants();
	}

	@PostMapping("/orderSys/getMenuByRestaurantId")
	public JSONArray getMenuByRestaurantId(@RequestBody JSONObject data) {
		return orderSystemService.findDishByRestaurantId(Integer.parseInt(data.getString("restaurantId")));
	}
	
	@PostMapping("/orderSys/setRestaurant")
	public void setRestaurant(@RequestBody JSONObject data) {
		System.out.println(data);
		String empId = jwt.getInfoFromJwtToken(data.getString("userToken"), "empId");
		orderSystemService.setRestaurant(data,empId);
	}
}
