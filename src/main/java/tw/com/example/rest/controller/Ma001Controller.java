package tw.com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.example.rest.service.Ma001ServiceImpl;
import tw.com.example.rest.vo.Ma001Request;
import tw.com.example.rest.vo.Ma001Response;

@RestController
public class Ma001Controller {
	
	@Autowired
	Ma001ServiceImpl ma001ServiceImpl ;
	
	@PostMapping("/login")
	public Ma001Response login(@RequestBody Ma001Request request) {
		System.out.println("ma001#start");
		System.out.println("");
		return ma001ServiceImpl.login(request);
		
	}

}
