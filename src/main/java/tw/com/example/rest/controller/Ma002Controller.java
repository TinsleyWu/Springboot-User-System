package tw.com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.example.rest.service.Ma002ServiceImpl;
import tw.com.example.rest.vo.Ma002Request;
import tw.com.example.rest.vo.Ma002Response;

@RestController
public class Ma002Controller {
	
	@Autowired
	Ma002ServiceImpl ma002ServiceImpl ;
	
	@RequestMapping("/user")
	public Ma002Response search(@RequestBody Ma002Request request) {
		
		return ma002ServiceImpl.search(request);
		
	}
	

}
