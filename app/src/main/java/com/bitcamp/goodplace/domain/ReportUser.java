package com.bitcamp.goodplace.domain;

public class ReportUser extends Report{
	User user;
	int reportedCount;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getReportedCount() {
		return reportedCount;
	}

	public void setReportedCount(int reportedCount) {
		this.reportedCount = reportedCount;
	}
	
}
