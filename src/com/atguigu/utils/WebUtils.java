package com.atguigu.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;

/**
 * Web的相关工具
 * @author lfy
 *
 */
public class WebUtils {

		
		/**
		 * 传入request对象将request中的数据封装成对象
		 * @param request
		 * @param t
		 * @return
		 */
		public static<T> T param2bean(HttpServletRequest request,T t){
			//封装对象，并返回
			//1、获取所有声明的属性
			Field[] fields = t.getClass().getDeclaredFields();
			
			//2、每个属性都有name值，属性名
			for (Field field : fields) {
				//获取属性名
				String name = field.getName();
				//在request中获取相应的属性值
				String value = request.getParameter(name);
				try {
					BeanUtils.setProperty(t, name, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//封装对象setAttrName()
			}
			return t;
		}
		
		public static<T> T param2bean2(HttpServletRequest request,T t){
			//populate将map中的键值对，直接映射到javaBean中
			Map map = request.getParameterMap();
			try {
				BeanUtils.populate(t, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return t;
		}

		public static Cart getCart(HttpServletRequest request) {
			// TODO Auto-generated method stub
			// Cart cart = new Cart();
			// 购物车的整个内容 Cart 在session中保存。
			// 获取购物车
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				// 给session中放入购物车
				cart = new Cart();
				session.setAttribute("cart", cart);
			}
			return cart;
		}

		public static User getLoginUser(HttpServletRequest request) {
			// TODO Auto-generated method stub
			//1、验证用户是否登陆
			HttpSession session = request.getSession();
			//取出session中的用户
			return  (User) session.getAttribute("user");
		}
	
}
