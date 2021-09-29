package com.bitcamp.goodplace.domain;

public class Report {
	
	private int no;
	private String content;
	private User reporter;
	private String state;
	
	@Override
	public String toString() {
		return "Report [no=" + no + ", content=" + content + ", reporter=" + reporter + ", state=" + state + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getReporter() {
		return reporter;
	}
	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
