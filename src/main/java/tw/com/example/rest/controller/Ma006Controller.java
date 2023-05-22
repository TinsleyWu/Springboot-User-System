package tw.com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.example.rest.service.Ma005ServiceImpl;
import tw.com.example.rest.service.Ma006ServiceImpl;
import tw.com.example.rest.vo.Ma005Request;
import tw.com.example.rest.vo.Ma005Response;
import tw.com.example.rest.vo.Ma006Request;
import tw.com.example.rest.vo.Ma006Response;

@RestController
public class Ma006Controller {
	
	@Autowired
	Ma006ServiceImpl ma006ServiceImpl ;
	
	@RequestMapping("/user/leave/dtl")
	public Ma006Response vacationSearch(@RequestBody Ma006Request request) {
		
		System.out.println("ma006#start");
		return ma006ServiceImpl.vacationSearch(request);
		
	}
		
}
