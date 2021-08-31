package com.bitcamp.goodplace.domain;

import java.util.ArrayList;
import java.util.List;

public class Place {
	private int no;
	private String theme;
	private String storeName;
	private String address;
	private List<String> photo = new ArrayList<String>();
	private List<String> comment = new ArrayList<String>();
	public String jibunAddress;
	public String xCoord;
	public String yCoord;
	
	
	
	@Override
	public String toString() {
		return "Place [theme=" + theme + ", storeName=" + storeName + ", address=" + address + ", photo=" + photo
				+ ", comment=" + comment + ", jibunAddress=" + jibunAddress + ", xCoord=" + xCoord + ", yCoord="
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

	public String getJibunAddress() {
		return jibunAddress;
	}

	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
