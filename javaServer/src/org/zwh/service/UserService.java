package org.zwh.service;

import java.util.List;
import org.zwh.entity.User;
//接口存在的意义:
//1.多个 实现类 拥有同样的方法的时候  用接口会减少代码量
//2.以后需要增加一个方法 的时候,直接在接口里面写,继承该接口的实现类会告诉你要新增方法.
//3.接口相当于一个规范,
//(a)个人开发时,你自己写接口，自己写实实现类，那么就没必要写接口了.
//(b)团队开发，我写了接口,我突然有事离开，你来写实现类，你看了接口就知道要写哪些了。这个时候就很有必要了。

public interface UserService {
	public User SelectUserByPrimaryKey(String username);
	public List<User> SelectAllUser();
	public void InsertUser(User user);
}
