package com.atguigu.test;

import org.junit.Test;

import com.atguigu.bean.User;
import com.atguigu.dao.UserDao;
import com.atguigu.dao.impl.UserDaoImpl;

public class UserDaoTest {
	
	@Test
	public void test() {
		UserDao ud = new UserDaoImpl();
		User user = ud.getUserByUserNameAndPassWord(new User(null, "tomcat3",
				"123456", null));
		System.out.println(user);
	}
	
	@Test
	public void test2() {
		UserDao ud = new UserDaoImpl();
		boolean b = ud.registUser(new User(null, "tomcat2",
				"123456", null));
		System.out.println(b);
	}
}
