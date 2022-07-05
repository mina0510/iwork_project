package tw.com.ourProject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "temptoken")
@EntityListeners(AuditingEntityListener.class)
public class Temptoken {
	@Id
	@Column(name = "tokenid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tokenId;
	
	@Column(name = "tokenvalue")
	private String tokenValue;
	
	@CreatedDate 
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@Column(name = "createtime")
	private Date createTime;

	public Integer getTokenId() {
		return tokenId;
	}

	public void setTokenId(Integer tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	@Override
	public String toString() {
		return "Temptoken [tokenId=" + tokenId + ", tokenValue=" + tokenValue + "]";
	}
	
	
}
