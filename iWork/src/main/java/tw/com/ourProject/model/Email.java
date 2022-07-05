package tw.com.ourProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="email")
public class Email {
	@Id
	@Column(name = "emailid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer emailId;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "content")
	private String content;
	
	@ManyToOne
	@JoinColumn(name="sender",referencedColumnName = "empid")
	@JoinColumn(name="recipient",referencedColumnName = "empid")
	private Employee employees;
	
	@Column(name = "sendtime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date sendtime;
	
}
