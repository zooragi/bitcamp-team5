package com.welcomeToJeju.moj.domain;

public class ReportUser extends Report{
  String reportedUserName;
  int reportedCount;

  @Override
	public String toString() {
		return "ReportUser [reportedUserName=" + reportedUserName + ", reportedCount=" + reportedCount + "]";
	}

	public String getReportedUserName() {
		return reportedUserName;
	}

	public void setReportedUserName(String reportedUserName) {
		this.reportedUserName = reportedUserName;
	}

	public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }

}
