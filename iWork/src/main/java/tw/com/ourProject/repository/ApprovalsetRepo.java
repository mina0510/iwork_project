package tw.com.ourProject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Apart;
import tw.com.ourProject.model.Approvalset;
import tw.com.ourProject.model.Employee;

@Transactional
public interface ApprovalsetRepo extends JpaRepository<Approvalset, Integer>{
	Approvalset findByApprovalSetId(Integer approvalSetId);
	Approvalset findByAparts(Apart aparts);
	Approvalset findByEmployees(Employee employees);
	List<Approvalset> findByEmployee(Employee employee);
	
}
