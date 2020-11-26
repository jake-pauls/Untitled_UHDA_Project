package com.csis3275.model;

import java.util.Date;

public class MiniBarItem {

	
	private Date bestBeforeDate;
	private String name;
	private float price;
	
	
	//Constructor
	public MiniBarItem(String newName, float newPrice, Date newBestBeforeDate) {
		this.bestBeforeDate = newBestBeforeDate;
		this.name = newName;
		this.price = newPrice;
	}
	
	
	public MiniBarItem() {
		// TODO Auto-generated constructor stub
	}


	public Date getBestBeforeDate() {
		return bestBeforeDate;
	}
	public void setBestBeforeDate(Date bestBeforeDate) {
		this.bestBeforeDate = bestBeforeDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
