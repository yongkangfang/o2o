package com.light.controller;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.light.model.User1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="FastJson测试", tags={"测试接口"})
@RestController
@RequestMapping("fastjson")
public class FastJsonController {
	
	protected Map<String, User1> userMap = new ConcurrentHashMap<>();
	
	@ApiOperation("通过用户名获取用户信息")
//	@ApiImplicitParam(name="name", value="用户名", dataType="string", paramType="query")
	@GetMapping("/select/{name}")
	public User1 test(@RequestParam String name){
		User1 user = userMap.get(name);
		return user;
	}
	
	@ApiOperation("获取所有用户信息")
	@GetMapping("/select/all")
	public Map<String, User1> test(){
		return userMap;
	}
	
	
	@ApiOperation("输入用户信息")
//	@ApiImplicitParam(name="user", value="用户具体信息", required= true, dataType="User")
	@PostMapping("/input/{}")
	public Map<String, User1> post(@RequestBody User1 user){
		
		userMap.put(user.getUsername(), user);
		return userMap;
	}
	
	@ApiOperation("根据id来删用户")
//	@ApiImplicitParam(name="name", value="用户名", dataType="User", paramType="query")
	@PostMapping("/delete/{name}")
	public Map<String, User1> post(@RequestParam String name){
		
		userMap.remove(name);
		
		return userMap;
	}
}
