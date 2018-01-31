package org.zwh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zwh.entity.User;
import org.zwh.service.UserService;

@Controller//注入一个bean到spring上下文中
@RequestMapping("/user")//这里是最外层请求拦截，requestMapping注解为控制器指定可以处理哪些URL请求
public class UserController {
	@Resource//注入service
	private UserService userService;
	@RequestMapping("/getUserByUserName")//这里是请求拦截，只要@RequestMapping的地址对了，就会进入下面的方法中，与方法名无关，方法名可以任意，但是最好和请求同名
    public void getUserByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
       request.setCharacterEncoding("UTF-8");  
       response.setCharacterEncoding("UTF-8"); 
       System.out.println(request.getParameter("username"));
       System.out.println(request.getParameter("password"));
       String username = request.getParameter("username");
       String password = request.getParameter("password");
       User user = userService.SelectUserByPrimaryKey(username);
       System.out.println(user.getPassword());
       PrintWriter out = response.getWriter();  
       if(user.getPassword().equals(password)){
    	   out.print(true);
       }else{
    	   out.print(false);
       }
        out.close();
        out = null;
    }  
	//RequestMethod.Get要和前台请求方式一致
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)//这种写法spring会自动将返回的对象序列化成json
	@ResponseBody
	//该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区
	 public List<User> getAllUser() {
		List<User> userList = userService.SelectAllUser();
		return userList;
    }
	
	//将数据展现在jsp
	@RequestMapping(value = "/hi")
	public String hi(HttpServletRequest request,HttpServletResponse response){
		//将想要返回的数据写入
		request.setAttribute("str", "Hi!");
		//return "hi"，回去mvc配置的视图解析器的目录下找hi.jsp
		return "hi";
	}
}
