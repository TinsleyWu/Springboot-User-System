package tw.com.example.rest.vo;

public class Ma005ResponseLeaves {
	
	 private String type ;
	 
	 private String typeDesc ;
	 
	 private Integer ownHours ;
	 
	 private Integer applyHours ;

     public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	private Integer totalHours ;


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
