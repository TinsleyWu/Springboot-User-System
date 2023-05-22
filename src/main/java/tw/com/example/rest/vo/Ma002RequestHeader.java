package tw.com.example.rest.vo;

import java.sql.Date;

public class Ma002RequestHeader {
	
	private String sys ;
	
	private String api ;
	
	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	private Date time ;

}
