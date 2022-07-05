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

import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="attendance")
@Configuration
@EntityListeners(AuditingEntityListener.class)
public class Attendance {
	@OneToMany(mappedBy = "attendances",cascade = CascadeType.ALL)
	private Set<Approvalrank> approvalranks;
	
	@Id
	@Column(name = "attendanceid",columnDefinition="char(11)")
	private String attendanceId;
	
	@ManyToOne
	@JoinColumn(name="empid",referencedColumnName = "empid")
	private Employee employees;
	
	@ManyToOne
	@JoinColumn(name="leaveid",referencedColumnName = "leaveid")
	private Leaves leaves;
	
	@Column(name = "contenttext")
	private String contentText;
	
	@Column(name = "startdate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date startDate;
	
	@Column(name = "enddate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date endDate;
	
	@Column(name = "hours")
	private Integer hours;
	
	@ManyToOne
	@JoinColumn(name="approvalid",referencedColumnName = "approvalid")
	private Approval approvals;
	
	@ManyToOne
	@JoinColumn(name = "createperson",referencedColumnName = "empid")
	private Employee employeess ;
	
	@ManyToOne
	@JoinColumn(name = "updateperson",referencedColumnName = "empid")
	private Employee employeesss ;
	
	@LastModifiedDate 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "updatedate")
	private java.util.Date updateDate ;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "createdate")
	private java.util.Date createDate ;

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Employee getEmployees() {
		return employees;
	}

	public void setEmployees(Employee employees) {
		this.employees = employees;
	}

	public Leaves getLeaves() {
		return leaves;
	}

	public void setLeaves(Leaves leaves) {
		this.leaves = leaves;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Approval getApprovals() {
		return approvals;
	}

	public void setApprovals(Approval approvals) {
		this.approvals = approvals;
	}


	public Employee getEmployeess() {
		return employeess;
	}

	public void setEmployeess(Employee employeess) {
		this.employeess = employeess;
	}

	public Employee getEmployeesss() {
		return employeesss;
	}

	public void setEmployeesss(Employee employeesss) {
		this.employeesss = employeesss;
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
		return "Attendance [approvalranks=" + approvalranks + ", attendanceId=" + attendanceId + ", employees="
				+ employees + ", leaves=" + leaves + ", contentText=" + contentText + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", hours=" + hours + ", approvals=" + approvals + ", employeess="
				+ employeess + ", employeesss=" + employeesss + ", updateDate=" + updateDate + ", createDate="
				+ createDate + "]";
	}

}
