package com.bitcamp.goodplace.domain;

import java.util.List;

public class Thema {
	private String title;
	private List<String> hashtag;
	private boolean isPublic;
	private int viewCount;
	private List<String> placeList;
	private boolean isShare;
	private String userName;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getHashtag() {
		return hashtag;
	}
	public void setHashtag(List<String> hashtag) {
		this.hashtag = hashtag;
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
	public List<String> getPlaceList() {
		return placeList;
	}
	public void setPlaceList(List<String> placeList) {
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
