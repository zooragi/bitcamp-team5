package com.welcomeToJeju.moj.domain;

public class ReportUser extends Report {

  User reportedUser;
  int reportedCount;

  @Override
  public String toString() {
    return "ReportUser [reportedUser=" + reportedUser + ", reportedCount=" + reportedCount + "]";
  }

  public User getReportedUser() {
    return reportedUser;
  }

  public void setReportedUser(User reportedUser) {
    this.reportedUser = reportedUser;
  }

  public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }


}
