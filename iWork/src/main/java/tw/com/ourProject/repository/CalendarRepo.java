package tw.com.ourProject.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.ourProject.model.Calendar;

@Transactional
@Repository
public interface CalendarRepo extends JpaRepository<Calendar, Integer> {
	Calendar findByCalendarId(Integer calendarId);
}
