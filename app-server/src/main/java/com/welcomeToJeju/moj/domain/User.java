package com.welcomeToJeju.moj.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class User implements Comparable<User> {
  private int no;
  private String email;
  private String password;
  private String nickName;
  private Date registeredDate;
  private int viewCount;
  private int reportedCount = 0;
  private int warningCount = 0;

  private List<Integer> likedUserNo = new ArrayList<>(); //팔로우 

  @Override
  public String toString() {
    return "User [no=" + no + ", email=" + email + ", password=" + password + ", nickName="
        + nickName + ", registeredDate=" + registeredDate + ", viewCount=" + viewCount
        + ", reportedCount=" + reportedCount + ", warningCount=" + warningCount + ", likedUsers="
        + likedUserNo + ", ]";
  }


  public List<Integer> getLikedUserNo() {
    return likedUserNo;
  }

  public void setLikedUser(List<Integer> likedUserNo) {
    this.likedUserNo = likedUserNo;
  }

  public int getReportedCount() {
    return reportedCount;
  }


  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }

  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
  }


  public String getEmail() {
    return email;
  }



  public void setEmail(String email) {
    this.email = email;
  }



  public String getPassword() {
    return password;
  }



  public void setPassword(String password) {
    this.password = password;
  }



  public String getNickName() {
    return nickName;
  }


  public void setNickName(String nickName) {
    this.nickName = nickName;
  }



  public Date getRegisteredDate() {
    return registeredDate;
  }


  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }



  public int getViewCount() {
    return viewCount;
  }


  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  @Override
  public int compareTo(User user) {
    return user.viewCount - this.viewCount;
  }


  public int getWarningCount() {
    return warningCount;
  }


  public void setWarningCount(int warningCount) {
    this.warningCount = warningCount;
  }

}
