package tw.com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.example.rest.service.Ma005ServiceImpl;
import tw.com.example.rest.vo.Ma005Request;
import tw.com.example.rest.vo.Ma005Response;

@RestController
public class Ma005Controller {
	
	@Autowired
	Ma005ServiceImpl ma005ServiceImpl ;
	
	@RequestMapping("/user/leave")
	public Ma005Response leave(@RequestBody Ma005Request request) {
		
		return ma005ServiceImpl.leave(request);
		
	}
}
