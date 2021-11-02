package com.welcomeToJeju.moj.domain;

public class Comment {

  int no;
  int placeNo;
  int userNo;
  String comment;

  @Override
  public String toString() {
    return "Comment [no=" + no + ", placeNo=" + placeNo + ", userNo=" + userNo + ", comment="
        + comment + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getPlaceNo() {
    return placeNo;
  }

  public void setPlaceNo(int placeNo) {
    this.placeNo = placeNo;
  }

  public int getUserNo() {
    return userNo;
  }

  public void setUserNo(int userNo) {
    this.userNo = userNo;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


}
