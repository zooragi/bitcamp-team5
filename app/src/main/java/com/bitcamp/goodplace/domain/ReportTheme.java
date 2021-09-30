package com.bitcamp.goodplace.domain;

public class ReportTheme extends Report{
	Theme theme;
	int reportedCount;
	
	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public int getReportedCount() {
		return reportedCount;
	}

	public void setReportedCount(int reportedCount) {
		this.reportedCount = reportedCount;
	}
	
}
