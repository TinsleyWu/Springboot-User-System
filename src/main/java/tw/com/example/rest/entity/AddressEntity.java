package tw.com.example.rest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HR_SYS_STAFF_ADDR")
public class AddressEntity {
	
	@EmbeddedId
	private HrSysAddrPkey pk ;

	public HrSysAddrPkey getPk() {
		return pk;
	}

	public void setPk(HrSysAddrPkey pk) {
		this.pk = pk;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Column(name = "ZIP_CODE")
	private String zipCode ;
	
	private String addr ;

	
	
	
}