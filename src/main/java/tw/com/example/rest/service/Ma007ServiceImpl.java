package tw.com.example.rest.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.example.rest.entity.HrSysCodeEntity;
import tw.com.example.rest.entity.HrSysLeaveDtlPkey;
import tw.com.example.rest.entity.LeaveDtlEntity;
import tw.com.example.rest.entity.LeaveEntity;
import tw.com.example.rest.entity.LoginEntity;
import tw.com.example.rest.entity.SysStaffEntity;
import tw.com.example.rest.repository.HrSysCodeRepository;
import tw.com.example.rest.repository.LeaveDtlRepository;
import tw.com.example.rest.repository.LeaveRepository;
import tw.com.example.rest.repository.LoginRepository;
import tw.com.example.rest.repository.SysStaffRepository;
import tw.com.example.rest.vo.Ma007Request;
import tw.com.example.rest.vo.Ma007Response;
import tw.com.example.rest.vo.Ma007ResponseHeader;

@Service
public class Ma007ServiceImpl {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	SysStaffRepository sysstaffRepository;
	
	@Autowired
	LeaveRepository leaveRepository;
	
	@Autowired
	LeaveDtlRepository leaveDtlRepository;
	
	@Autowired
	HrSysCodeRepository hrSysCodeRepository;
	
	@Autowired
	EmailServiceImpl mailService;
	
	public Ma007Response vacationApply(Ma007Request request) {
		
		//  Response
		Ma007Response response = new Ma007Response();
		
		// Response 的 Header
		Ma007ResponseHeader header= new Ma007ResponseHeader();
		header.setSys(request.getHeader().getSys());
		header.setApi(request.getHeader().getApi());
		header.setTime(System.currentTimeMillis());
		response.setHeader(header);
		
		//Response 的 Body 裡的資料明細
		String token = request.getBody().getToken();
		String type = request.getBody().getType();
		String userId = request.getBody().getUserId();
		Integer hours = request.getBody().getHours();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localBeginTime = LocalDateTime.parse(request.getBody().getBeginTime(), formatter);
		ZonedDateTime beginzdt = localBeginTime.atZone(zoneId);
		Date BeginTime = Date.from(beginzdt.toInstant());
		LocalDateTime localEndTime = LocalDateTime.parse(request.getBody().getEndTime(), formatter);
		ZonedDateTime endzdt = localEndTime.atZone(zoneId);
		Date EndTime = Date.from(endzdt.toInstant());
		
		// 取得假別名稱資料
		Map<String, String> mapList = new HashMap<String, String>();
		List<HrSysCodeEntity> LeaveList = hrSysCodeRepository.findByPkGp("LEAVE_TYPE");
		for(int i = 0 ; i < LeaveList.size() ; i ++ ) {
			mapList.put(LeaveList.get(i).getPk().getCode(), LeaveList.get(i).getCname());
		}
		
		try {
			// 取得 HR_SYS_LOGIN 資料
			Optional<LoginEntity> LoginData = loginRepository.findByToken(token);
			
			// 檢查 Token 是否登入過
			if(LoginData.isPresent()) {
				LoginEntity LoginData1 = LoginData.get();
				Date now = new Date();
				
				// 檢查 Token 是否失效
				if (now.compareTo(LoginData1.getEffectiveTime()) <= 0) {
					// 取得 HR_SYS_STAFF 員工資料
					Optional<SysStaffEntity> staffData = sysstaffRepository.findByIden(LoginData1.getIden());
					// 檢查員工資料是否存在
					if (staffData.isPresent()) {
						SysStaffEntity staffData1 = staffData.get();
						// 取得 HR_SYS_LEAVE 假別資料
						List<LeaveEntity> entityList = leaveRepository.findByPkIden(staffData1.getIden());
						
						// 
						for(int i = 0 ; i < entityList.size(); i++) {
							if (localBeginTime.getYear() == entityList.get(i).getPk().getYear().intValue() && 
								hours <= entityList.get(i).getLeaveOwnHours() - entityList.get(i).getLeaveApplyHours() &&
								type.equals(entityList.get(i).getPk().getLeaveType())) {
								
								// 異動 HR_SYS_LEAVE 主檔
								LeaveEntity leaveUpdate = entityList.get(i);
								leaveUpdate.setLeaveApplyHours(leaveUpdate.getLeaveApplyHours() - hours);
								leaveRepository.save(leaveUpdate);
								
								// 寫入 HR_SYS_LEAVE_DTL 資料
								LeaveDtlEntity leaveDtlInsert = new LeaveDtlEntity();
								leaveDtlInsert.setPk(new HrSysLeaveDtlPkey());
								leaveDtlInsert.setLeaveOwnHours(leaveUpdate.getLeaveOwnHours());
								leaveDtlInsert.setCreTime(new Date());
								leaveDtlInsert.setLeaveApplyHours(hours);
								leaveDtlInsert.setLeaveBeginTime(BeginTime);
								leaveDtlInsert.setLeaveEndTime(EndTime);
								leaveDtlInsert.setRemark(mapList.get(type));
								leaveDtlInsert.getPk().setYear(entityList.get(i).getPk().getYear().intValue());
								leaveDtlInsert.getPk().setIden(userId);
								leaveDtlInsert.getPk().setLeaveType(type);
								leaveDtlInsert.getPk().setId(10); // 問題
								leaveDtlInsert.setCreUser(staffData1.getCname());
								leaveDtlRepository.save(leaveDtlInsert);
								
								System.out.println(entityList.get(i));
								header.setCode("0000");
								header.setDesc("請假成功");
								
								mailService.prepareAndSend(staffData1.getEmail(),"請假成功信");
								
								break;
							}else {
								header.setCode("1001");
								header.setDesc("請假失敗，假別（特休）時數不足");
							}
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
			
		}catch(Exception e){
			header.setCode("9999");
			header.setDesc("系統發生異常，請聯絡系統管理員");
		}
		
		return response;
	
	}
}
