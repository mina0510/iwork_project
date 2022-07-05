package tw.com.ourProject.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="restaurant")
@EntityListeners(AuditingEntityListener.class)
public class Restaurant {
//	@OneToMany(mappedBy = "restaurants",cascade = CascadeType.ALL)
//	private Set<Dish> dishes;
//	@OneToMany(mappedBy = "restaurants",cascade = CascadeType.ALL)
//	private Set<Restaurantset> restaurantsets;
//	
	@Id
	@Column(name = "restaurantid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer restaurantId ;
	
	@Column(name = "restaurantname")
	private String restaurantName ;
	
	@Column(name = "tel")
	private String tel ;
	
	@Column(name = "addr")
	private String addr ;
	
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

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", tel=" + tel
				+ ", addr=" + addr + ", createPerson=" + createPerson + ", updatePerson=" + updatePerson
				+ ", updateDate=" + updateDate + ", createDate=" + createDate + "]";
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	
}
