package com.bitcamp.goodplace.domain;

import java.util.ArrayList;
import java.util.List;

public class Theme implements Comparable<Theme> {
  private int no;
  private String title;
  private User userNickName;
  private int viewCount;
  private List<Place> placeList = new ArrayList<>();
  private List<String> hashtags = new ArrayList<>();
  private String category; 
  private int warning;

  private boolean isPublic;

  //  private boolean isShare;
  //  List<User> participatingUser = new ArrayList<>();




  @Override
  public String toString() {
    return "Theme [no=" + no + ", title=" + title + ", userNickName=" + userNickName
        + ", viewCount=" + viewCount + ", placeList=" + placeList + ", hashtags=" + hashtags
        + ", category=" + category + ", warning=" + warning + ", isPublic=" + isPublic
        /*", isShare=" + isShare + ", participatingUser=" + participatingUser + "]"*/;
  }


  public boolean isPublic() {
    return isPublic;
  }


  public void setPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }


  //  public boolean isShare() {
  //    return isShare;
  //  }
  //
  //
  //  public void setShare(boolean isShare) {
  //    this.isShare = isShare;
  //  }
  //
  //
  //  public List<User> getParticipatingUser() {
  //    return participatingUser;
  //  }
  //
  //
  //  public void setParticipatingUser(List<User> participatingUser) {
  //    this.participatingUser = participatingUser;
  //  }



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


  public User getUserNickName() {
    return userNickName;
  }


  public void setUserNickName(User userName) {
    this.userNickName = userName;
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


  public List<String> getHashtags() {
    return hashtags;
  }


  public void setHashtags(List<String> hashtags) {
    this.hashtags = hashtags;
  }


  public String getCategory() {
    return category;
  }


  public void setCategory(String category) {
    this.category = category;
  }


  public int getWarning() {
    return warning;
  }


  public void setWarning(int warning) {
    this.warning = warning;
  }


  @Override
  public int compareTo(Theme theme) {
    return theme.viewCount - this.viewCount ;
  }


}