package com.welcomeToJeju.moj.domain;

public class ReportTheme extends Report {

  Theme reportedTheme;
  int reportedCount;

  @Override
  public String toString() {
    return "ReportTheme [reportedTheme=" + reportedTheme + ", reportedCount=" + reportedCount + "]";
  }

  public Theme getReportedTheme() {
    return reportedTheme;
  }

  public void setReportedTheme(Theme reportedTheme) {
    this.reportedTheme = reportedTheme;
  }

  public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }


}
