package tw.com.ourProject.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.com.ourProject.model.Order;

public interface OrderRepo  extends JpaRepository<Order, Integer>{

	List<Order> findByEmpIdAndDateBetweenAndType(String empId,Date startDate,Date endDate,String type);
	
	List<Order> findByEmpIdAndDateAndType(String empId,Date date ,String type);
	
	List<Order> findByDateBetweenAndType(Date startDate,Date endDate,String type);
	
	@Query(value="SELECT dishId , COUNT(*) FROM orders "
			+ "WHERE date BETWEEN :startDate AND :endDate AND type='出貨'"
			+ "GROUP BY dishId;",
			nativeQuery=true)	
	List<Object[]> findOrdersGroupBy(@Param("startDate")Date startDate ,@Param("endDate")Date endDate);
	
	@Query(value="SELECT SUM(qty) FROM orders "
			+ "WHERE date BETWEEN :startDate AND :endDate AND type='出貨' AND dishId = :dishId ;",
			nativeQuery=true)	
	List<Object[]> countOrdersQty(@Param("startDate")Date startDate ,@Param("endDate")Date endDate,@Param("dishId") String dishId);
	
}
