package com.bitcamp.goodplace.domain;

import java.util.ArrayList;
import java.util.List;

public class Theme {
  private String title;
  private String userName;
  private int viewCount;
  private boolean isPublic;
  private boolean isShare;
  private List<Place> placeList;
  private List<String> hashtags;

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public List<String> getHashtags() {
    if(this.hashtags == null) {
      setHashtags(new ArrayList<String>());
    }
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


}
