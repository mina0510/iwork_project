package tw.com.ourProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Dish;
import tw.com.ourProject.model.Restaurant;

public interface DishRepo extends JpaRepository<Dish, Integer> {
	
	Optional<Dish> findByDishItemAndRestaurantid(String dishItem,Integer restaurantId);

	List<Dish> findByRestaurantid(Integer restaurantId);

	

}
 