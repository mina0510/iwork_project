package tw.com.ourProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ourProject.model.Announcement;
import tw.com.ourProject.model.Attach;

@Repository
public interface AttachRepo extends JpaRepository<Attach, Integer>{
	List<Attach> findByAnnouncements(Announcement announcements);
}
