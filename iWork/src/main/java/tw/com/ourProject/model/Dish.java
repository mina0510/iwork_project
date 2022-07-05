package tw.com.ourProject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="dish")
@EntityListeners(AuditingEntityListener.class)
public class Dish {
	
	@Id
	@Column(name = "dishid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dishId ;
	
	@Column(name = "dishitem")
	private String dishItem ;
	
	
	@Column(name = "dishphoto")
	private String dishPhoto ;
	
	@Column(name = "dishprice")
	private Integer dishPrice ;
	
	@Column(name="restaurantid")
	private Integer restaurantid;
	
	
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

	public Integer getDishId() {
		return dishId;
	}

	public void setDishId(Integer dishId) {
		this.dishId = dishId;
	}

	public String getDishItem() {
		return dishItem;
	}
	

	public void setDishItem(String dishItem) {
		this.dishItem = dishItem;
	}

	public String getDishPhoto() {
		return dishPhoto;
	}

	public void setDishPhoto(String dishPhoto) {
		this.dishPhoto = dishPhoto;
	}

	public Integer getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(Integer dishPrice) {
		this.dishPrice = dishPrice;
	}

	public Integer getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(Integer restaurantid) {
		this.restaurantid = restaurantid;
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

	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", dishItem=" + dishItem + ", dishPhoto=" + dishPhoto + ", dishPrice="
				+ dishPrice + ", restaurantid=" + restaurantid + ", createPerson=" + createPerson + ", updatePerson="
				+ updatePerson + ", updateDate=" + updateDate + ", createDate=" + createDate + "]";
	}
	
	

}