package tw.com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.example.rest.service.Ma007ServiceImpl;
import tw.com.example.rest.vo.Ma007Request;
import tw.com.example.rest.vo.Ma007Response;

@RestController
public class Ma007Controller {
	
	@Autowired
	Ma007ServiceImpl ma007ServiceImpl ;

	@RequestMapping("/user/leave/apply")
	public Ma007Response vacationApply(@RequestBody Ma007Request request) {
		
		System.out.println("ma007#start");
		return ma007ServiceImpl.vacationApply(request);
		
	}
}
