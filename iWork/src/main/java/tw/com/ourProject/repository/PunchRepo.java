package tw.com.ourProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Punch;

public interface PunchRepo  extends JpaRepository<Punch, Integer> {

	List<Punch> findTop10ByPersonOrderByTimeDesc(String person);
}
