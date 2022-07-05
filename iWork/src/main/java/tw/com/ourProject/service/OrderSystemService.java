package tw.com.ourProject.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tw.com.ourProject.model.Dish;
import tw.com.ourProject.model.Order;
import tw.com.ourProject.model.Restaurant;
import tw.com.ourProject.model.Restaurantset;
import tw.com.ourProject.repository.DishRepo;
import tw.com.ourProject.repository.EmployeeRepo;
import tw.com.ourProject.repository.OrderRepo;
import tw.com.ourProject.repository.RestaurantRepo;
import tw.com.ourProject.repository.RestaurantSetRepo;

@Service
public class OrderSystemService {
	
	@Autowired
	public DishRepo dishRepo;
	
	@Autowired
	public RestaurantRepo restaurantRepo;
	
	@Autowired
	public OrderRepo orderRepo ;
	
	@Autowired
	public RestaurantSetRepo restaurantSetRepo;
	
	@Autowired
	public EmployeeRepo employeeRepo;
	
	

	public Order order = new Order();
	public Dish dish = new Dish();
	public Restaurant restaurant = new Restaurant();
	public Restaurantset restaurantset = new Restaurantset();

	
	public JSONArray findMenuByDate(Date date) {
		restaurant = restaurantSetRepo.findBySetDate(date).getRestaurants();
		List<Dish> menus =dishRepo.findByRestaurantid(restaurant.getRestaurantId());
		JSONArray jsonArr = new JSONArray();
		JSONObject restaurantName = new JSONObject();
		restaurantName.put("restaurantName", restaurant.getRestaurantName());
		jsonArr.add(restaurantName);
		for(int i = 0 ;i<menus.size() ;i++) {
			JSONObject obj = new JSONObject();
			obj.put("dishId",menus.get(i).getDishId());
			obj.put("dishItem",menus.get(i).getDishItem());
			obj.put("dishPrice",menus.get(i).getDishPrice());
			obj.put("dishPhoto",menus.get(i).getDishPhoto());
			jsonArr.add(obj);
		}
		return jsonArr;
	}
	
	public JSONArray findDishByRestaurantId(Integer restaurantId) {
		List<Dish> menus =dishRepo.findByRestaurantid(restaurantId);
		JSONArray jsonArr = new JSONArray();
		for(int i = 0 ;i<menus.size() ;i++) {
			JSONObject obj = new JSONObject();
			obj.put("dishId",menus.get(i).getDishId());
			obj.put("dishItem",menus.get(i).getDishItem());
			obj.put("dishPrice",menus.get(i).getDishPrice());
			obj.put("dishPhoto",menus.get(i).getDishPhoto());
			
			jsonArr.add(obj);
		}
		return jsonArr;
	}
	
	public void saveToDb(String empId , JSONArray data) {
		for(int i=0 ; i<data.size() ;i++) {
			order = new Order();
			dish.setDishId(Integer.parseInt(data.getJSONObject(i).get("dishId").toString()));
			order.setEmpId(empId);
			order.setDishes(dish);
			order.setQty(Integer.parseInt(data.getJSONObject(i).get("qty").toString()));
			order.setType("購物車");
			orderRepo.save(order);

		}
		
	}

	public JSONArray getOrderInCar(String empId , Date startDate , Date endDate) {
		List<Order> rs = orderRepo.findByEmpIdAndDateBetweenAndType(empId,startDate,endDate,"購物車");
		
		JSONArray jArry = new JSONArray();
		for(Order order : rs) {
			JSONObject obj = new JSONObject();
			obj.put("orderId", order.getOrderId());
			obj.put("dishItem", order.getDishes().getDishItem());
//			obj.put("dishPhoto", order.getDishes().getDishPhoto());
			obj.put("dishPrice", order.getDishes().getDishPrice());
			obj.put("qty", order.getQty());
//			obj.put("orderMemo", order.getOrderMemo());
			jArry.add(obj);
		}
		return jArry;
	}
	
	@Transactional
	public void deleteOrderInCar(Integer orderId) {
		orderRepo.deleteById(orderId);
	}

	@Transactional
	public void changeOrderType(JSONArray data) {
		for(int i = 0 ; i<data.size() ;i++) {
		Integer orderId = Integer.parseInt(data.getJSONObject(i).getString("orderId"));
		order = orderRepo.findById(orderId).get();
		order.setType("出貨");
		orderRepo.save(order);
		}
	}
	
