package org.zwh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zwh.dao.UserMapper;
import org.zwh.entity.User;
import org.zwh.service.UserService;

@Service("userService")//注入接口类，与实现类对接
public class UserServiceImpl implements UserService {

	@Resource//注入mapper
	private UserMapper userMapper;
	
	public User SelectUserByPrimaryKey(String username) {
		// TODO Auto-generated method stub
		//返回调用对象的userMapper的同名函数的结果
		return this.userMapper.selectByPrimaryKey(username);
	}
	
	public List<User> SelectAllUser() {
		// TODO Auto-generated method stub
		return this.userMapper.selectAllUser();
	}

	public void InsertUser(User user) {
		// TODO Auto-generated method stub
		this.userMapper.insert(user);
	}

}
