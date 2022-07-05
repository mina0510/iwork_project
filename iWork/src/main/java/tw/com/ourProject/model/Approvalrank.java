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
@Table(name="approvalrank")
public class Approvalrank {
	@Id
	@Column(name = "approvalrankid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer approvalRankId;
	
	@ManyToOne
	@JoinColumn(name="attendanceid",referencedColumnName = "attendanceid")
	private Attendance attendances;
	
	@Column(name = "attendancedate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date attendanceDate;
	
	@Column(name = "approver1")
	private String approver1;
	
	@Column(name = "dateapproved1")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date dateApproved1;
	
	@Column(name = "approver2")
	private String approver2;
	
	@Column(name = "dateapproved2")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date dateApproved2;

	public Integer getApprovalRankId() {
		return approvalRankId;
	}

	public void setApprovalRankId(Integer approvalRankId) {
		this.approvalRankId = approvalRankId;
	}

	public Attendance getAttendances() {
		return attendances;
	}

	public void setAttendances(Attendance attendances) {
		this.attendances = attendances;
	}

	public java.util.Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(java.util.Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getApprover1() {
		return approver1;
	}

	public void setApprover1(String approver1) {
		this.approver1 = approver1;
	}

	public java.util.Date getDateApproved1() {
		return dateApproved1;
	}

	public void setDateApproved1(java.util.Date dateApproved1) {
		this.dateApproved1 = dateApproved1;
	}

	public String getApprover2() {
		return approver2;
	}

	public void setApprover2(String approver2) {
		this.approver2 = approver2;
	}

	public java.util.Date getDateApproved2() {
		return dateApproved2;
	}

	public void setDateApproved2(java.util.Date dateApproved2) {
		this.dateApproved2 = dateApproved2;
	}

	@Override
	public String toString() {
		return "Approvalrank [approvalRankId=" + approvalRankId + ", attendances=" + attendances + ", attendanceDate="
				+ attendanceDate + ", approver1=" + approver1 + ", dateApproved1=" + dateApproved1 + ", approver2="
				+ approver2 + ", dateApproved2=" + dateApproved2 + "]";
	}
	
	
}
