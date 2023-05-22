package tw.com.example.rest.vo;

import java.util.Date;

public class Ma004ResponseBody {
	
	private String iden ;
	
	private String cname ;
	
	private String ename ;
	
	private Integer age;
	
	private Ma004ResponseBodyAddress address;
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Ma004ResponseBodyAddress getAddress() {
		return address;
	}

	public void setAddress(Ma004ResponseBodyAddress address) {
		this.address = address;
	}

	private Date birth ;
	
	private String sex ;

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

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
