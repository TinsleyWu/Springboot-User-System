package tw.com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.example.rest.service.Ma002ServiceImpl;
import tw.com.example.rest.service.Ma003ServiceImpl;
import tw.com.example.rest.vo.Ma002Request;
import tw.com.example.rest.vo.Ma002Response;
import tw.com.example.rest.vo.Ma003Request;
import tw.com.example.rest.vo.Ma003Response;

@RestController
public class Ma003Controller {

	@Autowired
	Ma003ServiceImpl ma003ServiceImpl ;
	
	@RequestMapping("/changepwd")
	public Ma003Response change(@RequestBody Ma003Request request) {
		System.out.println("m003#start"); // 除錯
		return ma003ServiceImpl.change(request);
		
	}
}
