package com.bitcamp.goodplace.domain;

public class ReportUser extends Report{
  User reportedUser;
  int reportedCount;

  public User getReportedUser() {
    return reportedUser;
  }

  public void setReportedUser(User user) {
    this.reportedUser = user;
  }

  public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }

}
