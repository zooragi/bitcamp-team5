package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class Theme implements Comparable<Theme>{
  private int no;
  private String title;
  private User themeOwner;
  private int viewCount;
  private int isPublic;
  private int isShare;
  private String category; 
  private int reportedCount = 0;

  private List<Place> placeList = new ArrayList<Place>();
  private List<String> hashtags = new ArrayList<String>();
  private List<User> likedThemeUser = new ArrayList<User>();

  @Override
  public String toString() {
    return "Theme [no=" + no + ", title=" + title + ", themeOwnerName=" + themeOwner + ", viewCount=" + viewCount
        + ", isPublic=" + isPublic + ", isShare=" + isShare + ", placeList=" + placeList + ", hashtags=" + hashtags
        + ", category=" + category + ", reportedCount=" + reportedCount + "]";
  }

  public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
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

  public User getThemeOwner() {
    return themeOwner;
  }

  public void setThemeOwner(User themeOwner) {
    this.themeOwner = themeOwner;
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

	public List<User> getLikedThemeUser() {
		return likedThemeUser;
	}

	public void setLikedThemeUser(List<User> likedThemeUser) {
		this.likedThemeUser = likedThemeUser;
	}

}