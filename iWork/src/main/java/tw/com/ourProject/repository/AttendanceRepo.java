package tw.com.ourProject.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.com.ourProject.model.Attendance;
import tw.com.ourProject.model.Employee;

@Transactional
public interface AttendanceRepo extends JpaRepository<Attendance, String> {
	Attendance findByAttendanceId(String attendanceId);
	List<Attendance> findByEmployees(Employee employees);
	Attendance findTopByOrderByAttendanceIdDesc();
	
	
}
