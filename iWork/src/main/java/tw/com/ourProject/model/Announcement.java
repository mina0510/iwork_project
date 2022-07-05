package tw.com.ourProject.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="announcement")
@EntityListeners(AuditingEntityListener.class)
public class Announcement {
	@OneToMany(mappedBy = "announcements",cascade = CascadeType.ALL)
	private Set<Attach> attaches;
	
	@Id
	@Column(name = "announceid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer announceId;
	
	@Column(name = "type",columnDefinition="ENUM('最新公告')")
	private String type;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "uploaddate")
	private java.util.Date uploadDate;
	
	@ManyToOne
	@JoinColumn(name="uploader",referencedColumnName = "empid")
	private Employee employees;
	
//  FK不需要，上面會產生
//	@Column(name = "uploader",columnDefinition="char(11)")
//	private String uploader;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "removed")
	private java.util.Date removed;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "createperson",columnDefinition="char(11)")
	private String createPerson ;
	
	@Column(name = "updateperson",columnDefinition="char(11)")
	private String updatePerson ;
	
	@LastModifiedDate 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "updatedate")
	private java.util.Date updateDate ;
	
	@CreatedDate 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "createdate")
	private java.util.Date createDate ;

	public Integer getAnnounceId() {
		return announceId;
	}

	public void setAnnounceId(Integer announceId) {
		this.announceId = announceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public java.util.Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(java.util.Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Employee getEmployees() {
		return employees;
	}

	public void setEmployees(Employee employees) {
		this.employees = employees;
	}

	public java.util.Date getRemoved() {
		return removed;
	}

	public void setRemoved(java.util.Date removed) {
		this.removed = removed;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Announcement [attaches=" + attaches + ", announceId=" + announceId + ", type=" + type + ", uploadDate="
				+ uploadDate + ", employees=" + employees + ", removed=" + removed + ", title=" + title + ", content="
				+ content + ", createPerson=" + createPerson + ", updatePerson=" + updatePerson + ", updateDate="
				+ updateDate + ", createDate=" + createDate + "]";
	}



	
	
}
