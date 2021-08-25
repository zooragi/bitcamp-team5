package com.bitcamp.goodplace.domain;

import java.util.List;

public class User {
	private String name;
	private String id;
	private String password;
	private String email;
	private String tel;
	private String nickName;
	private List<Thema> themaList; 
	private List<String> bookMark;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public List<Thema> getThemaList() {
		return themaList;
	}
	public void setThemaList(List<Thema> themaList) {
		this.themaList = themaList;
	}
	public List<String> getBookMark() {
		return bookMark;
	}
	public void setBookMark(List<String> bookMark) {
		this.bookMark = bookMark;
	}
	
	
}
