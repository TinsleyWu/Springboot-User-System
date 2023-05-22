package tw.com.example.rest.vo;

import java.util.Date;

public class Ma006ResponseLeaves {
	private String type ;
	 
	 private String typeDesc ;
	 
	 private Integer ownHours ;
	 
	 private Integer applyHours ;
	 
	 private Date beginTime ;
	 
	 private Date endTime ;
	 
	 private String remark;
	 
	 public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreTime() {
		return creTime;
	}

	public void setCreTime(Date creTime) {
		this.creTime = creTime;
	}

	private Date creTime ;

    public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOwnHours() {
		return ownHours;
	}

	public void setOwnHours(Integer ownHours) {
		this.ownHours = ownHours;
	}

	public Integer getApplyHours() {
		return applyHours;
	}

	public void setApplyHours(Integer applyHours) {
		this.applyHours = applyHours;
	}
}
