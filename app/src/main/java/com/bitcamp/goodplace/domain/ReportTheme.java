package com.bitcamp.goodplace.domain;

public class ReportTheme extends Report{
  Theme reportedTheme;
  int reportedCount;

  public Theme getReportedTheme() {
    return reportedTheme;
  }

  public void setReportedTheme(Theme theme) {
    this.reportedTheme = theme;
  }

  public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }

}
