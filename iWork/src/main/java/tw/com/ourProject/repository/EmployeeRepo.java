package tw.com.ourProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Apart;
import tw.com.ourProject.model.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, String>{
	
	
	Employee findByEmpId(String empId);
	
	List<Employee> findByApartsAndAdm(Apart apart,String adm);
	List<Employee> findByAparts(Apart apart);
	List<Employee> findByAdm(String adm);
	List<Employee> findByEmpName(String empName);
	Boolean existsByEmpName(String empName);
}
