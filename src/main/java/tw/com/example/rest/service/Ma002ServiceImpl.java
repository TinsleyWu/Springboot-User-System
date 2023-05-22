package tw.com.example.rest.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.example.rest.entity.LoginEntity;
import tw.com.example.rest.entity.SysStaffEntity;
import tw.com.example.rest.repository.LoginRepository;
import tw.com.example.rest.repository.SysStaffRepository;
import tw.com.example.rest.vo.Ma002Request;
import tw.com.example.rest.vo.Ma002Response;
import tw.com.example.rest.vo.Ma002ResponseBody;
import tw.com.example.rest.vo.Ma002ResponseHeader;

@Service
public class Ma002ServiceImpl {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	SysStaffRepository sysstaffRepository;
	
	public String hash(String raw) {
		
		char[] rawList = raw.toCharArray();
		for (int i = 0; i < rawList.length; i++) {
			System.out.println(rawList[i]);
			if (rawList.length <= 3 || (i == (rawList.length / 2) || (i == (rawList.length / 2 + 1)) || (i == (rawList.length / 2 - 1)))){
				rawList[i] = '*';
			}
		}
		return new String(rawList);
	}
	
	public Ma002Response search(Ma002Request request) {
			
			Ma002Response response = new Ma002Response();
			
			// 組成 Response 的 Header
			Ma002ResponseHeader header= new Ma002ResponseHeader();
			header.setSys(request.getHeader().getSys());
			header.setApi(request.getHeader().getApi());
			header.setTime(System.currentTimeMillis());
			response.setHeader(header);
			
			// 組成 Response 的 Body
			Ma002ResponseBody body= new Ma002ResponseBody();
			
			//組成 Response 的 Body 裡的資料明細
			String token = request.getBody().getToken();
			
			try {
				Optional<LoginEntity> LoginData = loginRepository.findByToken(token);
				
				if(LoginData.isPresent()) {
					LoginEntity LoginData1 = LoginData.get();
					Date now = new Date();
					if (now.compareTo(LoginData1.getEffectiveTime()) <= 0) {
						
						Optional<SysStaffEntity> staffData = sysstaffRepository.findByIden(LoginData1.getIden());
						SysStaffEntity staffData1 = staffData.get();
						body.setIden(hash(staffData1.getIden()));
						body.setSex(staffData1.getSex());
						body.setBirth(staffData1.getBirth());
						body.setCname(staffData1.getCname());
						body.setEname(staffData1.getEname());
						
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