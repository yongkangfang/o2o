package com.light.configuration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
	@RequestMapping("/hello")
	public String PrintHello(){
		return "Hello Word";
	}
	
	@RequestMapping("/hai")
	public String PrintHai(){
		return "Hello Hai";
	}
}
