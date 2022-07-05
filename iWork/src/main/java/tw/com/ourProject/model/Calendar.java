package tw.com.ourProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="calendar")
public class Calendar {
	@Id
	@Column(name = "calendarid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer calendarId;
	
	@Column(name = "calendartype",columnDefinition="ENUM('個人','部門','公司')")
	private String calendarType;
	
	@Column(name = "calendartitle")
	private String calendarTitle;
	
	@Column(name = "eventstart")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date eventStart;
	
	@Column(name = "eventend")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date eventEnd;
	
	@Column(name = "allday",columnDefinition="ENUM('Y','N')")
	private String allDay;

	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}

	public String getCalendarType() {
		return calendarType;
	}

	public void setCalendarType(String calendarType) {
		this.calendarType = calendarType;
	}

	public String getCalendarTitle() {
		return calendarTitle;
	}

	public void setCalendarTitle(String calendarTitle) {
		this.calendarTitle = calendarTitle;
	}

	public java.util.Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(java.util.Date eventStart) {
		this.eventStart = eventStart;
	}

	public java.util.Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(java.util.Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public String getAllDay() {
		return allDay;
	}

	public void setAllDay(String allDay) {
		this.allDay = allDay;
	}

	@Override
	public String toString() {
		return "Calendar [calendarId=" + calendarId + ", calendarType=" + calendarType + ", calendarTitle="
				+ calendarTitle + ", eventStart=" + eventStart + ", eventEnd=" + eventEnd + ", allDay=" + allDay + "]";
	}
	
}
