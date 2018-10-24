package com.atguigu.dao;

import com.atguigu.bean.User;


public interface UserDao{
	
	/**
	 * 按照用户名密码查询详细信息
	 * @param user
	 * @return
	 */
	public User getUserByUserNameAndPassWord(User user);
	
	/**
	 * 注册，保存用户
	 * @param user
	 * @return
	 */
	public boolean registUser(User user);
	
	public User getUserByUserName(User user);
	
}
