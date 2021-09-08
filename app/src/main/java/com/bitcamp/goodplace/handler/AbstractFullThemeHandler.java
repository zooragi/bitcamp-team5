package com.bitcamp.goodplace.handler;

import java.util.List;

import com.bitcamp.goodplace.domain.User;

public abstract class AbstractFullThemeHandler implements Command{
	List<User> userList;
	
	public AbstractFullThemeHandler(List<User> userList) {
		this.userList = userList;
	}
}
