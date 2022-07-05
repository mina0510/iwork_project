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
@Table(name="restaurantset")
public class Restaurantset {

	@Id
	@Column(name = "setid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer setId;
	
	@Column(name = "setdate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private java.util.Date setDate;
	
	@ManyToOne
	@JoinColumn(name="restaurantid",referencedColumnName = "restaurantid")
	private Restaurant restaurants;

	@Column(name = "createperson" ,columnDefinition = "char(11)")
	private String createPerson;
	
	@Column(name = "updateperson" ,columnDefinition = "char(11)")
	private String updatePerson;

	

	@Override
	public String toString() {
		return "Restaurantset [setId=" + setId + ", setDate=" + setDate + ", restaurants=" + restaurants
				+ ", createPerson=" + createPerson + ", updatePerson=" + updatePerson + "]";
	}

	public Integer getSetId() {
		return setId;
	}

	public void setSetId(Integer setId) {
		this.setId = setId;
	}

	public java.util.Date getSetDate() {
		return setDate;
	}

	public void setSetDate(java.util.Date setDate) {
		this.setDate = setDate;
	}

	public Restaurant getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Restaurant restaurants) {
		this.restaurants = restaurants;
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
