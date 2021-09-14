package com.bitcamp.goodplace.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User>{
	private int no;
	private String name;
	private String id;
	private String password;
	private String email;
	private String tel;
	private String nickName;
	private Date registeredDate;
	private int viewCount;

	private List<Theme> themeList = new ArrayList<Theme>();
	private List<Theme> bookMarks = new ArrayList<Theme>();


	@Override
	public String toString() {
		return "User [no=" + no + ", name=" + name + ", id=" + id + ", password=" + password + ", email=" + email
				+ ", tel=" + tel + ", nickName=" + nickName + ", registeredDate=" + registeredDate + ", viewCount="
				+ viewCount + ", themeList=" + themeList + ", bookMarks=" + bookMarks + "]";
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no++;
	}

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

	public List<Theme> getThemeList() {
		return themeList;
	}

	public void setThemeList(List<Theme> themeList) {
		this.themeList = themeList;
	}

	public List<Theme> getBookMarks() {
		return bookMarks;
	}

	public void setBookMarks(List<Theme> bookMarks) {
		this.bookMarks = bookMarks;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	@Override
	public int compareTo(User user) {
		return user.viewCount - this.viewCount ;
	}

}
