package tw.com.example.rest.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.example.rest.entity.LoginEntity;
import tw.com.example.rest.entity.SysStaffEntity;
import tw.com.example.rest.repository.LoginRepository;
import tw.com.example.rest.repository.SysStaffRepository;
import tw.com.example.rest.vo.Ma003Request;
import tw.com.example.rest.vo.Ma003Response;
import tw.com.example.rest.vo.Ma003ResponseHeader;

@Service
public class Ma003ServiceImpl {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	SysStaffRepository sysstaffRepository;
	
	public Ma003Response change(Ma003Request request) {
			
			// 組成 Response
			Ma003Response response = new Ma003Response();
			
			// 組成 Response 的 Header
			Ma003ResponseHeader header= new Ma003ResponseHeader();
			header.setSys(request.getHeader().getSys());
			header.setApi(request.getHeader().getApi());
			header.setTime(System.currentTimeMillis());
			response.setHeader(header);
			
			// 取出上行電文的登入資訊
			String token = request.getBody().getToken();
			
			try {
				Optional<LoginEntity> LoginData = loginRepository.findByToken(token);
				
				// 檢查Token是否存在
				if(LoginData.isPresent()) {
					LoginEntity LoginData1 = LoginData.get();
					Date now = new Date();
					
					// 檢查Token是否逾期
					if (now.compareTo(LoginData1.getEffectiveTime()) <= 0) {
						try {
							Optional<SysStaffEntity> staffData = sysstaffRepository.findByIden(LoginData1.getIden());
							
							// 檢查員工資料是否存在
							if (staffData.isPresent()) {
								SysStaffEntity staffUpdate1 = staffData.get();
								staffUpdate1.setPwd(request.getBody().getPwd());
								sysstaffRepository.save(staffUpdate1);
								
								header.setCode("0000");
								header.setDesc("變更密碼成功");
							}
						}catch (Exception e) {
							header.setCode("0003");
							header.setDesc("變更密碼失敗");
						}
						
					}else {
						header.setCode("0002");
						header.setDesc("登入時間逾期");
					}
				}else {
					header.setCode("0001");
					header.setDesc("查無登入資訊");
				}
				
			}catch(Exception e){
				header.setCode("9999");
				header.setDesc("系統發生異常，請聯絡系統管理員");
			}
			
			return response;
		}
}