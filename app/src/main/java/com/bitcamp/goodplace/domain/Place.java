package com.bitcamp.goodplace.domain;

import java.util.List;

public class Place {
	private String thema;
	private List<String> photo;
	private List<String> comment;
	public String jibunAddress;
	public String xCoord;
	public String yCoord;
	
	public String getThema() {
		return thema;
	}
	public void setThema(String thema) {
		this.thema = thema;
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

	
}
