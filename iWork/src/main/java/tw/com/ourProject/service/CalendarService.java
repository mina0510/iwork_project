package tw.com.ourProject.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.ourProject.model.Apart;
import tw.com.ourProject.model.Approvalset;
import tw.com.ourProject.model.Calendar;
import tw.com.ourProject.model.Employee;
import tw.com.ourProject.repository.CalendarRepo;

@Service
@Transactional
public class CalendarService {
	@Autowired
	public CalendarRepo calendarRepo;
	
	public List<Calendar> findEvents(){
		return calendarRepo.findAll();
	}
	
	public void saveEvent(String calendartype, String calendartitle, Date eventstart, Date eventEnd, String allday) {
		Calendar calendarInfo = new Calendar();
		calendarInfo.setCalendarType(calendartype);
		calendarInfo.setCalendarTitle(calendartitle);
		calendarInfo.setEventStart(eventstart);
		calendarInfo.setEventEnd(eventEnd);
		calendarInfo.setAllDay(allday);
		calendarRepo.save(calendarInfo);
		
	}
	
	public void deleteEvent(Integer calendarid) {
		calendarRepo.deleteById(calendarid);
	}
	
	public void updateEvent(Integer calendarid, String calendartype, String calendartitle, Date eventstart, Date eventEnd, String allday) {
		Calendar calendarInfo = calendarRepo.findByCalendarId(calendarid);
		calendarInfo.setCalendarType(calendartype);
		calendarInfo.setCalendarTitle(calendartitle);
		calendarInfo.setEventStart(eventstart);
		calendarInfo.setEventEnd(eventEnd);
		calendarInfo.setAllDay(allday);
		calendarRepo.save(calendarInfo);
		
	}
}
