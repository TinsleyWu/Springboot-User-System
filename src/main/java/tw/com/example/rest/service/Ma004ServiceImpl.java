package tw.com.example.rest.service;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.example.rest.entity.AddressEntity;
import tw.com.example.rest.entity.LoginEntity;
import tw.com.example.rest.entity.SysStaffEntity;
import tw.com.example.rest.repository.AddressRepository;
import tw.com.example.rest.repository.LoginRepository;
import tw.com.example.rest.repository.SysStaffRepository;
import tw.com.example.rest.vo.Ma000ResponseData;
import tw.com.example.rest.vo.Ma004Request;
import tw.com.example.rest.vo.Ma004Response;
import tw.com.example.rest.vo.Ma004ResponseBody;
import tw.com.example.rest.vo.Ma004ResponseBodyAddress;
import tw.com.example.rest.vo.Ma004ResponseHeader;

@Service
public class Ma004ServiceImpl {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	SysStaffRepository sysstaffRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	// 
	public String hash(String s) {
		
		char[] rawList = s.toCharArray();
		for (int i = 0; i < rawList.length; i++) {
			if (rawList.length <= 3 || (i == (rawList.length / 2) || (i == (rawList.length / 2 + 1)) || (i == (rawList.length / 2 - 1)))){
				rawList[i] = '*';
			}
		}
		return new String(rawList);
	}
	
	public Ma004Response findall(Ma004Request request) {
			
			Ma004Response response = new Ma004Response();
			
			// 組成 Response 的 Header
			Ma004ResponseHeader header= new Ma004ResponseHeader();
			header.setSys(request.getHeader().getSys());
			header.setApi(request.getHeader().getApi());
			header.setTime(System.currentTimeMillis());
			response.setHeader(header);
			
			// 組成 Response 的 Body
			Ma004ResponseBody body= new Ma004ResponseBody();
			
			//組成 Response 的 Body 裡的資料明細
			String token = request.getBody().getToken();
			
			try {
				Optional<LoginEntity> LoginData = loginRepository.findByToken(token);
				
				// 判斷是否有登入過
				if(LoginData.isPresent()) {
					LoginEntity LoginData1 = LoginData.get();
					Date now = new Date();
					List<AddressEntity> addressData = addressRepository.findByPkIden(LoginData1.getIden());
					
					//  檢查 TOKEN 是否失效
					if (now.compareTo(LoginData1.getEffectiveTime()) <= 0) {
						Optional<SysStaffEntity> staffData = sysstaffRepository.findByIden(LoginData1.getIden());
						
						// 判斷是否存在員工資料
						if (staffData.isPresent()) {
							SysStaffEntity staffData1 = staffData.get();
							long diffInMillies = Math.abs(new Date().getTime() - staffData1.getBirth().getTime());
						    int diff = (int)TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
							body.setIden(hash(staffData1.getIden()));
							body.setSex(staffData1.getSex());
							body.setBirth(staffData1.getBirth());
							body.setCname(staffData1.getCname());
							body.setEname(staffData1.getEname());
							body.setAge(diff / 365);
						}
						Ma004ResponseBodyAddress addr = new Ma004ResponseBodyAddress();
						
						// 用 Pkey 區別 A, B Type
						for (int i = 0; i < addressData.size(); i++) {
							addressData.get(i).getPk();
							if ("A".equals(addressData.get(i).getPk().getType())) {
								addr.setDomicile(addressData.get(i).getZipCode() + " 台北市 " + addressData.get(i).getAddr());
							}else {
								addr.setContact(addressData.get(i).getZipCode() + " 台北市 " + addressData.get(i).getAddr());
							}
						}
						
						body.setAddress(addr);
						header.setCode("0000");
						header.setDesc("查詢成功");
						
					}else {
						header.setCode("0002");
						header.setDesc("登入時間逾期");
					}
				}else {
					header.setCode("0001");
					header.setDesc("查無登入資訊");
				}
				
				response.setBody(body);
			}catch(Exception e){
				header.setCode("9999");
				header.setDesc("系統發生異常，請聯絡系統管理員");
			}
			
			return response;
		}
}