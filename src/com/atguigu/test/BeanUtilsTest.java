package com.atguigu.test;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.atguigu.bean.User;

public class BeanUtilsTest {
	
	@Test
	public void test1(){
		//setProperty(bean, name, value);
		//bean代表要给哪个对象设置属性值
		//name代表要设置的属性名
		//value要设置的值
		User user = new User();
		System.out.println("未设置值之前："+user);
		try {
			BeanUtils.setProperty(user, "username", "小明");
			System.out.println("设置值之后："+user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void test2(){
		
		Student student = new Student();
		System.out.println("设置之前："+student);
		try {
			BeanUtils.setProperty(student, "age", "85");
			BeanUtils.setProperty(student, "Usern", "abc123");
			System.out.println("设置之后："+student);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//javaBean  属性   private String name;
		//getter setter方法才是javaBean的属性getAttr-attr;
		// getImgPath --> imgPath
		//  setImgPath
	}
	

}
