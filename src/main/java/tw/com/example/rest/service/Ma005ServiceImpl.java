package tw.com.example.rest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.example.rest.entity.AddressEntity;
import tw.com.example.rest.entity.HrSysCodeEntity;
import tw.com.example.rest.entity.LeaveEntity;
import tw.com.example.rest.entity.LoginEntity;
import tw.com.example.rest.entity.SysStaffEntity;
import tw.com.example.rest.repository.HrSysCodeRepository;
import tw.com.example.rest.repository.LeaveRepository;
import tw.com.example.rest.repository.LoginRepository;
import tw.com.example.rest.repository.SysStaffRepository;
import tw.com.example.rest.vo.Ma005ResponseBody;
import tw.com.example.rest.vo.Ma000ResponseData;
import tw.com.example.rest.vo.Ma004ResponseBodyAddress;
import tw.com.example.rest.vo.Ma005Request;
import tw.com.example.rest.vo.Ma005Response;
import tw.com.example.rest.vo.Ma005ResponseHeader;
import tw.com.example.rest.vo.Ma005ResponseLeaves;

@Service
public class Ma005ServiceImpl {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	SysStaffRepository sysstaffRepository;
	
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	HrSysCodeRepository hrSysCodeRepository;
	
	public String hash(String raw) {
		
		char[] rawList = raw.toCharArray();
		for (int i = 0; i < rawList.length; i++) {
			if (rawList.length <= 3 || (i == (rawList.length / 2) || (i == (rawList.length / 2 + 1)) || (i == (rawList.length / 2 - 1)))){
				rawList[i] = '*';
			}
		}
		return new String(rawList);
	}
	
	public Ma005Response leave(Ma005Request request) {
		
		Ma005Response response = new Ma005Response();
		
		// 組成 Response 的 Header
		Ma005ResponseHeader header= new Ma005ResponseHeader();
		header.setSys(request.getHeader().getSys());
		header.setApi(request.getHeader().getApi());
		header.setTime(System.currentTimeMillis());
		response.setHeader(header);
		
		// 組成 Response 的 Body
		Ma005ResponseBody body= new Ma005ResponseBody();
		
		//組成 Response 的 Body 裡的資料明細
		String token = request.getBody().getToken();
		Integer year = request.getBody().getYear();
		
		// 取得假別名稱資料
		Map<String, String> mapList = new HashMap<String, String>();
		List<HrSysCodeEntity> LeaveList = hrSysCodeRepository.findByPkGp("LEAVE_TYPE");
		
		for(int i = 0 ; i < LeaveList.size() ; i ++ ) {
			mapList.put(LeaveList.get(i).getPk().getCode(), LeaveList.get(i).getCname());
		}	
		
		try {
			Optional<LoginEntity> LoginData = loginRepository.findByToken(token);
			
			if(LoginData.isPresent()) {
				LoginEntity LoginData1 = LoginData.get();
				Date now = new Date();
				if (now.compareTo(LoginData1.getEffectiveTime()) <= 0) {
					Optional<SysStaffEntity> staffData = sysstaffRepository.findByIden(LoginData1.getIden());
					if (staffData.isPresent()) {
						SysStaffEntity staffData1 = staffData.get();
						body.setIden(hash(staffData1.getIden()));
						body.setCname(staffData1.getCname());
						body.setYear(year);
						
						List<Ma005ResponseLeaves> leaveList = new ArrayList<>();
						List<LeaveEntity> entityList = leaveRepository.findByPkIden(staffData1.getIden());
						
						//組成 Response 的 Body 裡的資料明細
						if (entityList.size() == 0) {
							header.setCode("0003");
							header.setDesc("查無假別資料");
						}else {
							for(int i = 0 ; i < entityList.size(); i++) {
								if (year == entityList.get(i).getPk().getYear().intValue()) {
									System.out.println(entityList.get(i));
									Ma005ResponseLeaves leave = new Ma005ResponseLeaves();
									leave.setType(entityList.get(i).getPk().getLeaveType());
									leave.setApplyHours(entityList.get(i).getLeaveApplyHours());
									leave.setOwnHours(entityList.get(i).getLeaveOwnHours());
									leave.setTotalHours(entityList.get(i).getLeaveOwnHours() - entityList.get(i).getLeaveApplyHours());
									leave.setTypeDesc(mapList.get(entityList.get(i).getPk().getLeaveType()));
									leaveList.add(leave);
								}
							}
							
							body.setLeaves(leaveList);
							response.setBody(body);
							header.setCode("0000");
							header.setDesc("查詢成功");
						}
						
					}
					
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
