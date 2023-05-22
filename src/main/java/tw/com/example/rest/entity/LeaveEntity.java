package tw.com.example.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HR_SYS_LEAVE")
public class LeaveEntity {
	
	@EmbeddedId
	private HrSysLeavePkey pk ;

	@Column(name = "LEAVE_OWN_HOURS")
	private Integer leaveOwnHours;
	
	@Column(name = "LEAVE_APPLY_HOURS")
	private Integer leaveApplyHours;

	public HrSysLeavePkey getPk() {
		return pk;
	}

	public void setPk(HrSysLeavePkey pk) {
		this.pk = pk;
	}

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
	
	
	
}