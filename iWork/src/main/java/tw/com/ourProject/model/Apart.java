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
@Table(name="apart")
@Configuration
public class Apart {
	@OneToMany(mappedBy = "aparts",cascade = CascadeType.ALL)
	private Set<Approvalset> approvalsets;
	@OneToMany(mappedBy = "aparts",cascade = CascadeType.ALL)
	private Set<Employee> employees;

	@Id
	@Column(name = "apartid")
	private Integer apartId;
	
	@Column(name = "apart")
	private String apart;
	
	@Override
	public String toString() {
		return "Apart [apartId=" + apartId + ", apart=" + apart + "]";
	}

	public Integer getApartId() {
		return apartId;
	}

	public void setApartId(Integer apartId) {
		this.apartId = apartId;
	}

	public String getApart() {
		return apart;
	}

	public void setApart(String apart) {
		this.apart = apart;
	}
}
