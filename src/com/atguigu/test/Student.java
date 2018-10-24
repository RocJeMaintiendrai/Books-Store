package com.atguigu.test;

public class Student {
	private Integer id;
	private String username2;
	private String password;
	private String email;
	private int age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsern() {
		return username2;
	}
	public void setUsern(String username) {
		this.username2 = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Student(Integer id, String username, String password, String email,
			int age) {
		super();
		this.id = id;
		this.username2 = username;
		this.password = password;
		this.email = email;
		this.age = age;
	}
	public Student() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username2 + ", password="
				+ password + ", email=" + email + ", age=" + age + "]";
	}
	
	
	
	

}
