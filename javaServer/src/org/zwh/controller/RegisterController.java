package org.zwh.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zwh.entity.User;
import org.zwh.service.UserService;
import org.zwh.util.Result;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Resource // 注入service
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST) // 注册
	public Result register(@RequestBody User user) throws ServletException, IOException {
		String username = user.getUsername();
		String password = user.getPassword();
		System.out.println(username+"&&"+password);
		Result result = null;
		JSONObject data = new JSONObject();
		User hasUser = userService.SelectUserByPrimaryKey(username);
		if(hasUser != null) {
			result = new Result(false,"用户名已存在!",data);
		}else {
			User newUser  = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			userService.InsertUser(newUser);
			result = new Result(true,"注册成功!",data);
		}
		return result;
	}
}
