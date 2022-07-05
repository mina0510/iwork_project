package tw.com.ourProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="attach")
@EntityListeners(AuditingEntityListener.class)
public class Attach {
	@Id
	@Column(name = "attachmentid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attachmentId;
	
	@Column(name = "attname")
	private String attName;
	
	@ManyToOne
	@JoinColumn(name="announceid",referencedColumnName = "announceid")
	private Announcement announcements;
	
	@LastModifiedDate 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "updatedate")
	private Date updateDate;
	
	@CreatedDate 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "createdate")
	private Date createDate;
	
	@Column(name = "createperson",columnDefinition="char(11)")
	private String createPerson ;
	
	@Column(name = "updateperson",columnDefinition="char(11)")
	private String updatePerson ;

	
	
	@Override
	public String toString() {
		return "Attach [attachmentId=" + attachmentId + ", attName=" + attName + ", announcements=" + announcements
				+ ", updateDate=" + updateDate + ", createDate=" + createDate + ", createPerson=" + createPerson
				+ ", updatePerson=" + updatePerson + "]";
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public Announcement getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(Announcement announcements) {
		this.announcements = announcements;
	}

	
	
}

