package com.welcomeToJeju.moj.domain;

import java.sql.Date;

public class Report {

  public static final String WAITING = "대기중";
  //	public static final String CHECKING = "확인중";
  public static final String COMPLETE = "완료";

  private int no;
  private String content;
  private User writer;
  private String state;
  private Date registeredDate;

  @Override
  public String toString() {
    return "Report [no=" + no + ", content=" + content + ", writer=" + writer + ", state=" + state + ", registeredDate="
        + registeredDate + "]";
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public User getWriter() {
    return writer;
  }
  public void setWriter(User writer) {
    this.writer = writer;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

}
