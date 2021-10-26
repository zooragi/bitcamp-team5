package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class Place {
  private int no;
  private int themeNo;
  private String storeName;
  private String storeAddress;
  private List<Photo> photos = new ArrayList<>();
  private List<Comment> comments = new ArrayList<>();
  private String xCoord;
  private String yCoord;
  private User owner;

  @Override
  public String toString() {
    return "Place [no=" + no + ", themeNo=" + themeNo + ", storeName=" + storeName + ", storeAddress="
        + storeAddress + ", photos=" + photos + ", comments=" + comments + ", xCoord=" + xCoord
        + ", yCoord=" + yCoord + "]";
  }

  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getThemeNo() {
    return themeNo;
  }
  public void setThemeNo(int themeNo) {
    this.themeNo = themeNo;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}


}
