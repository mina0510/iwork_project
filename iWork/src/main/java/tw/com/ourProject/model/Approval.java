package tw.com.ourProject.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Configuration;

@Entity
@Configuration
@Table(name="approval")
public class Approval {
	@OneToMany(mappedBy = "approvals",cascade = CascadeType.ALL)
	private Set<Attendance> attendances;
	
	@Id
	@Column(name = "approvalid")
	private Integer approvalId;
	
	@Column(name = "approvaltype")
	private String approvalType;


	public Integer getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(Integer approvalId) {
		this.approvalId = approvalId;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	@Override
	public String toString() {
		return "Approval [approvalId=" + approvalId + ", approvalType=" + approvalType + "]";
	}
	
	
}
