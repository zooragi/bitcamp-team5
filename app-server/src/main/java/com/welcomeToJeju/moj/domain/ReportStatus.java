package com.welcomeToJeju.moj.domain;

public class ReportStatus {

  private int no;
  private String title;

  @Override
  public String toString() {
    return "ReportStatus [no=" + no + ", title=" + title + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


}
