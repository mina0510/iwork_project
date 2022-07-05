package tw.com.ourProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer>{
	Optional<Restaurant> findByRestaurantName(String restaurantName); 
}
