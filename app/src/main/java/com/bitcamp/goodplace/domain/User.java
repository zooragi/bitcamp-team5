package com.bitcamp.goodplace.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User> {
	private int no;
	private String email;
	private String password;
	private String nickName;
	private Date registeredDate;
	private int viewCount;
	private int reportedCount = 0;
	private int warningCount = 0;

	private List<User> followings = new ArrayList<>();
	private List<Theme> themeList = new ArrayList<Theme>();
	private List<Theme> bookMarks = new ArrayList<Theme>();
	
	@Override
	public String toString() {
		return "User [no=" + no + ", email=" + email + ", password=" + password + ", nickName=" + nickName
				+ ", registeredDate=" + registeredDate + ", viewCount=" + viewCount + ", reportedCount=" + reportedCount
				+ ", followings=" + followings + ", themeList=" + themeList + ", bookMarks=" + bookMarks + "]";
	}

	public int getWarningCount() {
		return warningCount;
	}

	public void setWarningCount(int warningCount) {
		this.warningCount = warningCount;
	}

	public int getReportedCount() {
		return reportedCount;
	}

	public void setReportedCount(int reportedCount) {
		this.reportedCount = reportedCount;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
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

	@Override
	public int compareTo(User user) {
		return user.viewCount - this.viewCount;
	}

	public List<User> getFollowings() {
		return followings;
	}

	public void setFollowings(List<User> followers) {
		this.followings = followers;
	}

}
