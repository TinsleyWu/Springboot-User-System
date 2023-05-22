package tw.com.example.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;

public class HrSysLeavePkey implements Serializable {

	private Integer year ;

	private String iden ;
	
	@Column(name = "LEAVE_TYPE")
	private String leaveType ;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	
}
