package com.bitcamp.goodplace.domain;

public class ReportUser extends Report{
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
