package com.welcomeToJeju.moj.domain;

public class Photo {

  int no;
  String filePath;

  @Override
  public String toString() {
    return "Photo [no=" + no + ", filePath=" + filePath + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }


}
