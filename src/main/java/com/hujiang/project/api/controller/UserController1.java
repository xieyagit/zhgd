package com.hujiang.project.api.controller;

import com.hujiang.project.api.model.ZhUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/user")
public class UserController1 {
	


	@RequestMapping("/get/{id}")
	public ZhUser get(@PathVariable("id") Integer id) {
		ZhUser user= new ZhUser();
		user.setId(id);
		user.setPort(8009);
		user.setPassword("123");
		System.out.println(user);
		return user;
	}
}
