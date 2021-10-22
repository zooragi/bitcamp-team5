package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class Theme implements Comparable<Theme>{
  private int no;
  private String title;
  private User owner;
  private int viewCount;
  private int isPublic;
  private int isShare;
  private Category category; 
  private int reportedCount = 0;

  private List<Place> placeList = new ArrayList<Place>();
  private List<String> hashtags = new ArrayList<String>();
  
  @Override
  public String toString() {
    return "Theme [no=" + no + ", title=" + title + ", themeOwnerName=" + owner + ", viewCount=" + viewCount
        + ", isPublic=" + isPublic + ", isShare=" + isShare + ", placeList=" + placeList + ", hashtags=" + hashtags
        + ", category=" + category + ", reportedCount=" + reportedCount + "]";
  }

  public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
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

  public int isPublic() {
    return isPublic;
  }

  public void setPublic(int isPublic) {
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

  public int isShare() {
    return isShare;
  }

  public void setShare(int isShare) {
    this.isShare = isShare;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
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

}