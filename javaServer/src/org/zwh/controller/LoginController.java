package org.zwh.controller;

import java.io.IOException;
import java.util.Map;

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
@RequestMapping("/login")
public class LoginController {
	@Resource//注入service
	private UserService userService;
	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.POST)//登录
    public Result login(@RequestBody User user) throws ServletException, IOException {  
		String username = user.getUsername();
		String password = user.getPassword();
		Result result = null; 
		JSONObject data = new JSONObject(); 
		User hasUser = userService.SelectUserByPrimaryKey(username);
	    if(hasUser != null && hasUser.getPassword().equals(password)){
	    		result = new Result(true,"登录成功!",data);
	    }
	    else{
	    		result = new Result(false,"用户名或密码错误!",data); 
	    }
	    return result;
    }  
}