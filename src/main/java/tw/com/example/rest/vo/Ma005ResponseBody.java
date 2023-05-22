package tw.com.example.rest.vo;

import java.util.List;

public class Ma005ResponseBody {
	
	 private String iden ;
	 
	 private String cname;
	 
	 private List<Ma005ResponseLeaves> leaves;
	 
	 public List<Ma005ResponseLeaves> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Ma005ResponseLeaves> leaves) {
		this.leaves = leaves;
	}

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	private Integer year ;


}
