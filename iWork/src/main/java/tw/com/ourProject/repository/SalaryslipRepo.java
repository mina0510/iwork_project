package tw.com.ourProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Salaryslip;

public interface SalaryslipRepo extends JpaRepository<Salaryslip,Integer>{
	
	Salaryslip findByEmpId(String empId);
}
