package com.welcomeToJeju.moj.domain;

public class ReportTheme extends Report{
  String reportedThemeTitle;
  int reportedCount;
  
  @Override
	public String toString() {
		return "ReportTheme [reportedThemeTitle=" + reportedThemeTitle + ", reportedCount=" + reportedCount + "]";
	}

	public String getReportedThemeTitle() {
		return reportedThemeTitle;
	}

	public void setReportedThemeTitle(String reportedThemeTitle) {
		this.reportedThemeTitle = reportedThemeTitle;
	}

	public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }

}
