package tw.com.ourProject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.ourProject.model.Announcement;

@Transactional
public interface AnnouncementRepo extends JpaRepository<Announcement, Integer> {
	Announcement findByAnnounceId(Integer announceId);
}
