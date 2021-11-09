package com.welcomeToJeju.moj.domain;

public class Comment {

  int no;
  String placeId;
  int userNo;
  String comment;

  @Override
  public String toString() {
    return "Comment [no=" + no + ", placeId=" + placeId + ", userNo=" + userNo + ", comment="
        + comment + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
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
