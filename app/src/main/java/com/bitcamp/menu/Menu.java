package com.bitcamp.menu;

public abstract class Menu {

	public static final int ACCESS_LOGOUT = 0x01;
	public static final int ACCESS_GENERAL = 0x02;
	public static final int ACCESS_ADMIN = 0x04;
	
	String title;
	int accessScope;
	public Menu(String title) {
		this(title, ACCESS_LOGOUT | ACCESS_GENERAL | ACCESS_ADMIN);
	}
	
	public Menu(String title,int accessScope) {
		this.title = title;
		this.accessScope = accessScope;
	}

	public abstract void execute();
}
