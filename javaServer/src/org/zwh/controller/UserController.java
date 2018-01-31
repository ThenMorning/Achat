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

@Controller//ע��һ��bean��spring��������
@RequestMapping("/user")//������������������أ�requestMappingע��Ϊ������ָ�����Դ�����ЩURL����
public class UserController {
	@Resource//ע��service
	private UserService userService;
	@RequestMapping("/getUserByUserName")//�������������أ�ֻҪ@RequestMapping�ĵ�ַ���ˣ��ͻ��������ķ����У��뷽�����޹أ��������������⣬������ú�����ͬ��
    public void getUserByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩  
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
	//RequestMethod.GetҪ��ǰ̨����ʽһ��
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)//����д��spring���Զ������صĶ������л���json
	@ResponseBody
	//��ע�����ڽ�Controller�ķ������صĶ���ͨ���ʵ���HttpMessageConverterת��Ϊָ����ʽ��д�뵽Response�����body������
	 public List<User> getAllUser() {
		List<User> userList = userService.SelectAllUser();
		return userList;
    }
	
	//������չ����jsp
	@RequestMapping(value = "/hi")
	public String hi(HttpServletRequest request,HttpServletResponse response){
		//����Ҫ���ص�����д��
		request.setAttribute("str", "Hi!");
		//return "hi"����ȥmvc���õ���ͼ��������Ŀ¼����hi.jsp
		return "hi";
	}
}
