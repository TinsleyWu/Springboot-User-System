package tw.com.example.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HR_SYS_CAL")
public class HrSysCalEntity {
	
	@Id
	private Integer id ;
	
	private String sys ;
	
	@Column(name = "CAL_YEAR")
	private Integer calYear ;
	
	@Column(name = "CAL_DATE")
	private Date calDate ; 
	
	@Column(name = "CAL_DAY")
	private String calDay ;
	
	@Column(name = "IS_HOLIDAY")
	private String isHoliday ;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public Integer getCalYear() {
		return calYear;
	}

	public void setCalYear(Integer calYear) {
		this.calYear = calYear;
	}

	public Date getCalDate() {
		return calDate;
	}

	public void setCalDate(Date calDate) {
		this.calDate = calDate;
	}

	public String getCalDay() {
		return calDay;
	}

	public void setCalDay(String calDay) {
		this.calDay = calDay;
	}

	public String getIsHoliday() {
		return isHoliday;
	}

	public void setIsHoliday(String isHoliday) {
		this.isHoliday = isHoliday;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String remark ;
	
	

}
