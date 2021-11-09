package com.welcomeToJeju.moj.domain;

import java.sql.Date;

public class Report {

  private int no;
  private User writer;
  private String content;
  private Date registeredDate;
  private ReportStatus status;

  @Override
  public String toString() {
    return "Report [no=" + no + ", writer=" + writer + ", content=" + content + ", registeredDate="
        + registeredDate + ", status=" + status + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public User getWriter() {
    return writer;
  }

  public void setWriter(User writer) {
    this.writer = writer;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public ReportStatus getStatus() {
    return status;
  }

  public void setStatus(ReportStatus status) {
    this.status = status;
  }


}
