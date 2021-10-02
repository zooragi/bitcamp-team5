package com.bitcamp.goodplace.domain;

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

  private List<User> likedUsers = new ArrayList<>(); //팔로우
  private List<Theme> themeList = new ArrayList<>();
  private List<Theme> likedThemes = new ArrayList<>(); // 북마크




  @Override
  public String toString() {
    return "User [no=" + no + ", email=" + email + ", password=" + password + ", nickName="
        + nickName + ", registeredDate=" + registeredDate + ", viewCount=" + viewCount
        + ", reportedCount=" + reportedCount + ", warningCount=" + warningCount + ", likedUsers="
        + likedUsers + ", themeList=" + themeList + ", likedThemes=" + likedThemes + "]";
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

  public List<User> getLikedUsers() {
    return likedUsers;
  }


  public void setLikedUsers(List<User> likedUsers) {
    this.likedUsers = likedUsers;
  }


  public List<Theme> getThemeList() {
    return themeList;
  }



  public void setThemeList(List<Theme> themeList) {
    this.themeList = themeList;
  }


  public List<Theme> getLikedThemes() {
    return likedThemes;
  }


  public void setLikedThemes(List<Theme> likedThemes) {
    this.likedThemes = likedThemes;
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
