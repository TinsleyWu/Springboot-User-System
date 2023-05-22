package tw.com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.example.rest.service.Ma008ServiceImpl;
import tw.com.example.rest.vo.Ma008Request;
import tw.com.example.rest.vo.Ma008Response;

@RestController
public class Ma008Controller {
	
	@Autowired
	Ma008ServiceImpl ma008ServiceImpl;
	
	@PostMapping("/holiday")
	public Ma008Response getHoliday(@RequestBody Ma008Request request) {
		System.out.println("ma008");
		return ma008ServiceImpl.getHoliday(request);
		
	}

}
