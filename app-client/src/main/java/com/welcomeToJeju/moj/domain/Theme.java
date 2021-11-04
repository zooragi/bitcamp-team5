package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class Theme implements Comparable<Theme> {

  private int no;
  private String title;
  private User owner;
  private Category category;
  private int isPublic;
  private int isShare;

  private int viewCount;
  private int reportedCount;

  private List<Place> placeList = new ArrayList<>();
  private List<String> hashtags = new ArrayList<>();

  @Override
  public String toString() {
    return "Theme [no=" + no + ", title=" + title + ", owner=" + owner + ", category=" + category
        + ", isPublic=" + isPublic + ", isShare=" + isShare + ", viewCount=" + viewCount
        + ", reportedCount=" + reportedCount + ", placeList=" + placeList + ", hashtags=" + hashtags
        + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public int getIsPublic() {
    return isPublic;
  }

  public void setIsPublic(int isPublic) {
    this.isPublic = isPublic;
  }

  public int getIsShare() {
    return isShare;
  }

  public void setIsShare(int isShare) {
    this.isShare = isShare;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }

  public List<Place> getPlaceList() {
    return placeList;
  }

  public void setPlaceList(List<Place> placeList) {
    this.placeList = placeList;
  }

  public List<String> getHashtags() {
    return hashtags;
  }

  public void setHashtags(List<String> hashtags) {
    this.hashtags = hashtags;
  }

  @Override
  public int compareTo(Theme theme) {
    return theme.viewCount - this.viewCount;
  }


}
