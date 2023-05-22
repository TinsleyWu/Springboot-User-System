package tw.com.example.rest.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import tw.com.example.rest.entity.HrSysCalEntity;
import tw.com.example.rest.repository.HrSysCalRepository;
import tw.com.example.rest.vo.Ma008Request;
import tw.com.example.rest.vo.Ma008Response;
import tw.com.example.rest.vo.Ma008ResponseHeader;

@Service
public class Ma008ServiceImpl {
	
	@Autowired
	HrSysCalRepository hrSysCalRepository;

	public Ma008Response getHoliday(Ma008Request request) {
		
		// 產生 Response
		Ma008Response response = new Ma008Response();
		
		// 產生 Response 的 Header
		Ma008ResponseHeader header= new Ma008ResponseHeader();
		header.setSys(request.getHeader().getSys());
		header.setApi(request.getHeader().getApi());
		header.setTime(System.currentTimeMillis());
		
		//  判斷 Request Body 的 Year 和 Sys 是否為空值
		if (request.getBody().getYear() != null && request.getBody().getSys() != null) {
			try {
				// 開啟 JSON 檔
				File file = new File("/Users/karenwu/git/springboot-hr-sys/src/main/resources/2022vacation.json");
				
				// 將 JSON 檔放入 Mapper 物件
				ObjectMapper mapper = new ObjectMapper();
			    JsonNode root = mapper.readTree(file);
			    HrSysCalEntity calInsert = new HrSysCalEntity();
			    
			    // 取出 JSON 資料放入資料庫
			    for(int i = 0 ; i < root.size() ; i ++ ) {
			        String year = root.get(i).get("西元日期").toString().substring(1, 5);
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			        Date day = sdf.parse(root.get(i).get("西元日期").toString().substring(1, 9));
			        calInsert.setId(i);
			        calInsert.setSys(request.getBody().getSys());
			        calInsert.setCalYear(Integer.valueOf(year));
			        calInsert.setCalDate(day);
			        calInsert.setCalDay(root.get(i).get("星期").toString().substring(1, 2));
			        calInsert.setIsHoliday(root.get(i).get("是否放假").toString().substring(1, 2));
			        calInsert.setRemark(root.get(i).get("備註").toString().substring(1, root.get(i).get("備註").toString().length() - 1));
			        
			        hrSysCalRepository.save(calInsert);
			    }
			    header.setCode("0000");
				header.setDesc("執行成功");
			}catch(Exception e) {
				header.setCode("9999");
				header.setDesc("系統發生異常，請聯絡系統管理員");
			}
		}else {
			header.setCode("0001");
			header.setDesc("執行失敗");
		}
		response.setHeader(header);
		
		return response;
		
	}
}
