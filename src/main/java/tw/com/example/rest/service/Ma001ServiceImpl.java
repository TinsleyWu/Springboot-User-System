package tw.com.example.rest.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.example.rest.entity.LoginEntity;
import tw.com.example.rest.entity.SysStaffEntity;
import tw.com.example.rest.repository.LoginRepository;
import tw.com.example.rest.repository.SysStaffRepository;
import tw.com.example.rest.vo.Ma001Request;
import tw.com.example.rest.vo.Ma001Response;
import tw.com.example.rest.vo.Ma001ResponseBody;
import tw.com.example.rest.vo.Ma001ResponseHeader;

@Service
public class Ma001ServiceImpl {

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	SysStaffRepository sysstaffRepository;

	public Ma001Response login(Ma001Request request) {

		Ma001Response response = new Ma001Response();

		// 組成 Response 的 Header
		Ma001ResponseHeader header = new Ma001ResponseHeader();
		header.setSys(request.getHeader().getSys());
		header.setApi(request.getHeader().getApi());
		header.setTime(System.currentTimeMillis());
		response.setHeader(header);

		// 組成 Response 的 Body
		Ma001ResponseBody body = new Ma001ResponseBody();

		// 組成 Response 的 Body 裡的資料明細
		String iden = request.getBody().getIden();
		String pwd = request.getBody().getPwd();

		try {
			Optional<SysStaffEntity> staffData = sysstaffRepository.findByIden(iden);
			
			if (staffData.isPresent()) {
				SysStaffEntity staffData1 = staffData.get();
				if (pwd.equals(staffData1.getPwd())) {
					// 產生 UUID
					UUID uuid = UUID.randomUUID();
					String uuidstr = uuid.toString();

					header.setCode("0000");
					header.setDesc("登入成功");
					body.setToken(uuidstr);

					Optional<LoginEntity> LoginData = loginRepository.findByIden(iden);
					
					Date cretime = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(cretime);
					cal.add(Calendar.MINUTE, 30);
					Date efftime = cal.getTime();

					LoginEntity loginUpdate1 = LoginData.get();
					loginUpdate1.setToken(uuidstr);
					loginUpdate1.setCreTime(cretime);
					loginUpdate1.setEffectiveTime(efftime);
					loginRepository.save(loginUpdate1);
				} else {
					header.setCode("0002");
					header.setDesc("密碼錯誤");
				}
			} else {
				header.setCode("0001");
				header.setDesc("查無使用者");
			}
			response.setBody(body);

		} catch (Exception e) {
			header.setCode("9999");
			header.setDesc("系統發生異常，請聯絡系統管理員");
			System.out.print(e);
		}

		return response;

	}
}
