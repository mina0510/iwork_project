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
@Table(name="leaves")
public class Leaves {
	@OneToMany(mappedBy = "leaves",cascade = CascadeType.ALL)
	private Set<Attendance> attendances;
	
	@Id
	@Column(name = "leaveid")
	private Integer leaveId;
	
	@Column(name = "leavetype")
	private String leaveType;


	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	@Override
	public String toString() {
		return "Leaves [ leaveId=" + leaveId + ", leaveType=" + leaveType + "]";
	}
	
	
	
}
