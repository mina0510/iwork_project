package tw.com.ourProject.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Restaurantset;

public interface RestaurantSetRepo extends JpaRepository<Restaurantset, Integer> {
	
	Restaurantset findBySetDate(Date setDate);
}
