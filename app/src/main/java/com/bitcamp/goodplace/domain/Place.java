package com.bitcamp.goodplace.domain;

import java.util.List;

public class Place {
  private String thema;
  private String storeName;
  private List<String> photo;
  private List<String> comment;
  private String address;

  public String getThema() {
    return thema;
  }
  public void setThema(String thema) {
    this.thema = thema;
  }
  public String getStoreName() {
    return storeName;
  }
  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }
  public List<String> getPhoto() {
    return photo;
  }
  public void setPhoto(List<String> photo) {
    this.photo = photo;
  }
  public List<String> getComment() {
    return comment;
  }
  public void setComment(List<String> comment) {
    this.comment = comment;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
}
