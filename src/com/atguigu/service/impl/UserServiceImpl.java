package com.atguigu.service.impl;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao ud = new UserDaoImpl();
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return ud.getUserByUserNameAndPassWord(user);
	}

	@Override
	public boolean regist(User user) {
		return ud.registUser(user);
	}
	
	/**
	 * true  可以注册
	 * false 用户已存在
	 */
	@Override
	public boolean checkuser(User user) {
		// TODO Auto-generated method stub
		User byUserName = ud.getUserByUserName(user);
		return byUserName==null;
	}

}
