package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class Place {

  private String id;
  private String storeName;
  private String storeAddress;

  private List<Photo> photos = new ArrayList<>();
  private List<Comment> comments = new ArrayList<>();

  private String xCoord;
  private String yCoord;

  @Override
  public String toString() {
    return "Place [id=" + id + ", storeName=" + storeName
        + ", storeAddress=" + storeAddress + ", xCoord=" + xCoord + ", yCoord=" + yCoord + "]";
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getStoreAddress() {
    return storeAddress;
  }

  public void setStoreAddress(String storeAddress) {
    this.storeAddress = storeAddress;
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public String getxCoord() {
    return xCoord;
  }

  public void setxCoord(String xCoord) {
    this.xCoord = xCoord;
  }

  public String getyCoord() {
    return yCoord;
  }

  public void setyCoord(String yCoord) {
    this.yCoord = yCoord;
  }


}
