package tw.com.ourProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ourProject.model.Leaves;

@Repository
public interface LeavesRepo extends JpaRepository<Leaves, Integer>{

}
