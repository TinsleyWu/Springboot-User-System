package tw.com.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.example.rest.service.Ma004ServiceImpl;
import tw.com.example.rest.vo.Ma004Request;
import tw.com.example.rest.vo.Ma004Response;

@RestController
public class Ma004Controller {

	@Autowired
	Ma004ServiceImpl ma004ServiceImpl ;
	
	@RequestMapping("/user/all")
	public Ma004Response findall(@RequestBody Ma004Request request) {
		
		return ma004ServiceImpl.findall(request);
		
	}
}
