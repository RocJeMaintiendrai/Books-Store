package com.atguigu.service;

import com.atguigu.bean.User;

/**
 * 完成用户的登陆注册
 * @author lfy
 *
 */
public interface UserService {
	public User login(User user);
	public boolean regist(User user);
	public boolean checkuser(User user);
	
}
