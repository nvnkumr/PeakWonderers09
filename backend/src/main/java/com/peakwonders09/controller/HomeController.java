package com.peakwonders09.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //this class handleshttp req and return data directly
public class HomeController{
	
	@GetMapping("/") //meanswhen someone visit root/homepage call home() method
	public String home(){
		return "Welcome home"; //this becaomes http response
	}
}
