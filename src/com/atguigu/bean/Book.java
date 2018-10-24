package com.atguigu.bean;

import java.io.Serializable;

public class Book implements Serializable {
	/**
	 * 图书的主键
	 */
	private Integer id;
	/**
	 * 图书的书名
	 */
	private String title;

	/**
	 * 图书的作者
	 */
	private String author;
	/**
	 * 图书的价格
	 */
	private double price;
	/**
	 * 图书的销量
	 */
	private int sales;

	/**
	 * 图书的库存
	 */
	private int stock;

	/**
	 * 图片的封面路径，可以指定默认路径, 写相对于项目的相对路径
	 */
	private String imgPath = "static/img/default.jpg";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {

		this.imgPath = imgPath;
	}

	public Book(Integer id, String title, String author, double price,
			int sales, int stock, String imgPath) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.price = price;
		this.sales = sales;
		this.stock = stock;
		if (imgPath != null)
			this.imgPath = imgPath;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ ", price=" + price + ", sales=" + sales + ", stock=" + stock
				+ ", imgPath=" + imgPath + "]";
	}

}
