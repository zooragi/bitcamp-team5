package com.bitcamp.goodplace.domain;

import java.util.ArrayList;
import java.util.List;

public class Place {
	private int no;
	private String theme;
	private String storeName;
	private String addressName;
	private List<String> photo = new ArrayList<String>();
	private List<String> comment = new ArrayList<String>();
	public String xCoord;
	public String yCoord;
	
	@Override
	public String toString() {
		return "Place [theme=" + theme + ", storeName=" + storeName + ", addressName=" + addressName + ", photo=" + photo
				+ ", comment=" + comment + ", xCoord=" + xCoord + ", yCoord="
				+ yCoord + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<String> getPhoto() {
		return photo;
	}

	public void setPhoto(List<String> photo) {
		this.photo = photo;
	}

	public List<String> getComment() {
		return comment;
	}

	public void setComment(List<String> comment) {
		this.comment = comment;
	}

	public String getxCoord() {
		return xCoord;
	}

	public void setxCoord(String xCoord) {
		this.xCoord = xCoord;
	}

	public String getyCoord() {
		return yCoord;
	}

	public void setyCoord(String yCoord) {
		this.yCoord = yCoord;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

}
