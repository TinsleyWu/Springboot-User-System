package tw.com.example.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HR_SYS_LOGIN")
public class LoginEntity {
	
	
	private String token ;
	@Id
	private String iden ;
	
	
	@Column(name = "CRE_TIME")
	private Date creTime ;
	
	private Date effectiveTime ;
	
	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date cretime2) {
		this.creTime = cretime2;
	}

	public Date getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	
	
	
}