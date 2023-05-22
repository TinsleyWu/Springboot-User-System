package tw.com.example.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HR_SYS_LEAVE_DTL")
public class LeaveDtlEntity {
	
	@EmbeddedId
	private HrSysLeaveDtlPkey pk ;

	@Column(name = "LEAVE_OWN_HOURS")
	private Integer leaveOwnHours;
	
	@Column(name = "LEAVE_APPLY_HOURS")
	private Integer leaveApplyHours;
	
	@Column(name = "LEAVE_BEGIN_TIME")
	private Date leaveBeginTime;
	
	@Column(name = "LEAVE_END_TIME")
	private Date leaveEndTime;
	
	public String getCreUser() {
		return creUser;
	}

	public void setCreUser(String creUser) {
		this.creUser = creUser;
	}

	@Column(name = "CRE_USER")
	private String creUser;
	
	public Integer getLeaveOwnHours() {
		return leaveOwnHours;
	}

	public void setLeaveOwnHours(Integer leaveOwnHours) {
		this.leaveOwnHours = leaveOwnHours;
	}

	public Integer getLeaveApplyHours() {
		return leaveApplyHours;
	}

	public void setLeaveApplyHours(Integer leaveApplyHours) {
		this.leaveApplyHours = leaveApplyHours;
	}

	public Date getLeaveBeginTime() {
		return leaveBeginTime;
	}

	public void setLeaveBeginTime(Date leaveBeginTime) {
		this.leaveBeginTime = leaveBeginTime;
	}

	public Date getLeaveEndTime() {
		return leaveEndTime;
	}

	public void setLeaveEndTime(Date leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CRE_TIME")
	private Date creTime;
	
	private String remark;

	public HrSysLeaveDtlPkey getPk() {
		return pk;
	}

	public void setPk(HrSysLeaveDtlPkey pk) {
		this.pk = pk;
	}
	
	
	
}