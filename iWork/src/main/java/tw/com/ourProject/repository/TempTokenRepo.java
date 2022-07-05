package tw.com.ourProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Temptoken;

public interface TempTokenRepo extends JpaRepository<Temptoken, Integer>{
	
	Boolean existsBy();
	
	
	void deleteByTokenValue(String usertoken);

}