	//查詢個人訂單記錄，第一筆資料為餐廳名字
	public  JSONArray getHistoryOrder(String empId, Date startDate , Date endDate,Date date) {
		List<Order> orders = orderRepo.findByEmpIdAndDateBetweenAndType(empId,startDate,endDate,"出貨");
		restaurant = restaurantSetRepo.findBySetDate(date).getRestaurants();
		JSONArray jArry = new JSONArray();
		JSONObject restaurantName = new JSONObject();
		restaurantName.put("restaurantName", restaurant.getRestaurantName());
		jArry.add(restaurantName);
		for(Order order : orders) {
			JSONObject obj = new JSONObject();
			obj.put("orderId", order.getOrderId());
			obj.put("dishItem", order.getDishes().getDishItem());
			obj.put("dishPrice", order.getDishes().getDishPrice());
			obj.put("qty", order.getQty());
			jArry.add(obj);
		}
		return jArry;
	}
	
	public  JSONArray getAllOrdersByDate(Date startDate , Date endDate) {
		List<Order> orders = orderRepo.findByDateBetweenAndType(startDate,endDate,"出貨");
		
		JSONArray jArry = new JSONArray();
		for(Order order : orders) {
			JSONObject obj = new JSONObject();
			obj.put("orderId", order.getOrderId());
			obj.put("empName",employeeRepo.findByEmpId(order.getEmpId()).getEmpName());
			obj.put("dishItem", order.getDishes().getDishItem());
			obj.put("dishPrice", order.getDishes().getDishPrice());
			obj.put("qty", order.getQty());
			jArry.add(obj);
		}
		return jArry;
	}
	
	public JSONArray getAllOrdersByDateOrderByItem(Date startDate , Date endDate,Date date) {
		List<Object[]> objArry = orderRepo.findOrdersGroupBy(startDate ,endDate);
		JSONArray jsonAr = new JSONArray();
		Restaurantset temp = restaurantSetRepo.findBySetDate(date);
		if(temp == null) {
			return jsonAr;
		}else {
			restaurant = temp.getRestaurants();
		}
		JSONObject restaurantName = new JSONObject();
		restaurantName.put("restaurantName", restaurant.getRestaurantName());
		jsonAr.add(restaurantName);
	          for (Object[] object : objArry) {
	        	String dishItem = dishRepo.findById(Integer.parseInt(object[0].toString())).get().getDishItem();
	        	Integer dishPrice = dishRepo.findById(Integer.parseInt(object[0].toString())).get().getDishPrice();
	        	JSONObject obj = new JSONObject();
	        	obj.put("dishItem", dishItem);
	        	obj.put("dishPrice", dishPrice);
	        	obj.put("qty", orderRepo.countOrdersQty(startDate, endDate, object[0].toString()));
	        	jsonAr.add(obj);	        	
	          }
	          return jsonAr;
	       
	
	}
	
	public JSONArray getRestaurants() {
		List<Restaurant> restaurants= restaurantRepo.findAll();
		JSONArray jArry = new JSONArray();
		for(Restaurant restaurant :restaurants) {
			JSONObject obj = new JSONObject();
			obj.put("restaurantId", restaurant.getRestaurantId());
			obj.put("restaurantName", restaurant.getRestaurantName());
			jArry.add(obj);
		}
		return jArry;
	}

	public void setRestaurant(JSONObject data ,String empId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
		Date date = format.parse(data.getString("setDate"));
		System.out.println(date);
		restaurantset = restaurantSetRepo.findBySetDate(date);
		if(restaurantset == null) {
			Restaurantset newRestaurantset = new Restaurantset();
			newRestaurantset.setSetDate(date);
			newRestaurantset.setRestaurants(restaurantRepo.findById(data.getInteger("restaurantId")).get());
			newRestaurantset.setCreatePerson(empId);
			newRestaurantset.setUpdatePerson(empId);
			restaurantSetRepo.save(newRestaurantset);
		}else {
			restaurantset.setSetDate(date);
			restaurantset.setRestaurants(restaurantRepo.findById(data.getInteger("restaurantId")).get());
			restaurantset.setUpdatePerson(empId);
			restaurantSetRepo.save(restaurantset);			
		}
		}catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}
