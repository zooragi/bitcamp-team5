package com.welcomeToJeju.moj.domain;

public class Comment {
	int no;
	String comment;
	
	@Override
	public String toString() {
		return "Comment [no=" + no + ", comment=" + comment + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
