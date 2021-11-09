package com.welcomeToJeju.moj.domain;

public class Photo {

  int no;
  String placeId;
  int userNo;
  String filePath;

  @Override
  public String toString() {
    return "Photo [no=" + no + ", placeId=" + placeId + ", userNo=" + userNo + ", filePath="
        + filePath + "]";
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

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }


}
