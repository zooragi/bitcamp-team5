package com.bitcamp.goodplace.domain;

import java.util.ArrayList;
import java.util.List;

public class Theme implements Comparable<Theme>{
  private int no;
  private String title;
  private String userName;
  private int viewCount;
  private boolean isPublic;
  private boolean isShare;
  private List<Place> placeList = new ArrayList<Place>();
  private List<String> hashtags = new ArrayList<String>();
  private String category; 
  private int warning = 0;



  @Override
  public String toString() {
    return "Theme [no=" + no + ", title=" + title + ", userName=" + userName + ", viewCount="
        + viewCount + ", isPublic=" + isPublic + ", isShare=" + isShare + ", placeList=" + placeList
        + ", hashtags=" + hashtags + ", category=" + category + ", warning=" + warning + "]";
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<String> getHashtags() {
    return hashtags;
  }

  public void setHashtags(List<String> hashtag) {
    this.hashtags = hashtag;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public void setPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public List<Place> getPlaceList() {
    return placeList;
  }

  public void setPlaceList(List<Place> placeList) {
    this.placeList = placeList;
  }

  public boolean isShare() {
    return isShare;
  }

  public void setShare(boolean isShare) {
    this.isShare = isShare;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public int compareTo(Theme theme) {
    return theme.viewCount - this.viewCount ;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getWarning() {
    return warning;
  }

  public void setWarning(int warning) {
    this.warning = warning;
  }

}