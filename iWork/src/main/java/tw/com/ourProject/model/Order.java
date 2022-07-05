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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="orders")
@EntityListeners(AuditingEntityListener.class)
public class Order {
	
	@Id
	@Column(name = "orderid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@Column(name="empid",columnDefinition = "char(11)")
	private String empId;
	
	@CreatedDate 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="dishid ",referencedColumnName = "dishid")
	private Dish dishes;
	
	@Column(name = "qty")
	private Integer qty;
	
	@Column(name = "ordermemo")
	private String orderMemo;
	
	@Column(name = "type",columnDefinition="ENUM('購物車','出貨')")
	private String type;

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", empId=" + empId + ", date=" + date + ", dishes=" + dishes + ", qty="
				+ qty + ", orderMemo=" + orderMemo + ", type=" + type + "]";
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public Dish getDishes() {
		return dishes;
	}

	public void setDishes(Dish dishes) {
		this.dishes = dishes;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getOrderMemo() {
		return orderMemo;
	}

	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
